package net.petersil98.utilcraft;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.material.Material;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmlclient.registry.ClientRegistry;
import net.petersil98.utilcraft.blocks.*;
import net.petersil98.utilcraft.config.Config;
import net.petersil98.utilcraft.container.*;
import net.petersil98.utilcraft.data.KeyBindings;
import net.petersil98.utilcraft.data.capabilities.home.CapabilityHome;
import net.petersil98.utilcraft.data.capabilities.last_death.CapabilityLastDeath;
import net.petersil98.utilcraft.data.capabilities.vein_miner.CapabilityVeinMiner;
import net.petersil98.utilcraft.gamerules.UtilcraftGameRules;
import net.petersil98.utilcraft.network.NetworkManager;
import net.petersil98.utilcraft.recipes.SushiMakerRecipe;
import net.petersil98.utilcraft.render.SecureChestBlockEntityRenderer;
import net.petersil98.utilcraft.screen.*;
import net.petersil98.utilcraft.block_entities.DisenchantmentTableBlockEntity;
import net.petersil98.utilcraft.block_entities.UtilcraftSignBlockEntity;
import net.petersil98.utilcraft.block_entities.UtilcraftBlockEntities;
import net.petersil98.utilcraft.blocks.sakura.*;
import net.petersil98.utilcraft.blocks.sideslabs.*;
import net.petersil98.utilcraft.commands.UtilcraftCommands;
import net.petersil98.utilcraft.enchantments.BeheadingEnchantment;
import net.petersil98.utilcraft.loot_modifiers.BeheadingModifier;
import net.petersil98.utilcraft.food.AppleJuice;
import net.petersil98.utilcraft.food.Baguette;
import net.petersil98.utilcraft.food.SweetBerryJuice;
import net.petersil98.utilcraft.generation.WorldGeneration;
import net.petersil98.utilcraft.items.*;
import net.petersil98.utilcraft.block_entities.SecureChestBlockEntity;
import net.petersil98.utilcraft.utils.ClientSetup;

import javax.annotation.Nonnull;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Utilcraft.MOD_ID)
public class Utilcraft {

