package net.petersil98.utilcraft.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.blocks.ModBlocks;
import net.petersil98.utilcraft.blocks.sideslabs.ModSideSlabs;
import net.petersil98.utilcraft.enchantments.ModEnchantments;
import net.petersil98.utilcraft.food.ModFoods;
import net.petersil98.utilcraft.items.ModItems;

public class Languages {

    public static English getEnglish(DataGenerator generator){
        return new English(generator);
    }

    public static German getGerman(DataGenerator generator){
        return new German(generator);
    }

    private static class English extends LanguageProvider {

        public English(DataGenerator generator) {
            super(generator, Main.MOD_ID, "en_us");
        }

        @Override
        protected void addTranslations() {
            add(ModBlocks.GOLD_BRICK, "Gold Brick");
            add(ModBlocks.GOLD_STAIRS, "Gold Stairs");
            add(ModBlocks.GOLD_SLAB, "Gold Slab");
            add(ModBlocks.GOLD_WALL, "Gold Wall");
            add(ModBlocks.COMPRESSED_COBBLESTONE, "Compressed Cobblestone");
            add(ModBlocks.SILVER_ORE, "Silver Ore");
            add(ModBlocks.ROSE_QUARTZ_ORE, "Rose Quartz Ore");
            add(ModBlocks.ROSE_QUARTZ_BLOCK, "Rose Quartz Block");
            add(ModSideSlabs.SIDE_STONE_SLAB, "Side Stone Slab");
            add(ModSideSlabs.SIDE_COBBLESTONE_SLAB, "Side Cobblestone Slab");
            add(ModSideSlabs.SIDE_OAK_SLAB, "Side Oak Slab");
            add(ModSideSlabs.SIDE_SPRUCE_SLAB, "Side Spruce Slab");
            add(ModSideSlabs.SIDE_BIRCH_SLAB, "Side Birch Slab");
            add(ModSideSlabs.SIDE_JUNGLE_SLAB, "Side Jungle Slab");
            add(ModSideSlabs.SIDE_ACACIA_SLAB, "Side Acacia Slab");
            add(ModSideSlabs.SIDE_DARK_OAK_SLAB, "Side Dark Oak Slab");
            add(ModSideSlabs.SIDE_SAKURA_SLAB, "Side Sakura Slab");
            add(ModSideSlabs.SIDE_GOLD_SLAB, "Side Gold Slab");
            add(ModBlocks.SAKURA_LEAVES, "Sakura Leaves");
            add(ModBlocks.SAKURA_LOG, "Sakura Log");
            add(ModBlocks.SAKURA_PLANKS, "Sakura Planks");
            add(ModBlocks.SAKURA_SAPLING, "Sakura Sapling");
            add(ModBlocks.SAKURA_SLAB, "Sakura Slab");
            add(ModBlocks.SAKURA_STAIRS, "Sakura Stairs");
            add(ModBlocks.SAKURA_FENCE, "Sakura Fence");
            add(ModBlocks.SAKURA_FENCE_GATE, "Sakura Fence Gate");
            add(ModBlocks.SAKURA_PRESSURE_PLATE, "Sakura Pressure Plate");
            add(ModBlocks.SAKURA_TRAPDOOR, "Sakura Trapdoor");
            add(ModBlocks.SAKURA_SIGN, "Sakura Sign");
            add(ModBlocks.SAKURA_BUTTON, "Sakura Button");
            add(ModBlocks.SAKURA_DOOR, "Sakura Door");
            add(ModBlocks.DISENCHANTMENT_TABLE, "Disenchantment Table");
            add(ModBlocks.SECURE_CHEST, "Secure Chest");
            add(ModBlocks.REDSTONE_STAIRS, "Redstone Stairs");
            add(ModBlocks.REDSTONE_SLAB, "Redstone Slab");
            add(ModSideSlabs.SIDE_REDSTONE_SLAB, "Side Redstone Slab");
            add(ModBlocks.SUSHI_MAKER, "Sushi Maker");

            add(ModItems.JUICER, "Juicer");
            add(ModFoods.APPLE_JUICE, "Apple Juice");
            add(ModFoods.SWEET_BERRY_JUICE, "Sweet Berry Juice");
            add(ModItems.FLOUR, "Flour");
            add(ModFoods.BAGUETTE, "Baguette");
            add(ModItems.ROSE_QUARTZ, "Rose Quartz");
            add(ModItems.ROSE_QUARTZ_HELMET, "Rose Quartz Helmet");
            add(ModItems.ROSE_QUARTZ_CHESTPLATE, "Rose Quartz Chestplate");
            add(ModItems.ROSE_QUARTZ_LEGGINGS, "Rose Quartz Leggings");
            add(ModItems.ROSE_QUARTZ_BOOTS, "Rose Quartz Boots");
            add(ModItems.ROSE_QUARTZ_PICKAXE, "Rose Quartz Pickaxe");
            add(ModItems.ROSE_QUARTZ_SHOVEL, "Rose Quartz Shovel");
            add(ModItems.ROSE_QUARTZ_HOE, "Rose Quartz Hoe");
            add(ModItems.ROSE_QUARTZ_SWORD, "Rose Quartz Sword");
            add(ModItems.ROSE_QUARTZ_AXE, "Rose Quartz Axe");
            add(ModItems.ROSE_QUARTZ_SUPER_HAMMER, "Rose Quartz Super Hammer");
            add(ModItems.ROSE_QUARTZ_SUPER_SHOVEL, "Rose Quartz Super Shovel");
            add(ModItems.TRAVELERS_BACKPACK, "Travelers Backpack");
            add(ModItems.TNT_FINDER, "TNT Finder");
            add(ModItems.SILVER_INGOT, "Silver Ingot");
            add(ModItems.BUTCHERS_KNIFE, "Butcher's Knife");

            add(ModEnchantments.BEHEADING, "Beheading");

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
            super(generator, Main.MOD_ID, "de_de");
        }

