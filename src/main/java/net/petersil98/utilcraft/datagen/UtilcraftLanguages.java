package net.petersil98.utilcraft.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.blocks.sideslabs.UtilcraftSideSlabs;
import net.petersil98.utilcraft.enchantments.UtilcraftEnchantments;
import net.petersil98.utilcraft.food.UtilcraftFoods;
import net.petersil98.utilcraft.items.UtilcraftItems;

public class UtilcraftLanguages {

    public static English getEnglish(DataGenerator generator){
        return new English(generator);
    }

    public static German getGerman(DataGenerator generator){
        return new German(generator);
    }

    private static class English extends LanguageProvider {

        public English(DataGenerator generator) {
            super(generator, Utilcraft.MOD_ID, "en_us");
        }

        @Override
        protected void addTranslations() {
            add(UtilcraftBlocks.GOLD_BRICK, "Gold Brick");
            add(UtilcraftBlocks.GOLD_STAIRS, "Gold Stairs");
            add(UtilcraftBlocks.GOLD_SLAB, "Gold Slab");
            add(UtilcraftBlocks.GOLD_WALL, "Gold Wall");
            add(UtilcraftBlocks.COMPRESSED_COBBLESTONE, "Compressed Cobblestone");
            add(UtilcraftBlocks.SILVER_ORE, "Silver Ore");
            add(UtilcraftBlocks.ROSE_QUARTZ_ORE, "Rose Quartz Ore");
            add(UtilcraftBlocks.ROSE_QUARTZ_BLOCK, "Rose Quartz Block");
            add(UtilcraftSideSlabs.SIDE_STONE_SLAB, "Side Stone Slab");
            add(UtilcraftSideSlabs.SIDE_COBBLESTONE_SLAB, "Side Cobblestone Slab");
            add(UtilcraftSideSlabs.SIDE_OAK_SLAB, "Side Oak Slab");
            add(UtilcraftSideSlabs.SIDE_SPRUCE_SLAB, "Side Spruce Slab");
            add(UtilcraftSideSlabs.SIDE_BIRCH_SLAB, "Side Birch Slab");
            add(UtilcraftSideSlabs.SIDE_JUNGLE_SLAB, "Side Jungle Slab");
            add(UtilcraftSideSlabs.SIDE_ACACIA_SLAB, "Side Acacia Slab");
            add(UtilcraftSideSlabs.SIDE_DARK_OAK_SLAB, "Side Dark Oak Slab");
            add(UtilcraftSideSlabs.SIDE_SAKURA_SLAB, "Side Sakura Slab");
            add(UtilcraftSideSlabs.SIDE_GOLD_SLAB, "Side Gold Slab");
            add(UtilcraftBlocks.SAKURA_LEAVES, "Sakura Leaves");
            add(UtilcraftBlocks.SAKURA_LOG, "Sakura Log");
            add(UtilcraftBlocks.SAKURA_PLANKS, "Sakura Planks");
            add(UtilcraftBlocks.SAKURA_SAPLING, "Sakura Sapling");
            add(UtilcraftBlocks.SAKURA_SLAB, "Sakura Slab");
            add(UtilcraftBlocks.SAKURA_STAIRS, "Sakura Stairs");
            add(UtilcraftBlocks.SAKURA_FENCE, "Sakura Fence");
            add(UtilcraftBlocks.SAKURA_FENCE_GATE, "Sakura Fence Gate");
            add(UtilcraftBlocks.SAKURA_PRESSURE_PLATE, "Sakura Pressure Plate");
            add(UtilcraftBlocks.SAKURA_TRAPDOOR, "Sakura Trapdoor");
            add(UtilcraftBlocks.SAKURA_SIGN, "Sakura Sign");
            add(UtilcraftBlocks.SAKURA_BUTTON, "Sakura Button");
            add(UtilcraftBlocks.SAKURA_DOOR, "Sakura Door");
            add(UtilcraftBlocks.DISENCHANTMENT_TABLE, "Disenchantment Table");
            add(UtilcraftBlocks.SECURE_CHEST, "Secure Chest");
            add(UtilcraftBlocks.REDSTONE_STAIRS, "Redstone Stairs");
            add(UtilcraftBlocks.REDSTONE_SLAB, "Redstone Slab");
            add(UtilcraftSideSlabs.SIDE_REDSTONE_SLAB, "Side Redstone Slab");
            add(UtilcraftBlocks.SUSHI_MAKER, "Sushi Maker");

            add(UtilcraftItems.JUICER, "Juicer");
            add(UtilcraftFoods.APPLE_JUICE, "Apple Juice");
            add(UtilcraftFoods.SWEET_BERRY_JUICE, "Sweet Berry Juice");
            add(UtilcraftItems.FLOUR, "Flour");
            add(UtilcraftFoods.BAGUETTE, "Baguette");
            add(UtilcraftItems.ROSE_QUARTZ, "Rose Quartz");
            add(UtilcraftItems.ROSE_QUARTZ_HELMET, "Rose Quartz Helmet");
            add(UtilcraftItems.ROSE_QUARTZ_CHESTPLATE, "Rose Quartz Chestplate");
            add(UtilcraftItems.ROSE_QUARTZ_LEGGINGS, "Rose Quartz Leggings");
            add(UtilcraftItems.ROSE_QUARTZ_BOOTS, "Rose Quartz Boots");
            add(UtilcraftItems.ROSE_QUARTZ_PICKAXE, "Rose Quartz Pickaxe");
            add(UtilcraftItems.ROSE_QUARTZ_SHOVEL, "Rose Quartz Shovel");
            add(UtilcraftItems.ROSE_QUARTZ_HOE, "Rose Quartz Hoe");
            add(UtilcraftItems.ROSE_QUARTZ_SWORD, "Rose Quartz Sword");
            add(UtilcraftItems.ROSE_QUARTZ_AXE, "Rose Quartz Axe");
            add(UtilcraftItems.ROSE_QUARTZ_SUPER_HAMMER, "Rose Quartz Super Hammer");
            add(UtilcraftItems.ROSE_QUARTZ_SUPER_SHOVEL, "Rose Quartz Super Shovel");
            add(UtilcraftItems.TRAVELERS_BACKPACK, "Travelers Backpack");
            add(UtilcraftItems.TNT_FINDER, "TNT Finder");
            add(UtilcraftItems.SILVER_INGOT, "Silver Ingot");
            add(UtilcraftItems.BUTCHERS_KNIFE, "Butcher's Knife");

            add(UtilcraftEnchantments.BEHEADING, "Beheading");

            add("itemGroup.utilcraft", "Utilcraft");

            add("advancements.utilcraft.root.title", "Green Thumb");
            add("advancements.utilcraft.root.description", "Place a Sakura Sapling");

            add("advancements.utilcraft.mine_rose_quartz.title", "Mine Rose Quartz");
            add("advancements.utilcraft.mine_rose_quartz.description", "Find and mine some Rose Quartz");

            add("screen.utilcraft.disenchantment_table", "Disenchantment Table");
            add("screen.utilcraft.secure_chest", "Secure Chest");

            add("protection.utilcraft.block_protected", "This block is protected, you cannot break or use it!");
            add("player_trusted.utilcraft.player_added", "Player %s can now access your Secure Chests");
            add("player_trusted.utilcraft.player_removed", "Player %s can no longer access your Secure Chests");
            add("player_trusted.utilcraft.list", "You trust the following Players: %s");

            add("vein_miner.utilcraft.active", "Vein Miner active");
            add("vein_miner.utilcraft.inactive", "Vein Miner inactive");

            add("home.utilcraft.set_home", "Successfully set home to current location!");
            add("home.utilcraft.not_set", "Home wasn't set yet!");
            add("home.utilcraft.teleported", "Welcome Home!");

            add("key_bindings.utilcraft.category", "Utilcraft");
            add("key_bindings.utilcraft.vein_miner", "Toggle Vein Miner");
        }
    }