    public static final String MOD_ID = "utilcraft";
    public static final String MOD_ID_SHORT = "uc";

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(MOD_ID) {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(UtilcraftBlocks.GOLD_BRICK);
        }
    };

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public Utilcraft() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
    }

    private void setup(final FMLCommonSetupEvent event) {
        CapabilityVeinMiner.register();
        CapabilityHome.register();
        CapabilityLastDeath.register();
        NetworkManager.registerMessages();
        UtilcraftGameRules.register();
        ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(UtilcraftBlocks.SAKURA_SAPLING.getRegistryName(), () -> UtilcraftBlocks.POTTED_SAKURA_SAPLING);
    }

    private void clientSetup(final FMLClientSetupEvent event){
        ItemBlockRenderTypes.setRenderLayer(UtilcraftBlocks.SAKURA_SAPLING, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(UtilcraftBlocks.SAKURA_TRAPDOOR, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(UtilcraftBlocks.SAKURA_DOOR, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(UtilcraftBlocks.GLASS_STAIRS, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(UtilcraftBlocks.POTTED_SAKURA_SAPLING, RenderType.cutout());
        MenuScreens.register(UtilcraftContainer.DISENCHANTMENT_BLOCK_CONTAINER, DisenchantmentTableScreen::new);
        MenuScreens.register(UtilcraftContainer.SECURE_CHEST_CONTAINER, SecureChestScreen::new);
        MenuScreens.register(UtilcraftContainer.TRAVELERS_BACKPACK_CONTAINER, TravelersBackpackScreen::new);
        MenuScreens.register(UtilcraftContainer.SUSHI_MAKER_CONTAINER, SushiMakerScreen::new);
        MenuScreens.register(UtilcraftContainer.ENTROPY_TABLE_CONTAINER, EntropyTableScreen::new);
        BlockEntityRenderers.register(UtilcraftBlockEntities.UTILCRAFT_SIGN, SignRenderer::new);
        BlockEntityRenderers.register(UtilcraftBlockEntities.SECURE_CHEST, SecureChestBlockEntityRenderer::new);
        ClientRegistry.registerKeyBinding(KeyBindings.VEIN_MINER);
        ClientSetup.registerItemProperties();
        ClientSetup.registerExtensionPoint();
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryMinecraftEvents {
        @SubscribeEvent
        public static void registerBlocks(@Nonnull final RegistryEvent.Register<Block> blockRegistryEvent) {
            SakuraSign sign = new SakuraSign();
            sign.setRegistryName("sakura_sign");

            blockRegistryEvent.getRegistry().register(new GoldBrick().setRegistryName("gold_brick"));
            blockRegistryEvent.getRegistry().register(new GoldStairs().setRegistryName("gold_stairs"));
            blockRegistryEvent.getRegistry().register(new GoldSlab().setRegistryName("gold_slab"));
            blockRegistryEvent.getRegistry().register(new GoldWall().setRegistryName("gold_wall"));
            blockRegistryEvent.getRegistry().register(new CompressedCobblestone().setRegistryName("compressed_cobblestone"));
            blockRegistryEvent.getRegistry().register(new SilverOre().setRegistryName("silver_ore"));
            blockRegistryEvent.getRegistry().register(new RoseQuartzOre().setRegistryName("rose_quartz_ore"));
            blockRegistryEvent.getRegistry().register(new RoseQuartzBlock().setRegistryName("rose_quartz_block"));
            blockRegistryEvent.getRegistry().register(new SideStoneSlab().setRegistryName("side_stone_slab"));
            blockRegistryEvent.getRegistry().register(new SideCobblestoneSlab().setRegistryName("side_cobblestone_slab"));
            blockRegistryEvent.getRegistry().register(new SideOakSlab().setRegistryName("side_oak_slab"));
            blockRegistryEvent.getRegistry().register(new SideSpruceSlab().setRegistryName("side_spruce_slab"));
            blockRegistryEvent.getRegistry().register(new SideBirchSlab().setRegistryName("side_birch_slab"));
            blockRegistryEvent.getRegistry().register(new SideJungleSlab().setRegistryName("side_jungle_slab"));
            blockRegistryEvent.getRegistry().register(new SideAcaciaSlab().setRegistryName("side_acacia_slab"));
            blockRegistryEvent.getRegistry().register(new SideDarkOakSlab().setRegistryName("side_dark_oak_slab"));
            blockRegistryEvent.getRegistry().register(new SideSakuraSlab().setRegistryName("side_sakura_slab"));
            blockRegistryEvent.getRegistry().register(new SideGoldSlab().setRegistryName("side_gold_slab"));
            blockRegistryEvent.getRegistry().register(new SakuraLeaves().setRegistryName("sakura_leaves"));
            blockRegistryEvent.getRegistry().register(new SakuraLog().setRegistryName("sakura_log"));
            blockRegistryEvent.getRegistry().register(new SakuraPlanks().setRegistryName("sakura_planks"));
            blockRegistryEvent.getRegistry().register(new SakuraSapling().setRegistryName("sakura_sapling"));
            blockRegistryEvent.getRegistry().register(new SakuraSlab().setRegistryName("sakura_slab"));
            blockRegistryEvent.getRegistry().register(new SakuraStairs().setRegistryName("sakura_stairs"));
            blockRegistryEvent.getRegistry().register(new SakuraFence().setRegistryName("sakura_fence"));
            blockRegistryEvent.getRegistry().register(new SakuraFenceGate().setRegistryName("sakura_fence_gate"));
            blockRegistryEvent.getRegistry().register(new SakuraPressurePlate().setRegistryName("sakura_pressure_plate"));
            blockRegistryEvent.getRegistry().register(new SakuraTrapdoor().setRegistryName("sakura_trapdoor"));
            blockRegistryEvent.getRegistry().register(sign);
            blockRegistryEvent.getRegistry().register(new SakuraWallSign(sign).setRegistryName("sakura_wall_sign"));
            blockRegistryEvent.getRegistry().register(new SakuraButton().setRegistryName("sakura_button"));
            blockRegistryEvent.getRegistry().register(new SakuraDoor().setRegistryName("sakura_door"));
            blockRegistryEvent.getRegistry().register(new DisenchantmentTable().setRegistryName("disenchantment_table"));
            blockRegistryEvent.getRegistry().register(new SecureChest().setRegistryName("secure_chest"));
            blockRegistryEvent.getRegistry().register(new RedstoneStairs().setRegistryName("redstone_stairs"));
            blockRegistryEvent.getRegistry().register(new RedstoneSlab().setRegistryName("redstone_slab"));
            blockRegistryEvent.getRegistry().register(new SideRedstoneSlab().setRegistryName("side_redstone_slab"));
            blockRegistryEvent.getRegistry().register(new SushiMaker().setRegistryName("sushi_maker"));
            blockRegistryEvent.getRegistry().register(new GlassStairs().setRegistryName("glass_stairs"));
            blockRegistryEvent.getRegistry().register(new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT.delegate.get() ,() -> UtilcraftBlocks.SAKURA_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()).setRegistryName("potted_sakura_sapling"));
            blockRegistryEvent.getRegistry().register(new SilverBlock().setRegistryName("silver_block"));
            blockRegistryEvent.getRegistry().register(new ChunkLoader().setRegistryName("chunk_loader"));
            blockRegistryEvent.getRegistry().register(new EntropyTable().setRegistryName("entropy_table"));
        }

        @SubscribeEvent
        public static void registerItems(@Nonnull final RegistryEvent.Register<Item> itemRegistryEvent) {
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.GOLD_BRICK, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("gold_brick"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.GOLD_STAIRS, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("gold_stairs"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.GOLD_SLAB, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("gold_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.GOLD_WALL, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("gold_wall"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.COMPRESSED_COBBLESTONE, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("compressed_cobblestone"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.SILVER_ORE, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("silver_ore"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.ROSE_QUARTZ_ORE, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("rose_quartz_ore"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.ROSE_QUARTZ_BLOCK, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("rose_quartz_block"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftSideSlabs.SIDE_STONE_SLAB, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("side_stone_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftSideSlabs.SIDE_COBBLESTONE_SLAB, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("side_cobblestone_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftSideSlabs.SIDE_OAK_SLAB, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("side_oak_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftSideSlabs.SIDE_SPRUCE_SLAB, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("side_spruce_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftSideSlabs.SIDE_BIRCH_SLAB, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("side_birch_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftSideSlabs.SIDE_JUNGLE_SLAB, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("side_jungle_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftSideSlabs.SIDE_ACACIA_SLAB, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("side_acacia_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftSideSlabs.SIDE_DARK_OAK_SLAB, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("side_dark_oak_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftSideSlabs.SIDE_SAKURA_SLAB, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("side_sakura_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftSideSlabs.SIDE_GOLD_SLAB, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("side_gold_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftSideSlabs.SIDE_REDSTONE_SLAB, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("side_redstone_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.SAKURA_LEAVES, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("sakura_leaves"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.SAKURA_LOG, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("sakura_log"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.SAKURA_PLANKS, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("sakura_planks"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.SAKURA_SAPLING, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("sakura_sapling"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.SAKURA_SLAB, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("sakura_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.SAKURA_STAIRS, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("sakura_stairs"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.SAKURA_FENCE, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("sakura_fence"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.SAKURA_FENCE_GATE, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("sakura_fence_gate"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.SAKURA_PRESSURE_PLATE, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("sakura_pressure_plate"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.SAKURA_TRAPDOOR, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("sakura_trapdoor"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.SAKURA_BUTTON, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("sakura_button"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.SAKURA_DOOR, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("sakura_door"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.DISENCHANTMENT_TABLE, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("disenchantment_table"));
            itemRegistryEvent.getRegistry().register(new SecureChestItem(UtilcraftBlocks.SECURE_CHEST, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("secure_chest"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.REDSTONE_STAIRS, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("redstone_stairs"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.REDSTONE_SLAB, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("redstone_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.SUSHI_MAKER, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("sushi_maker"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.GLASS_STAIRS, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("glass_stairs"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.SILVER_BLOCK, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("silver_block"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.CHUNK_LOADER, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("chunk_loader"));
            itemRegistryEvent.getRegistry().register(new BlockItem(UtilcraftBlocks.ENTROPY_TABLE, new Item.Properties().tab(ITEM_GROUP)).setRegistryName("entropy_table"));

            itemRegistryEvent.getRegistry().register(new Juicer().setRegistryName("juicer"));
            itemRegistryEvent.getRegistry().register(new AppleJuice().setRegistryName("apple_juice"));
            itemRegistryEvent.getRegistry().register(new SweetBerryJuice().setRegistryName("sweet_berry_juice"));
            itemRegistryEvent.getRegistry().register(new Flour().setRegistryName("flour"));
            itemRegistryEvent.getRegistry().register(new Baguette().setRegistryName("baguette"));
            itemRegistryEvent.getRegistry().register(new RoseQuartz().setRegistryName("rose_quartz"));
            itemRegistryEvent.getRegistry().register(new RoseQuartzHelmet().setRegistryName("rose_quartz_helmet"));
            itemRegistryEvent.getRegistry().register(new RoseQuartzChestplate().setRegistryName("rose_quartz_chestplate"));
            itemRegistryEvent.getRegistry().register(new RoseQuartzLeggings().setRegistryName("rose_quartz_leggings"));
            itemRegistryEvent.getRegistry().register(new RoseQuartzBoots().setRegistryName("rose_quartz_boots"));
            itemRegistryEvent.getRegistry().register(new RoseQuartzPickaxe().setRegistryName("rose_quartz_pickaxe"));
            itemRegistryEvent.getRegistry().register(new RoseQuartzSword().setRegistryName("rose_quartz_sword"));
            itemRegistryEvent.getRegistry().register(new RoseQuartzShovel().setRegistryName("rose_quartz_shovel"));
            itemRegistryEvent.getRegistry().register(new RoseQuartzHoe().setRegistryName("rose_quartz_hoe"));
            itemRegistryEvent.getRegistry().register(new RoseQuartzAxe().setRegistryName("rose_quartz_axe"));
            itemRegistryEvent.getRegistry().register(new RoseQuartzSuperHammer().setRegistryName("rose_quartz_super_hammer"));
            itemRegistryEvent.getRegistry().register(new RoseQuartzSuperShovel().setRegistryName("rose_quartz_super_shovel"));
            itemRegistryEvent.getRegistry().register(new SakuraSignItem().setRegistryName("sakura_sign"));
            itemRegistryEvent.getRegistry().register(new TravelersBackpack().setRegistryName("travelers_backpack"));
            itemRegistryEvent.getRegistry().register(new TNTFinder().setRegistryName("tnt_finder"));
            itemRegistryEvent.getRegistry().register(new SilverIngot().setRegistryName("silver_ingot"));
            itemRegistryEvent.getRegistry().register(new ButchersKnife().setRegistryName("butchers_knife"));
            itemRegistryEvent.getRegistry().register(new SpawnerItem().setRegistryName("spawner_item"));
        }

        @SubscribeEvent
        public static void registerBiomes(@Nonnull final RegistryEvent.Register<Biome> event){
            //event.getRegistry().register(new GraveyardBiome().setRegistryName("graveyard"));
        }

        @SubscribeEvent
        public static void registerEnchantments(@Nonnull final RegistryEvent.Register<Enchantment> enchantmentRegister) {
            enchantmentRegister.getRegistry().register(new BeheadingEnchantment().setRegistryName("beheading_enchantment"));
        }

        @SubscribeEvent
        public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
            event.getRegistry().register(new BeheadingModifier.Serializer().setRegistryName("beheading"));
        }

        @SubscribeEvent
        public static void registerBlockEntities(@Nonnull final RegistryEvent.Register<BlockEntityType<?>> blockEntityRegister) {
            blockEntityRegister.getRegistry().register(BlockEntityType.Builder.of(DisenchantmentTableBlockEntity::new, UtilcraftBlocks.DISENCHANTMENT_TABLE).build(null).setRegistryName("disenchantment_table"));
            blockEntityRegister.getRegistry().register(BlockEntityType.Builder.of(UtilcraftSignBlockEntity::new, UtilcraftBlocks.SAKURA_SIGN, UtilcraftBlocks.SAKURA_WALL_SIGN).build(null).setRegistryName("mod_sign"));
            blockEntityRegister.getRegistry().register(BlockEntityType.Builder.of(SecureChestBlockEntity::new, UtilcraftBlocks.SECURE_CHEST).build(null).setRegistryName("secure_chest"));
        }

        @SubscribeEvent
        public static void registerContainer(@Nonnull final RegistryEvent.Register<MenuType<?>> containerRegister) {
            containerRegister.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> new DisenchantmentTableContainer(windowId, inv)).setRegistryName("disenchantment_table"));
            containerRegister.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> new SecureChestContainer(windowId, inv)).setRegistryName("secure_chest"));
            containerRegister.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> new SushiMakerContainer(windowId, inv)).setRegistryName("sushi_maker"));
            containerRegister.getRegistry().register(IForgeContainerType.create(TravelersBackpackContainer::new).setRegistryName("travelers_backpack"));
            containerRegister.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> new EntropyTableContainer(windowId, inv)).setRegistryName("entropy_table"));
        }

        @SubscribeEvent
        public static void registerRecipeSerializers(@Nonnull final RegistryEvent.Register<RecipeSerializer<?>> recipeSerializerRegister) {
            recipeSerializerRegister.getRegistry().register(new SushiMakerRecipe.Serializer().setRegistryName("sushi_maker"));
        }

        @SubscribeEvent
        public static void registerPaintingTypes(@Nonnull final RegistryEvent.Register<Motive> paintingTypeRegister) {
            paintingTypeRegister.getRegistry().register(new Motive(16, 16).setRegistryName("frog"));
        }
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
