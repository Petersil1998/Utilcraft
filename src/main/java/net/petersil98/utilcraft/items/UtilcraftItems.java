package net.petersil98.utilcraft.items;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.items.food.AppleJuice;
import net.petersil98.utilcraft.items.food.Baguette;
import net.petersil98.utilcraft.items.food.SweetBerryJuice;
import net.petersil98.utilcraft.render.SecureChestItemTileEntityRenderer;

public class UtilcraftItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Utilcraft.MOD_ID);

    private static final SecureChestItemTileEntityRenderer SECURE_CHEST_ITEM_TILE_ENTITY_RENDERER = new SecureChestItemTileEntityRenderer();

    public static final RegistryObject<BlockItem> GOLD_BRICK = ITEMS.register("gold_brick", () -> new BlockItem(UtilcraftBlocks.GOLD_BRICK.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> GOLD_STAIRS = ITEMS.register("gold_stairs", () -> new BlockItem(UtilcraftBlocks.GOLD_STAIRS.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> GOLD_SLAB = ITEMS.register("gold_slab", () -> new BlockItem(UtilcraftBlocks.GOLD_SLAB.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> GOLD_WALL = ITEMS.register("gold_wall", () -> new BlockItem(UtilcraftBlocks.GOLD_WALL.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> COMPRESSED_COBBLESTONE = ITEMS.register("compressed_cobblestone", () -> new BlockItem(UtilcraftBlocks.COMPRESSED_COBBLESTONE.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SILVER_ORE = ITEMS.register("silver_ore", () -> new BlockItem(UtilcraftBlocks.SILVER_ORE.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> ROSE_QUARTZ_ORE = ITEMS.register("rose_quartz_ore", () -> new BlockItem(UtilcraftBlocks.ROSE_QUARTZ_ORE.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> ROSE_QUARTZ_BLOCK = ITEMS.register("rose_quartz_block", () -> new BlockItem(UtilcraftBlocks.ROSE_QUARTZ_BLOCK.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SIDE_STONE_SLAB = ITEMS.register("side_stone_slab", () -> new BlockItem(UtilcraftBlocks.SIDE_STONE_SLAB.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SIDE_COBBLESTONE_SLAB = ITEMS.register("side_cobblestone_slab", () -> new BlockItem(UtilcraftBlocks.SIDE_COBBLESTONE_SLAB.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SIDE_OAK_SLAB = ITEMS.register("side_oak_slab", () -> new BlockItem(UtilcraftBlocks.SIDE_OAK_SLAB.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SIDE_SPRUCE_SLAB = ITEMS.register("side_spruce_slab", () -> new BlockItem(UtilcraftBlocks.SIDE_SPRUCE_SLAB.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SIDE_BIRCH_SLAB = ITEMS.register("side_birch_slab", () -> new BlockItem(UtilcraftBlocks.SIDE_BIRCH_SLAB.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SIDE_JUNGLE_SLAB = ITEMS.register("side_jungle_slab", () -> new BlockItem(UtilcraftBlocks.SIDE_JUNGLE_SLAB.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SIDE_ACACIA_SLAB = ITEMS.register("side_acacia_slab", () -> new BlockItem(UtilcraftBlocks.SIDE_ACACIA_SLAB.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SIDE_DARK_OAK_SLAB = ITEMS.register("side_dark_oak_slab", () -> new BlockItem(UtilcraftBlocks.SIDE_DARK_OAK_SLAB.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SIDE_SAKURA_SLAB = ITEMS.register("side_sakura_slab", () -> new BlockItem(UtilcraftBlocks.SIDE_SAKURA_SLAB.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SIDE_GOLD_SLAB = ITEMS.register("side_gold_slab", () -> new BlockItem(UtilcraftBlocks.SIDE_GOLD_SLAB.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SIDE_REDSTONE_SLAB = ITEMS.register("side_redstone_slab", () -> new BlockItem(UtilcraftBlocks.SIDE_REDSTONE_SLAB.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SAKURA_LEAVES = ITEMS.register("sakura_leaves", () -> new BlockItem(UtilcraftBlocks.SAKURA_LEAVES.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SAKURA_LOG = ITEMS.register("sakura_log", () -> new BlockItem(UtilcraftBlocks.SAKURA_LOG.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SAKURA_PLANKS = ITEMS.register("sakura_planks", () -> new BlockItem(UtilcraftBlocks.SAKURA_PLANKS.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SAKURA_SAPLING = ITEMS.register("sakura_sapling", () -> new BlockItem(UtilcraftBlocks.SAKURA_SAPLING.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SAKURA_SLAB = ITEMS.register("sakura_slab", () -> new BlockItem(UtilcraftBlocks.SAKURA_SLAB.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SAKURA_STAIRS = ITEMS.register("sakura_stairs", () -> new BlockItem(UtilcraftBlocks.SAKURA_STAIRS.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SAKURA_FENCE = ITEMS.register("sakura_fence", () -> new BlockItem(UtilcraftBlocks.SAKURA_FENCE.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SAKURA_FENCE_GATE = ITEMS.register("sakura_fence_gate", () -> new BlockItem(UtilcraftBlocks.SAKURA_FENCE_GATE.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SAKURA_PRESSURE_PLATE = ITEMS.register("sakura_pressure_plate", () -> new BlockItem(UtilcraftBlocks.SAKURA_PRESSURE_PLATE.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SAKURA_TRAPDOOR = ITEMS.register("sakura_trapdoor", () -> new BlockItem(UtilcraftBlocks.SAKURA_TRAPDOOR.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SAKURA_BUTTON = ITEMS.register("sakura_button", () -> new BlockItem(UtilcraftBlocks.SAKURA_BUTTON.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SAKURA_DOOR = ITEMS.register("sakura_door", () -> new BlockItem(UtilcraftBlocks.SAKURA_DOOR.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> DISENCHANTMENT_TABLE = ITEMS.register("disenchantment_table", () -> new BlockItem(UtilcraftBlocks.DISENCHANTMENT_TABLE.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SECURE_CHEST = ITEMS.register("secure_chest", () -> new BlockItem(UtilcraftBlocks.SECURE_CHEST.get(), new Item.Properties().setISTER(() -> () -> SECURE_CHEST_ITEM_TILE_ENTITY_RENDERER).tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> REDSTONE_STAIRS = ITEMS.register("redstone_stairs", () -> new BlockItem(UtilcraftBlocks.REDSTONE_STAIRS.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> REDSTONE_SLAB = ITEMS.register("redstone_slab", () -> new BlockItem(UtilcraftBlocks.REDSTONE_SLAB.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SUSHI_MAKER = ITEMS.register("sushi_maker", () -> new BlockItem(UtilcraftBlocks.SUSHI_MAKER.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> GLASS_STAIRS = ITEMS.register("glass_stairs", () -> new BlockItem(UtilcraftBlocks.GLASS_STAIRS.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> SILVER_BLOCK = ITEMS.register("silver_block", () -> new BlockItem(UtilcraftBlocks.SILVER_BLOCK.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> CHUNK_LOADER = ITEMS.register("chunk_loader", () -> new BlockItem(UtilcraftBlocks.CHUNK_LOADER.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<BlockItem> ENTROPY_TABLE = ITEMS.register("entropy_table", () -> new BlockItem(UtilcraftBlocks.ENTROPY_TABLE.get(), new Item.Properties().tab(Utilcraft.ITEM_GROUP)));

    public static final RegistryObject<Juicer> JUICER = ITEMS.register("juicer", () -> new Juicer(new Item.Properties().tab(Utilcraft.ITEM_GROUP).stacksTo(1)));
    public static final RegistryObject<AppleJuice> APPLE_JUICE = ITEMS.register("apple_juice", () -> new AppleJuice(new Item.Properties().food(new Food.Builder().nutrition(8).fast().saturationMod(3).build()).tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<SweetBerryJuice> SWEET_BERRY_JUICE = ITEMS.register("sweet_berry_juice", () -> new SweetBerryJuice(new Item.Properties().food(new Food.Builder().nutrition(4).fast().saturationMod(0.8f).build()).tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<Flour> FLOUR = ITEMS.register("flour", () -> new Flour(new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<Baguette> BAGUETTE = ITEMS.register("baguette", () -> new Baguette(new Item.Properties().food(new Food.Builder().nutrition(6).build()).tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<RoseQuartz> ROSE_QUARTZ = ITEMS.register("rose_quartz", () -> new RoseQuartz(new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<RoseQuartzHelmet> ROSE_QUARTZ_HELMET = ITEMS.register("rose_quartz_helmet", () -> new RoseQuartzHelmet(new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<RoseQuartzChestplate> ROSE_QUARTZ_CHESTPLATE = ITEMS.register("rose_quartz_chestplate", () -> new RoseQuartzChestplate(new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<RoseQuartzLeggings> ROSE_QUARTZ_LEGGINGS = ITEMS.register("rose_quartz_leggings", () -> new RoseQuartzLeggings(new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<RoseQuartzBoots> ROSE_QUARTZ_BOOTS = ITEMS.register("rose_quartz_boots", () -> new RoseQuartzBoots(new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<RoseQuartzPickaxe> ROSE_QUARTZ_PICKAXE = ITEMS.register("rose_quartz_pickaxe", () -> new RoseQuartzPickaxe(new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<RoseQuartzSword> ROSE_QUARTZ_SWORD = ITEMS.register("rose_quartz_sword", () -> new RoseQuartzSword(new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<RoseQuartzShovel> ROSE_QUARTZ_SHOVEL = ITEMS.register("rose_quartz_shovel", () -> new RoseQuartzShovel(new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<RoseQuartzHoe> ROSE_QUARTZ_HOE = ITEMS.register("rose_quartz_hoe", () -> new RoseQuartzHoe(new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<RoseQuartzAxe> ROSE_QUARTZ_AXE = ITEMS.register("rose_quartz_axe", () -> new RoseQuartzAxe(new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<RoseQuartzSuperHammer> ROSE_QUARTZ_SUPER_HAMMER = ITEMS.register("rose_quartz_super_hammer", () -> new RoseQuartzSuperHammer(new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<RoseQuartzSuperShovel> ROSE_QUARTZ_SUPER_SHOVEL = ITEMS.register("rose_quartz_super_shovel", () -> new RoseQuartzSuperShovel(new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<SakuraSignItem> SAKURA_SIGN = ITEMS.register("sakura_sign", () -> new SakuraSignItem(new Item.Properties().stacksTo(16).tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<TravelersBackpack> TRAVELERS_BACKPACK = ITEMS.register("travelers_backpack", () -> new TravelersBackpack(new Item.Properties().tab(Utilcraft.ITEM_GROUP).stacksTo(1)));
    public static final RegistryObject<TNTFinder> TNT_FINDER = ITEMS.register("tnt_finder", () -> new TNTFinder(new Item.Properties().tab(Utilcraft.ITEM_GROUP).stacksTo(1)));
    public static final RegistryObject<SilverIngot> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new SilverIngot(new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<ButchersKnife> BUTCHERS_KNIFE = ITEMS.register("butchers_knife", () -> new ButchersKnife(new Item.Properties().tab(Utilcraft.ITEM_GROUP)));
    public static final RegistryObject<SpawnerItem> SPAWNER_ITEM = ITEMS.register("spawner_item", () -> new SpawnerItem(new Item.Properties()));
}
