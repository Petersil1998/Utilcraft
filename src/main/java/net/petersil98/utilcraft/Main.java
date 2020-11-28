package net.petersil98.utilcraft;

import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.petersil98.utilcraft.blocks.*;
import net.petersil98.utilcraft.container.DisenchantmentTableContainer;
import net.petersil98.utilcraft.container.ModContainer;
import net.petersil98.utilcraft.container.SecureChestContainer;
import net.petersil98.utilcraft.data.KeyBindings;
import net.petersil98.utilcraft.network.PacketHandler;
import net.petersil98.utilcraft.renderer.SecureChestItemTileEntityRenderer;
import net.petersil98.utilcraft.renderer.SecureChestTileEntityRenderer;
import net.petersil98.utilcraft.screen.DisenchantmentTableScreen;
import net.petersil98.utilcraft.screen.SecureChestScreen;
import net.petersil98.utilcraft.tile_entities.DisenchantmentTableTileEntity;
import net.petersil98.utilcraft.tile_entities.ModSignTileEntity;
import net.petersil98.utilcraft.tile_entities.ModTileEntities;
import net.petersil98.utilcraft.blocks.sakura.*;
import net.petersil98.utilcraft.blocks.sideslabs.*;
import net.petersil98.utilcraft.commands.ModCommands;
import net.petersil98.utilcraft.enchantments.BeheadingEnchantment;
import net.petersil98.utilcraft.enchantments.BeheadingModifier;
import net.petersil98.utilcraft.food.AppleJuice;
import net.petersil98.utilcraft.food.Baguette;
import net.petersil98.utilcraft.food.SweetBerryJuice;
import net.petersil98.utilcraft.generation.WorldGeneration;
import net.petersil98.utilcraft.items.*;
import net.petersil98.utilcraft.tile_entities.SecureChestTileEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

@Mod("utilcraft")
public class Main {

    public static final String MOD_ID = "utilcraft";

    public static boolean isVeinMinerActive = false;

