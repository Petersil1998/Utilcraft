package net.petersil98.utilcraft;

import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.petersil98.utilcraft.blocks.*;
import net.petersil98.utilcraft.blocks.sakura.*;
import net.petersil98.utilcraft.blocks.sideslabs.*;
import net.petersil98.utilcraft.commands.ModCommands;
import net.petersil98.utilcraft.data.tileEntityOwner.CapabilityTileEntityOwner;
import net.petersil98.utilcraft.enchantments.BeheadingEnchantment;
import net.petersil98.utilcraft.enchantments.BeheadingModifier;
import net.petersil98.utilcraft.food.Applejuice;
import net.petersil98.utilcraft.food.Baguette;
import net.petersil98.utilcraft.food.SweetBerryjuice;
import net.petersil98.utilcraft.generation.WorldGeneration;
import net.petersil98.utilcraft.items.*;
import net.petersil98.utilcraft.proxies.ClientProxy;
import net.petersil98.utilcraft.proxies.IProxy;
import net.petersil98.utilcraft.proxies.ServerProxy;
import net.petersil98.utilcraft.renderer.SignTileEntityRenderer;
import net.petersil98.utilcraft.renderer.ModTileEntityTypes;
import net.petersil98.utilcraft.utils.ModSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

@Mod("utilcraft")
public class Main {

    public static String MOD_ID = "utilcraft";

    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public static ModSetup setup = new ModSetup();

    private static final Logger LOGGER = LogManager.getLogger();

    public Main() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    private void setup(final FMLCommonSetupEvent event) {

        CapabilityTileEntityOwner.register();
        setup.init();
        proxy.init();
    }