        @Override
        protected void addTranslations() {
            add(ModBlocks.GOLD_BRICK, "Gold Ziegel");
            add(ModBlocks.GOLD_STAIRS, "Gold Treppen");
            add(ModBlocks.GOLD_SLAB, "Gold Slab");
            add(ModBlocks.GOLD_WALL, "Gold Wall");
            add(ModBlocks.COMPRESSED_COBBLESTONE, "Compressed Cobblestone");
            add(ModBlocks.SILVER_ORE, "Silver Ore");
            add(ModBlocks.ROSE_QUARTZ_ORE, "Rose Quartz Ore");
            add(ModBlocks.ROSE_QUARTZ_BLOCK, "Rose Quartz Block");
            add(ModSideSlabs.SIDE_STONE_SLAB, "Side Stone Slab");
            add(ModSideSlabs.SIDE_COBBLESTONE_SLAB, "Side Cobblestone Slab");
            add(ModSideSlabs.SIDE_OAK_SLAB, "Side Oak Slab");
            add(ModSideSlabs.SIDE_SPRUCE_SLAB, "Side Spruce Slab");
            add(ModSideSlabs.SIDE_BIRCH_SLAB, "Side Birch Slab");
            add(ModSideSlabs.SIDE_JUNGLE_SLAB, "Side Jungle Slab");
            add(ModSideSlabs.SIDE_ACACIA_SLAB, "Side Acacia Slab");
            add(ModSideSlabs.SIDE_DARK_OAK_SLAB, "Side Dark Oak Slab");
            add(ModSideSlabs.SIDE_SAKURA_SLAB, "Side Sakura Slab");
            add(ModSideSlabs.SIDE_GOLD_SLAB, "Side Gold Slab");
            add(ModBlocks.SAKURA_LEAVES, "Sakura Leaves");
            add(ModBlocks.SAKURA_LOG, "Sakura Log");
            add(ModBlocks.SAKURA_PLANKS, "Sakura Planks");
            add(ModBlocks.SAKURA_SAPLING, "Sakura Sapling");
            add(ModBlocks.SAKURA_SLAB, "Sakura Slab");
            add(ModBlocks.SAKURA_STAIRS, "Sakura Stairs");
            add(ModBlocks.SAKURA_FENCE, "Sakura Fence");
            add(ModBlocks.SAKURA_FENCE_GATE, "Sakura Fence Gate");
            add(ModBlocks.SAKURA_PRESSURE_PLATE, "Sakura Pressure Plate");
            add(ModBlocks.SAKURA_TRAPDOOR, "Sakura Trapdoor");
            add(ModBlocks.SAKURA_SIGN, "Sakura Sign");
            add(ModBlocks.SAKURA_BUTTON, "Sakura Button");
            add(ModBlocks.SAKURA_DOOR, "Sakura Door");
            add(ModBlocks.DISENCHANTMENT_TABLE, "Disenchantment Table");
            add(ModBlocks.SECURE_CHEST, "Secure Chest");
            add(ModBlocks.REDSTONE_STAIRS, "Redstone Stairs");
            add(ModBlocks.REDSTONE_SLAB, "Redstone Slab");
            add(ModSideSlabs.SIDE_REDSTONE_SLAB, "Side Redstone Slab");
            add(ModBlocks.SUSHI_MAKER, "Sushi Maker");

            add(ModItems.JUICER, "Juicer");
            add(ModFoods.APPLE_JUICE, "Juicer");
            add(ModFoods.SWEET_BERRY_JUICE, "Juicer");
            add(ModItems.FLOUR, "Juicer");
            add(ModFoods.BAGUETTE, "Juicer");
            add(ModItems.ROSE_QUARTZ, "Juicer");
            add(ModItems.ROSE_QUARTZ_HELMET, "Juicer");
            add(ModItems.ROSE_QUARTZ_CHESTPLATE, "Juicer");
            add(ModItems.ROSE_QUARTZ_LEGGINGS, "Juicer");
            add(ModItems.ROSE_QUARTZ_BOOTS, "Juicer");
            add(ModItems.ROSE_QUARTZ_PICKAXE, "Juicer");
            add(ModItems.ROSE_QUARTZ_SHOVEL, "Juicer");
            add(ModItems.ROSE_QUARTZ_HOE, "Juicer");
            add(ModItems.ROSE_QUARTZ_SWORD, "Juicer");
            add(ModItems.ROSE_QUARTZ_AXE, "Juicer");
            add(ModItems.ROSE_QUARTZ_SUPER_HAMMER, "Juicer");
            add(ModItems.ROSE_QUARTZ_SUPER_SHOVEL, "Juicer");
            add(ModItems.TRAVELERS_BACKPACK, "Juicer");
            add(ModItems.TNT_FINDER, "Juicer");
            add(ModItems.SILVER_INGOT, "Juicer");
            add(ModItems.BUTCHERS_KNIFE, "Butcher's Knife");

            add(ModEnchantments.BEHEADING, "Beheading");

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