    public static ItemGroup itemGroup = new ItemGroup("utilcraft") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.GOLD_BRICK);
        }
    };

    private static final Logger LOGGER = LogManager.getLogger();

    public Main() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        }

    private void setup(final FMLCommonSetupEvent event) {

        //CapabilityTileEntityOwner.register();
        PacketHandler.registerMessages();
    }

    private void clientSetup(final FMLClientSetupEvent event){
        RenderTypeLookup.setRenderLayer(ModBlocks.SAKURA_SAPLING, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.SAKURA_TRAPDOOR, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.SAKURA_DOOR, RenderType.getCutout());
        ScreenManager.registerFactory(ModContainer.DISENCHANTMENT_BLOCK_CONTAINER, DisenchantmentTableScreen::new);
        ScreenManager.registerFactory(ModContainer.SECURE_CHEST_CONTAINER, SecureChestScreen::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.MOD_SIGN, SignTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.SECURE_CHEST, SecureChestTileEntityRenderer::new);
        ClientRegistry.registerKeyBinding(KeyBindings.VEIN_MINER);
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> blockRegistryEvent) {
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
        }

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> itemRegistryEvent) {
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.GOLD_BRICK, new Item.Properties().group(itemGroup)).setRegistryName("gold_brick"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.GOLD_STAIRS, new Item.Properties().group(itemGroup)).setRegistryName("gold_stairs"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.GOLD_SLAB, new Item.Properties().group(itemGroup)).setRegistryName("gold_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.GOLD_WALL, new Item.Properties().group(itemGroup)).setRegistryName("gold_wall"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.COMPRESSED_COBBLESTONE, new Item.Properties().group(itemGroup)).setRegistryName("compressed_cobblestone"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SILVER_ORE, new Item.Properties().group(itemGroup)).setRegistryName("silver_ore"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.ROSE_QUARTZ_ORE, new Item.Properties().group(itemGroup)).setRegistryName("rose_quartz_ore"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.ROSE_QUARTZ_BLOCK, new Item.Properties().group(itemGroup)).setRegistryName("rose_quartz_block"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDE_STONE_SLAB, new Item.Properties().group(itemGroup)).setRegistryName("side_stone_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDE_COBBLESTONE_SLAB, new Item.Properties().group(itemGroup)).setRegistryName("side_cobblestone_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDE_OAK_SLAB, new Item.Properties().group(itemGroup)).setRegistryName("side_oak_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDE_SPRUCE_SLAB, new Item.Properties().group(itemGroup)).setRegistryName("side_spruce_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDE_BIRCH_SLAB, new Item.Properties().group(itemGroup)).setRegistryName("side_birch_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDE_JUNGLE_SLAB, new Item.Properties().group(itemGroup)).setRegistryName("side_jungle_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDE_ACACIA_SLAB, new Item.Properties().group(itemGroup)).setRegistryName("side_acacia_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDE_DARK_OAK_SLAB, new Item.Properties().group(itemGroup)).setRegistryName("side_dark_oak_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDE_SAKURA_SLAB, new Item.Properties().group(itemGroup)).setRegistryName("side_sakura_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDE_GOLD_SLAB, new Item.Properties().group(itemGroup)).setRegistryName("side_gold_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDE_REDSTONE_SLAB, new Item.Properties().group(itemGroup)).setRegistryName("side_redstone_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURA_LEAVES, new Item.Properties().group(itemGroup)).setRegistryName("sakura_leaves"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURA_LOG, new Item.Properties().group(itemGroup)).setRegistryName("sakura_log"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURA_PLANKS, new Item.Properties().group(itemGroup)).setRegistryName("sakura_planks"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURA_SAPLING, new Item.Properties().group(itemGroup)).setRegistryName("sakura_sapling"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURA_SLAB, new Item.Properties().group(itemGroup)).setRegistryName("sakura_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURA_STAIRS, new Item.Properties().group(itemGroup)).setRegistryName("sakura_stairs"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURA_FENCE, new Item.Properties().group(itemGroup)).setRegistryName("sakura_fence"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURA_FENCE_GATE, new Item.Properties().group(itemGroup)).setRegistryName("sakura_fence_gate"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURA_PRESSURE_PLATE, new Item.Properties().group(itemGroup)).setRegistryName("sakura_pressure_plate"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURA_TRAPDOOR, new Item.Properties().group(itemGroup)).setRegistryName("sakura_trapdoor"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURA_BUTTON, new Item.Properties().group(itemGroup)).setRegistryName("sakura_button"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURA_DOOR, new Item.Properties().group(itemGroup)).setRegistryName("sakura_door"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.DISENCHANTMENT_TABLE, new Item.Properties().group(itemGroup)).setRegistryName("disenchantment_table"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SECURE_CHEST, new Item.Properties().setISTER(() -> SecureChestItemTileEntityRenderer::new).group(itemGroup)).setRegistryName("secure_chest"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.REDSTONE_STAIRS, new Item.Properties().group(itemGroup)).setRegistryName("redstone_stairs"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.REDSTONE_SLAB, new Item.Properties().group(itemGroup)).setRegistryName("redstone_slab"));

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
        }

        @SubscribeEvent
        public static void registerBiomes(final RegistryEvent.Register<Biome> event){
            //event.getRegistry().register(new GraveyardBiome().setRegistryName("graveyard"));
        }

        @SubscribeEvent
        public static void registerEnchantments(final RegistryEvent.Register<Enchantment> enchantmentRegister) {
            enchantmentRegister.getRegistry().register(new BeheadingEnchantment().setRegistryName("beheading_enchantment"));
        }

        @SubscribeEvent
        public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
            event.getRegistry().register(new BeheadingModifier.Serializer().setRegistryName("beheading"));
        }

        @SubscribeEvent
        public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> tileEntityRegister) {
            tileEntityRegister.getRegistry().register(TileEntityType.Builder.create(DisenchantmentTableTileEntity::new, ModBlocks.DISENCHANTMENT_TABLE).build(null).setRegistryName("disenchantment_table"));
            tileEntityRegister.getRegistry().register(TileEntityType.Builder.create(ModSignTileEntity::new, ModBlocks.SAKURA_SIGN, ModBlocks.SAKURA_WALL_SIGN).build(null).setRegistryName("mod_sign"));
            tileEntityRegister.getRegistry().register(TileEntityType.Builder.create(SecureChestTileEntity::new, ModBlocks.SECURE_CHEST).build(null).setRegistryName("secure_chest"));
        }

        @SubscribeEvent
        public static void registerContainer(final RegistryEvent.Register<ContainerType<?>> containerRegister) {
            containerRegister.getRegistry().register(new ContainerType<>(DisenchantmentTableContainer::new).setRegistryName("disenchantment_table"));
            containerRegister.getRegistry().register(new ContainerType<>(SecureChestContainer::new).setRegistryName("secure_chest"));
        }
    }
    @Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.FORGE)
    public static class RegistryCommands {

        @SubscribeEvent
        public static void registerCommands(RegisterCommandsEvent event){
            ModCommands.register(event.getDispatcher());
        }

        @SubscribeEvent
        public static void registerOres(BiomeLoadingEvent event){
            WorldGeneration.addSilverOre(event.getGeneration());
            WorldGeneration.addRoseQuartzOre(event.getGeneration());
            WorldGeneration.addSakuraTrees(event.getGeneration());
        }
    }
}