    private void clientSetup(final FMLClientSetupEvent event){
        RenderTypeLookup.setRenderLayer(ModBlocks.SAKURASAPLING, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.SAKURATRAPDOOR, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.SAKURADOOR, RenderType.getCutout());
        ScreenManager.registerFactory(ModContainer.DISENCHANTMENTBLOCKCONTAINER, DisenchantmentTableScreen::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntityTypes.MOD_SIGN, SignTileEntityRenderer::new);
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> blockRegistryEvent) {
            blockRegistryEvent.getRegistry().register(new GoldBrick().setRegistryName("goldbrick"));
            blockRegistryEvent.getRegistry().register(new GoldStairs().setRegistryName("goldstairs"));
            blockRegistryEvent.getRegistry().register(new GoldSlab().setRegistryName("goldslab"));
            blockRegistryEvent.getRegistry().register(new GoldWall().setRegistryName("goldwall"));
            blockRegistryEvent.getRegistry().register(new CompressedCobblestone().setRegistryName("compressedcobblestone"));
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
            blockRegistryEvent.getRegistry().register(new SakuraSign().setRegistryName("sakura_sign"));
            blockRegistryEvent.getRegistry().register(new SakuraWallSign().setRegistryName("sakura_wall_sign"));
            blockRegistryEvent.getRegistry().register(new SakuraButton().setRegistryName("sakura_button"));
            blockRegistryEvent.getRegistry().register(new SakuraDoor().setRegistryName("sakura_door"));
            blockRegistryEvent.getRegistry().register(new DisenchantmentTable().setRegistryName("disenchantment_table"));
        }

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> itemRegistryEvent) {
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.GOLDBRICK, new Item.Properties().group(setup.itemGroup)).setRegistryName("goldbrick"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.GOLDSTAIRS, new Item.Properties().group(setup.itemGroup)).setRegistryName("goldstairs"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.GOLDSLAB, new Item.Properties().group(setup.itemGroup)).setRegistryName("goldslab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.GOLDWALL, new Item.Properties().group(setup.itemGroup)).setRegistryName("goldwall"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.COMPRESSEDCOBBLESTONE, new Item.Properties().group(setup.itemGroup)).setRegistryName("compressedcobblestone"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SILVERORE, new Item.Properties().group(setup.itemGroup)).setRegistryName("silver_ore"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.ROSEQUARTZORE, new Item.Properties().group(setup.itemGroup)).setRegistryName("rose_quartz_ore"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.ROSEQUARTZBLOCK, new Item.Properties().group(setup.itemGroup)).setRegistryName("rose_quartz_block"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDESTONESLAB, new Item.Properties().group(setup.itemGroup)).setRegistryName("side_stone_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDECOBBLESTONESLAB, new Item.Properties().group(setup.itemGroup)).setRegistryName("side_cobblestone_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDEOAKSLAB, new Item.Properties().group(setup.itemGroup)).setRegistryName("side_oak_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDESPRUCESLAB, new Item.Properties().group(setup.itemGroup)).setRegistryName("side_spruce_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDEBIRCHSLAB, new Item.Properties().group(setup.itemGroup)).setRegistryName("side_birch_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDEJUNGLESLAB, new Item.Properties().group(setup.itemGroup)).setRegistryName("side_jungle_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDEACACIASLAB, new Item.Properties().group(setup.itemGroup)).setRegistryName("side_acacia_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDEDARKOAKSLAB, new Item.Properties().group(setup.itemGroup)).setRegistryName("side_dark_oak_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDESAKURASLAB, new Item.Properties().group(setup.itemGroup)).setRegistryName("side_sakura_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDEGOLDSLAB, new Item.Properties().group(setup.itemGroup)).setRegistryName("side_gold_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURALEAVES, new Item.Properties().group(setup.itemGroup)).setRegistryName("sakura_leaves"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURALOG, new Item.Properties().group(setup.itemGroup)).setRegistryName("sakura_log"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURAPLANKS, new Item.Properties().group(setup.itemGroup)).setRegistryName("sakura_planks"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURASAPLING, new Item.Properties().group(setup.itemGroup)).setRegistryName("sakura_sapling"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURASLAB, new Item.Properties().group(setup.itemGroup)).setRegistryName("sakura_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURASTAIRS, new Item.Properties().group(setup.itemGroup)).setRegistryName("sakura_stairs"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURAFENCE, new Item.Properties().group(setup.itemGroup)).setRegistryName("sakura_fence"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURAFENCEGATE, new Item.Properties().group(setup.itemGroup)).setRegistryName("sakura_fence_gate"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURAPRESSUREPLATE, new Item.Properties().group(setup.itemGroup)).setRegistryName("sakura_pressure_plate"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURATRAPDOOR, new Item.Properties().group(setup.itemGroup)).setRegistryName("sakura_trapdoor"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURABUTTON, new Item.Properties().group(setup.itemGroup)).setRegistryName("sakura_button"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURADOOR, new Item.Properties().group(setup.itemGroup)).setRegistryName("sakura_door"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.DISENCHANTMENTTABLE, new Item.Properties().group(setup.itemGroup)).setRegistryName("disenchantment_table"));

            itemRegistryEvent.getRegistry().register(new Juicer().setRegistryName("juicer"));
            itemRegistryEvent.getRegistry().register(new Applejuice().setRegistryName("applejuice"));
            itemRegistryEvent.getRegistry().register(new SweetBerryjuice().setRegistryName("sweetberryjuice"));
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
            tileEntityRegister.getRegistry().register(TileEntityType.Builder.create(DisenchantmentTableTile::new, ModBlocks.DISENCHANTMENTTABLE).build(null).setRegistryName("disenchantment_table"));
            tileEntityRegister.getRegistry().register(TileEntityType.Builder.create(SignTileEntity::new, ModBlocks.SAKURASIGN, ModBlocks.SAKURAWALLSIGN).build(null).setRegistryName("mod_sign"));
        }

        @SubscribeEvent
        public static void registerContainer(final RegistryEvent.Register<ContainerType<?>> containerRegister) {
            containerRegister.getRegistry().register(new ContainerType<>(DisenchantmentTableContainer::new).setRegistryName("disenchantment_table"));
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
