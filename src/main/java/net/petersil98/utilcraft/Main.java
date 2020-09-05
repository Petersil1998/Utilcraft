package net.petersil98.utilcraft;

import com.mojang.datafixers.types.Type;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.command.Commands;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.petersil98.utilcraft.biomes.GraveyardBiome;
import net.petersil98.utilcraft.blocks.*;
import net.petersil98.utilcraft.blocks.sakura.SakuraLeaves;
import net.petersil98.utilcraft.blocks.sakura.SakuraLog;
import net.petersil98.utilcraft.blocks.sakura.SakuraPlanks;
import net.petersil98.utilcraft.blocks.sakura.SakuraSapling;
import net.petersil98.utilcraft.blocks.sideslabs.*;
import net.petersil98.utilcraft.commands.ModCommands;
import net.petersil98.utilcraft.data.tileEntityOwner.CapabilityTileEntityOwner;
import net.petersil98.utilcraft.data.trustedPlayers.CapabilityTrustedPlayers;
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
    }

    private void setup(final FMLCommonSetupEvent event) {
        ScreenManager.registerFactory(ModContainer.DISENCHANTMENTBLOCKCONTAINER, DisenchantmentTableScreen::new);
        RenderTypeLookup.setRenderLayer(ModBlocks.SAKURASAPLING, RenderType.getCutout());

        CapabilityTileEntityOwner.register();
        CapabilityTrustedPlayers.register();

        DeferredWorkQueue.runLater(() -> {
            for (Biome biome : ForgeRegistries.BIOMES) {
                WorldGeneration.addSilverOre(biome);
                WorldGeneration.addRoseQuartzOre(biome);
                WorldGeneration.addSakuraTrees(biome);
            }
        });

        setup.init();
        proxy.init();
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> blockRegistryEvent) {
            blockRegistryEvent.getRegistry().register(new MyBlock().setRegistryName("myblock"));
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
            blockRegistryEvent.getRegistry().register(new SideGoldSlab().setRegistryName("side_gold_slab"));
            blockRegistryEvent.getRegistry().register(new SakuraLeaves().setRegistryName("sakura_leaves"));
            blockRegistryEvent.getRegistry().register(new SakuraLog().setRegistryName("sakura_log"));
            blockRegistryEvent.getRegistry().register(new SakuraPlanks().setRegistryName("sakura_planks"));
            blockRegistryEvent.getRegistry().register(new SakuraSapling().setRegistryName("sakura_sapling"));
            blockRegistryEvent.getRegistry().register(new DisenchantmentTable().setRegistryName("disenchantment_table"));
        }

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> itemRegistryEvent) {
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.MYBLOCK, new Item.Properties().group(setup.itemGroup)).setRegistryName("myblock"));
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
            itemRegistryEvent.getRegistry().register(new BlockItem(ModSlabs.SIDEGOLDSLAB, new Item.Properties().group(setup.itemGroup)).setRegistryName("side_gold_slab"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURALEAVES, new Item.Properties().group(setup.itemGroup)).setRegistryName("sakura_leaves"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURALOG, new Item.Properties().group(setup.itemGroup)).setRegistryName("sakura_log"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURAPLANKS, new Item.Properties().group(setup.itemGroup)).setRegistryName("sakura_planks"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAKURASAPLING, new Item.Properties().group(setup.itemGroup)).setRegistryName("sakura_sapling"));
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
        }

        @SubscribeEvent
        public static void registerBiomes(final RegistryEvent.Register<Biome> event){
            event.getRegistry().register(new GraveyardBiome().setRegistryName("graveyard"));
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
            Type<?> type = Util.attemptDataFix(TypeReferences.BLOCK_ENTITY, "disenchantment_table");
            tileEntityRegister.getRegistry().register(TileEntityType.Builder.create(DisenchantmentTableTile::new, ModBlocks.DISENCHANTMENTTABLE).build(type).setRegistryName("disenchantment_table"));
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
    }
}
