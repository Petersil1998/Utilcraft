package net.petersil98.utilcraft;

import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.commands.UtilcraftCommands;
import net.petersil98.utilcraft.config.Config;
import net.petersil98.utilcraft.container.UtilcraftContainer;
import net.petersil98.utilcraft.data.KeyBindings;
import net.petersil98.utilcraft.data.capabilities.home.CapabilityHome;
import net.petersil98.utilcraft.data.capabilities.last_death.CapabilityLastDeath;
import net.petersil98.utilcraft.data.capabilities.vein_miner.CapabilityVeinMiner;
import net.petersil98.utilcraft.enchantments.UtilcraftEnchantments;
import net.petersil98.utilcraft.gamerules.UtilcraftGameRules;
import net.petersil98.utilcraft.generation.WorldGeneration;
import net.petersil98.utilcraft.items.UtilcraftItems;
import net.petersil98.utilcraft.loot_modifiers.UtilcraftLootModifiers;
import net.petersil98.utilcraft.network.NetworkManager;
import net.petersil98.utilcraft.paintings.UtilcraftPaintings;
import net.petersil98.utilcraft.recipes.UtilcraftRecipeTypes;
import net.petersil98.utilcraft.render.SecureChestTileEntityRenderer;
import net.petersil98.utilcraft.screen.*;
import net.petersil98.utilcraft.tile_entities.UtilcraftTileEntities;
import net.petersil98.utilcraft.utils.ClientSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

@Mod(Utilcraft.MOD_ID)
public class Utilcraft {

    public static final String MOD_ID = "utilcraft";
    public static final String MOD_ID_SHORT = "uc";

    public static final ItemGroup ITEM_GROUP = new ItemGroup(MOD_ID) {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(UtilcraftBlocks.GOLD_BRICK.get());
        }
    };

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public Utilcraft() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);

        UtilcraftBlocks.BLOCKS.register(eventBus);
        UtilcraftItems.ITEMS.register(eventBus);
        UtilcraftEnchantments.ENCHANTMENTS.register(eventBus);
        UtilcraftLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(eventBus);
        UtilcraftTileEntities.TILE_ENTITIES.register(eventBus);
        UtilcraftContainer.CONTAINERS.register(eventBus);
        UtilcraftRecipeTypes.RECIPE_SERIALIZERS.register(eventBus);
        UtilcraftPaintings.PAINTING_TYPES.register(eventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        CapabilityVeinMiner.register();
        CapabilityHome.register();
        CapabilityLastDeath.register();
        NetworkManager.registerMessages();
        UtilcraftGameRules.register();
        event.enqueueWork(() -> ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(UtilcraftBlocks.SAKURA_SAPLING.getId(), UtilcraftBlocks.POTTED_SAKURA_SAPLING));
    }

    private void clientSetup(final FMLClientSetupEvent event){
        RenderTypeLookup.setRenderLayer(UtilcraftBlocks.SAKURA_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(UtilcraftBlocks.SAKURA_TRAPDOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(UtilcraftBlocks.SAKURA_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(UtilcraftBlocks.GLASS_STAIRS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(UtilcraftBlocks.POTTED_SAKURA_SAPLING.get(), RenderType.cutout());
        ClientRegistry.bindTileEntityRenderer(UtilcraftTileEntities.UTILCRAFT_SIGN.get(), SignTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(UtilcraftTileEntities.SECURE_CHEST.get(), SecureChestTileEntityRenderer::new);
        ClientRegistry.registerKeyBinding(KeyBindings.VEIN_MINER);
        ClientSetup.registerExtensionPoint();
        event.enqueueWork(() -> {
            ScreenManager.register(UtilcraftContainer.DISENCHANTMENT_BLOCK_CONTAINER.get(), DisenchantmentTableScreen::new);
            ScreenManager.register(UtilcraftContainer.SECURE_CHEST_CONTAINER.get(), SecureChestScreen::new);
            ScreenManager.register(UtilcraftContainer.TRAVELERS_BACKPACK_CONTAINER.get(), TravelersBackpackScreen::new);
            ScreenManager.register(UtilcraftContainer.SUSHI_MAKER_CONTAINER.get(), SushiMakerScreen::new);
            ScreenManager.register(UtilcraftContainer.ENTROPY_TABLE_CONTAINER.get(), EntropyTableScreen::new);
            ClientSetup.registerItemProperties();
        });
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.FORGE)
    public static class RegistryForgeEvents {

        @SubscribeEvent
        public static void registerCommands(@Nonnull RegisterCommandsEvent event){
            UtilcraftCommands.register(event.getDispatcher());
        }

        @SubscribeEvent
        public static void registerBiomeAddons(@Nonnull BiomeLoadingEvent event){
            WorldGeneration.addSilverOre(event.getGeneration());
            WorldGeneration.addRoseQuartzOre(event.getGeneration());
            if(WorldGeneration.SAKURA_SPAWN_BIOMES.contains(event.getName())) {
                WorldGeneration.addSakuraTrees(event.getGeneration());
            }
        }
    }
}
