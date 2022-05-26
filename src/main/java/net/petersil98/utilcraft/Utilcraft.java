package net.petersil98.utilcraft;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.petersil98.utilcraft.block_entities.UtilcraftBlockEntities;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.commands.UtilcraftCommands;
import net.petersil98.utilcraft.config.Config;
import net.petersil98.utilcraft.container.UtilcraftContainer;
import net.petersil98.utilcraft.data.KeyBindings;
import net.petersil98.utilcraft.data.capabilities.home.CapabilityHome;
import net.petersil98.utilcraft.data.capabilities.last_death.CapabilityLastDeath;
import net.petersil98.utilcraft.data.capabilities.vein_miner.CapabilityVeinMiner;
import net.petersil98.utilcraft.enchantments.UtilcraftEnchantments;
import net.petersil98.utilcraft.entities.UtilcraftEntities;
import net.petersil98.utilcraft.gamerules.UtilcraftGameRules;
import net.petersil98.utilcraft.items.UtilcraftItems;
import net.petersil98.utilcraft.loot_modifiers.UtilcraftLootModifiers;
import net.petersil98.utilcraft.network.NetworkManager;
import net.petersil98.utilcraft.paintings.UtilcraftPaintings;
import net.petersil98.utilcraft.recipes.UtilcraftRecipeTypes;
import net.petersil98.utilcraft.render.EmptyRenderer;
import net.petersil98.utilcraft.render.SecureChestBlockEntityRenderer;
import net.petersil98.utilcraft.screen.*;
import net.petersil98.utilcraft.utils.ClientSetup;
import net.petersil98.utilcraft.worldgen.UtilcraftFeatures;
import net.petersil98.utilcraft.worldgen.WorldGeneration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

@Mod(Utilcraft.MOD_ID)
public class Utilcraft {

    public static final String MOD_ID = "utilcraft";
    public static final String MOD_ID_SHORT = "uc";

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(MOD_ID) {
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
        eventBus.addListener(this::registerCapabilities);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);

        UtilcraftBlocks.BLOCKS.register(eventBus);
        UtilcraftItems.ITEMS.register(eventBus);
        UtilcraftEntities.ENTITIES.register(eventBus);
        UtilcraftEnchantments.ENCHANTMENTS.register(eventBus);
        UtilcraftLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(eventBus);
        UtilcraftBlockEntities.BLOCK_ENTITY_TYPES.register(eventBus);
        UtilcraftContainer.CONTAINER_TYPES.register(eventBus);
        UtilcraftRecipeTypes.RECIPE_SERIALIZERS.register(eventBus);
        UtilcraftPaintings.PAINTING_TYPES.register(eventBus);
        UtilcraftFeatures.CONFIGURED_FEATURES.register(eventBus);
        UtilcraftFeatures.PLACED_FEATURES.register(eventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        NetworkManager.registerMessages();
        UtilcraftGameRules.register();
        event.enqueueWork(() -> ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(UtilcraftBlocks.SAKURA_SAPLING.getId(), UtilcraftBlocks.POTTED_SAKURA_SAPLING));
    }

    private void clientSetup(final FMLClientSetupEvent event){
        ItemBlockRenderTypes.setRenderLayer(UtilcraftBlocks.SAKURA_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(UtilcraftBlocks.SAKURA_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(UtilcraftBlocks.SAKURA_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(UtilcraftBlocks.GLASS_STAIRS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(UtilcraftBlocks.POTTED_SAKURA_SAPLING.get(), RenderType.cutout());
        BlockEntityRenderers.register(UtilcraftBlockEntities.UTILCRAFT_SIGN.get(), SignRenderer::new);
        BlockEntityRenderers.register(UtilcraftBlockEntities.SECURE_CHEST.get(), SecureChestBlockEntityRenderer::new);
        ClientRegistry.registerKeyBinding(KeyBindings.VEIN_MINER);
        ClientRegistry.registerKeyBinding(KeyBindings.PING);
        ClientSetup.registerExtensionPoint();
        event.enqueueWork(() -> {
            MenuScreens.register(UtilcraftContainer.DISENCHANTMENT_BLOCK_CONTAINER.get(), DisenchantmentTableScreen::new);
            MenuScreens.register(UtilcraftContainer.SECURE_CHEST_CONTAINER.get(), SecureChestScreen::new);
            MenuScreens.register(UtilcraftContainer.TRAVELERS_BACKPACK_CONTAINER.get(), TravelersBackpackScreen::new);
            MenuScreens.register(UtilcraftContainer.SUSHI_MAKER_CONTAINER.get(), SushiMakerScreen::new);
            MenuScreens.register(UtilcraftContainer.ENTROPY_TABLE_CONTAINER.get(), EntropyTableScreen::new);
            ClientSetup.registerItemProperties();
        });
    }

    private void registerCapabilities(final RegisterCapabilitiesEvent event) {
        CapabilityVeinMiner.register(event);
        CapabilityHome.register(event);
        CapabilityLastDeath.register(event);
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

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD,value= Dist.CLIENT)
    public static class RegistryClient {

        @SubscribeEvent
        public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(UtilcraftEntities.EMPTY_ENTITY.get(), EmptyRenderer::new);
        }

        @SubscribeEvent
        public static void entityRenderers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            MeshDefinition mesh = new MeshDefinition();
            mesh.getRoot().addOrReplaceChild("cube", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
            event.registerLayerDefinition(EmptyRenderer.MODEL_LAYER_LOCATION, () -> LayerDefinition.create(mesh, 64, 32));
        }
    }
}