    private static class German extends LanguageProvider {

        public German(DataGenerator generator) {
            super(generator, Utilcraft.MOD_ID, "de_de");
        }

        @Override
        protected void addTranslations() {
            add(UtilcraftBlocks.GOLD_BRICK, "Gold Ziegel");
            add(UtilcraftBlocks.GOLD_STAIRS, "Gold Treppen");
            add(UtilcraftBlocks.GOLD_SLAB, "Gold Slab");
            add(UtilcraftBlocks.GOLD_WALL, "Gold Wall");
            add(UtilcraftBlocks.COMPRESSED_COBBLESTONE, "Compressed Cobblestone");
            add(UtilcraftBlocks.SILVER_ORE, "Silver Ore");
            add(UtilcraftBlocks.ROSE_QUARTZ_ORE, "Rose Quartz Ore");
            add(UtilcraftBlocks.ROSE_QUARTZ_BLOCK, "Rose Quartz Block");
            add(UtilcraftSideSlabs.SIDE_STONE_SLAB, "Side Stone Slab");
            add(UtilcraftSideSlabs.SIDE_COBBLESTONE_SLAB, "Side Cobblestone Slab");
            add(UtilcraftSideSlabs.SIDE_OAK_SLAB, "Side Oak Slab");
            add(UtilcraftSideSlabs.SIDE_SPRUCE_SLAB, "Side Spruce Slab");
            add(UtilcraftSideSlabs.SIDE_BIRCH_SLAB, "Side Birch Slab");
            add(UtilcraftSideSlabs.SIDE_JUNGLE_SLAB, "Side Jungle Slab");
            add(UtilcraftSideSlabs.SIDE_ACACIA_SLAB, "Side Acacia Slab");
            add(UtilcraftSideSlabs.SIDE_DARK_OAK_SLAB, "Side Dark Oak Slab");
            add(UtilcraftSideSlabs.SIDE_SAKURA_SLAB, "Side Sakura Slab");
            add(UtilcraftSideSlabs.SIDE_GOLD_SLAB, "Side Gold Slab");
            add(UtilcraftBlocks.SAKURA_LEAVES, "Sakura Leaves");
            add(UtilcraftBlocks.SAKURA_LOG, "Sakura Log");
            add(UtilcraftBlocks.SAKURA_PLANKS, "Sakura Planks");
            add(UtilcraftBlocks.SAKURA_SAPLING, "Sakura Sapling");
            add(UtilcraftBlocks.SAKURA_SLAB, "Sakura Slab");
            add(UtilcraftBlocks.SAKURA_STAIRS, "Sakura Stairs");
            add(UtilcraftBlocks.SAKURA_FENCE, "Sakura Fence");
            add(UtilcraftBlocks.SAKURA_FENCE_GATE, "Sakura Fence Gate");
            add(UtilcraftBlocks.SAKURA_PRESSURE_PLATE, "Sakura Pressure Plate");
            add(UtilcraftBlocks.SAKURA_TRAPDOOR, "Sakura Trapdoor");
            add(UtilcraftBlocks.SAKURA_SIGN, "Sakura Sign");
            add(UtilcraftBlocks.SAKURA_BUTTON, "Sakura Button");
            add(UtilcraftBlocks.SAKURA_DOOR, "Sakura Door");
            add(UtilcraftBlocks.DISENCHANTMENT_TABLE, "Disenchantment Table");
            add(UtilcraftBlocks.SECURE_CHEST, "Secure Chest");
            add(UtilcraftBlocks.REDSTONE_STAIRS, "Redstone Stairs");
            add(UtilcraftBlocks.REDSTONE_SLAB, "Redstone Slab");
            add(UtilcraftSideSlabs.SIDE_REDSTONE_SLAB, "Side Redstone Slab");
            add(UtilcraftBlocks.SUSHI_MAKER, "Sushi Maker");

            add(UtilcraftItems.JUICER, "Juicer");
            add(UtilcraftFoods.APPLE_JUICE, "Juicer");
            add(UtilcraftFoods.SWEET_BERRY_JUICE, "Juicer");
            add(UtilcraftItems.FLOUR, "Juicer");
            add(UtilcraftFoods.BAGUETTE, "Juicer");
            add(UtilcraftItems.ROSE_QUARTZ, "Juicer");
            add(UtilcraftItems.ROSE_QUARTZ_HELMET, "Juicer");
            add(UtilcraftItems.ROSE_QUARTZ_CHESTPLATE, "Juicer");
            add(UtilcraftItems.ROSE_QUARTZ_LEGGINGS, "Juicer");
            add(UtilcraftItems.ROSE_QUARTZ_BOOTS, "Juicer");
            add(UtilcraftItems.ROSE_QUARTZ_PICKAXE, "Juicer");
            add(UtilcraftItems.ROSE_QUARTZ_SHOVEL, "Juicer");
            add(UtilcraftItems.ROSE_QUARTZ_HOE, "Juicer");
            add(UtilcraftItems.ROSE_QUARTZ_SWORD, "Juicer");
            add(UtilcraftItems.ROSE_QUARTZ_AXE, "Juicer");
            add(UtilcraftItems.ROSE_QUARTZ_SUPER_HAMMER, "Juicer");
            add(UtilcraftItems.ROSE_QUARTZ_SUPER_SHOVEL, "Juicer");
            add(UtilcraftItems.TRAVELERS_BACKPACK, "Juicer");
            add(UtilcraftItems.TNT_FINDER, "Juicer");
            add(UtilcraftItems.SILVER_INGOT, "Juicer");
            add(UtilcraftItems.BUTCHERS_KNIFE, "Butcher's Knife");

            add(UtilcraftEnchantments.BEHEADING, "Beheading");

            add("itemGroup.utilcraft", "Utilcraft");

            add("advancements.utilcraft.root.title", "Green Thumb");
            add("advancements.utilcraft.root.description", "Place a Sakura Sapling");

            add("advancements.utilcraft.mine_rose_quartz.title", "Mine Rose Quartz");
            add("advancements.utilcraft.mine_rose_quartz.description", "Find and mine some Rose Quartz");

            add("screen.utilcraft.disenchantment_table", "Disenchantment Table");
            add("screen.utilcraft.secure_chest", "Secure Chest");

            add("protection.utilcraft.block_protected", "This block is protected, you cannot break or use it!");
            add("player_trusted.utilcraft.player_added", "Player %s can now access your Secure Chests");
            add("player_trusted.utilcraft.player_removed", "Player %s can no longer access your Secure Chests");
            add("player_trusted.utilcraft.list", "You trust the following Players: %s");

            add("vein_miner.utilcraft.active", "Vein Miner active");
            add("vein_miner.utilcraft.inactive", "Vein Miner inactive");

            add("home.utilcraft.set_home", "Successfully set home to current location!");
            add("home.utilcraft.not_set", "Home wasn't set yet!");
            add("home.utilcraft.teleported", "Welcome Home!");

            add("key_bindings.utilcraft.category", "Utilcraft");
            add("key_bindings.utilcraft.vein_miner", "Toggle Vein Miner");
        }
    }
}
