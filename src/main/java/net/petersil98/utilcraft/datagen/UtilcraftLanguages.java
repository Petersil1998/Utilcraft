package net.petersil98.utilcraft.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.enchantments.UtilcraftEnchantments;
import net.petersil98.utilcraft.items.UtilcraftItems;

import javax.annotation.Nonnull;

public class UtilcraftLanguages {

    @Nonnull
    public static English getEnglish(DataGenerator generator){
        return new English(generator);
    }

    @Nonnull
    public static German getGerman(DataGenerator generator){
        return new German(generator);
    }

    private static class English extends LanguageProvider {

        public English(DataGenerator generator) {
            super(generator, Utilcraft.MOD_ID, "en_us");
        }

        @Override
        protected void addTranslations() {
            add(UtilcraftBlocks.GOLD_BRICK.get(), "Gold Brick");
            add(UtilcraftBlocks.GOLD_STAIRS.get(), "Gold Stairs");
            add(UtilcraftBlocks.GOLD_SLAB.get(), "Gold Slab");
            add(UtilcraftBlocks.GOLD_WALL.get(), "Gold Wall");
            add(UtilcraftBlocks.COMPRESSED_COBBLESTONE.get(), "Compressed Cobblestone");
            add(UtilcraftBlocks.SILVER_ORE.get(), "Silver Ore");
            add(UtilcraftBlocks.ROSE_QUARTZ_ORE.get(), "Rose Quartz Ore");
            add(UtilcraftBlocks.ROSE_QUARTZ_BLOCK.get(), "Rose Quartz Block");
            add(UtilcraftBlocks.SIDE_STONE_SLAB.get(), "Side Stone Slab");
            add(UtilcraftBlocks.SIDE_COBBLESTONE_SLAB.get(), "Side Cobblestone Slab");
            add(UtilcraftBlocks.SIDE_OAK_SLAB.get(), "Side Oak Slab");
            add(UtilcraftBlocks.SIDE_SPRUCE_SLAB.get(), "Side Spruce Slab");
            add(UtilcraftBlocks.SIDE_BIRCH_SLAB.get(), "Side Birch Slab");
            add(UtilcraftBlocks.SIDE_JUNGLE_SLAB.get(), "Side Jungle Slab");
            add(UtilcraftBlocks.SIDE_ACACIA_SLAB.get(), "Side Acacia Slab");
            add(UtilcraftBlocks.SIDE_DARK_OAK_SLAB.get(), "Side Dark Oak Slab");
            add(UtilcraftBlocks.SIDE_SAKURA_SLAB.get(), "Side Sakura Slab");
            add(UtilcraftBlocks.SIDE_GOLD_SLAB.get(), "Side Gold Slab");
            add(UtilcraftBlocks.SAKURA_LEAVES.get(), "Sakura Leaves");
            add(UtilcraftBlocks.SAKURA_LOG.get(), "Sakura Log");
            add(UtilcraftBlocks.SAKURA_PLANKS.get(), "Sakura Planks");
            add(UtilcraftBlocks.SAKURA_SAPLING.get(), "Sakura Sapling");
            add(UtilcraftBlocks.SAKURA_SLAB.get(), "Sakura Slab");
            add(UtilcraftBlocks.SAKURA_STAIRS.get(), "Sakura Stairs");
            add(UtilcraftBlocks.SAKURA_FENCE.get(), "Sakura Fence");
            add(UtilcraftBlocks.SAKURA_FENCE_GATE.get(), "Sakura Fence Gate");
            add(UtilcraftBlocks.SAKURA_PRESSURE_PLATE.get(), "Sakura Pressure Plate");
            add(UtilcraftBlocks.SAKURA_TRAPDOOR.get(), "Sakura Trapdoor");
            add(UtilcraftBlocks.SAKURA_SIGN.get(), "Sakura Sign");
            add(UtilcraftBlocks.SAKURA_BUTTON.get(), "Sakura Button");
            add(UtilcraftBlocks.SAKURA_DOOR.get(), "Sakura Door");
            add(UtilcraftBlocks.DISENCHANTMENT_TABLE.get(), "Disenchantment Table");
            add(UtilcraftBlocks.SECURE_CHEST.get(), "Secure Chest");
            add(UtilcraftBlocks.REDSTONE_STAIRS.get(), "Redstone Stairs");
            add(UtilcraftBlocks.REDSTONE_SLAB.get(), "Redstone Slab");
            add(UtilcraftBlocks.SIDE_REDSTONE_SLAB.get(), "Side Redstone Slab");
            add(UtilcraftBlocks.SUSHI_MAKER.get(), "Sushi Maker");
            add(UtilcraftBlocks.GLASS_STAIRS.get(), "Glass Stairs");
            add(UtilcraftBlocks.SILVER_BLOCK.get(), "Silver Block");

            add(UtilcraftItems.JUICER.get(), "Juicer");
            add(UtilcraftItems.APPLE_JUICE.get(), "Apple Juice");
            add(UtilcraftItems.SWEET_BERRY_JUICE.get(), "Sweet Berry Juice");
            add(UtilcraftItems.FLOUR.get(), "Flour");
            add(UtilcraftItems.BAGUETTE.get(), "Baguette");
            add(UtilcraftItems.ROSE_QUARTZ.get(), "Rose Quartz");
            add(UtilcraftItems.ROSE_QUARTZ_HELMET.get(), "Rose Quartz Helmet");
            add(UtilcraftItems.ROSE_QUARTZ_CHESTPLATE.get(), "Rose Quartz Chestplate");
            add(UtilcraftItems.ROSE_QUARTZ_LEGGINGS.get(), "Rose Quartz Leggings");
            add(UtilcraftItems.ROSE_QUARTZ_BOOTS.get(), "Rose Quartz Boots");
            add(UtilcraftItems.ROSE_QUARTZ_PICKAXE.get(), "Rose Quartz Pickaxe");
            add(UtilcraftItems.ROSE_QUARTZ_SHOVEL.get(), "Rose Quartz Shovel");
            add(UtilcraftItems.ROSE_QUARTZ_HOE.get(), "Rose Quartz Hoe");
            add(UtilcraftItems.ROSE_QUARTZ_SWORD.get(), "Rose Quartz Sword");
            add(UtilcraftItems.ROSE_QUARTZ_AXE.get(), "Rose Quartz Axe");
            add(UtilcraftItems.ROSE_QUARTZ_SUPER_HAMMER.get(), "Rose Quartz Super Hammer");
            add(UtilcraftItems.ROSE_QUARTZ_SUPER_SHOVEL.get(), "Rose Quartz Super Shovel");
            add(UtilcraftItems.TRAVELERS_BACKPACK.get(), "Travelers Backpack");
            add(UtilcraftItems.TNT_FINDER.get(), "TNT Finder");
            add(UtilcraftItems.SILVER_INGOT.get(), "Silver Ingot");
            add(UtilcraftItems.BUTCHERS_KNIFE.get(), "Butcher's Knife");
            add(UtilcraftItems.SPAWNER_ITEM.get(), "Broken Spawner");

            add(UtilcraftEnchantments.BEHEADING.get(), "Beheading");

            add("itemGroup.utilcraft", "Utilcraft");

            add("advancements.utilcraft.root.title", "Green Thumb");
            add("advancements.utilcraft.root.description", "Place a Sakura Sapling");

            add("advancements.utilcraft.mine_rose_quartz.title", "Mine Rose Quartz");
            add("advancements.utilcraft.mine_rose_quartz.description", "Find and mine some Rose Quartz");

            add("config.section.gui", "GUI Config");
            add("config.number_player_deaths.description", "First x Players are shown in the Death-Scoreboard (0 to disable feature)");
            add("config.number_player_deaths.title", "Death-Scoreboard Length (0 - 20)");

            add("screen.utilcraft.config", "Utilcraft Configuration");
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

            add("version.utilcraft.new", "[Utilcraft] New Version %s available!");
        }
    }

    private static class German extends LanguageProvider {

        public German(DataGenerator generator) {
            super(generator, Utilcraft.MOD_ID, "de_de");
        }

        @Override
        protected void addTranslations() {
            add(UtilcraftBlocks.GOLD_BRICK.get(), "Gold Ziegelstein");
            add(UtilcraftBlocks.GOLD_STAIRS.get(), "Goldtreppe");
            add(UtilcraftBlocks.GOLD_SLAB.get(), "Goldstufe");
            add(UtilcraftBlocks.GOLD_WALL.get(), "Goldmauer");
            add(UtilcraftBlocks.COMPRESSED_COBBLESTONE.get(), "Gepresster Bruchstein");
            add(UtilcraftBlocks.SILVER_ORE.get(), "Silbererz");
            add(UtilcraftBlocks.ROSE_QUARTZ_ORE.get(), "Rosenquarzerz");
            add(UtilcraftBlocks.ROSE_QUARTZ_BLOCK.get(), "Rosenquarzblock");
            add(UtilcraftBlocks.SIDE_STONE_SLAB.get(), "Seitliche Steinstufe");
            add(UtilcraftBlocks.SIDE_COBBLESTONE_SLAB.get(), "Seitliche Bruchsteinstufe");
            add(UtilcraftBlocks.SIDE_OAK_SLAB.get(), "Seitliche Eichenholzstufe");
            add(UtilcraftBlocks.SIDE_SPRUCE_SLAB.get(), "Seitliche Fichtenholzstufe");
            add(UtilcraftBlocks.SIDE_BIRCH_SLAB.get(), "Seitliche Brikenholzstufe");
            add(UtilcraftBlocks.SIDE_JUNGLE_SLAB.get(), "Seitliche Tropenholzstufe");
            add(UtilcraftBlocks.SIDE_ACACIA_SLAB.get(), "Seitliche Akazienholzstufe");
            add(UtilcraftBlocks.SIDE_DARK_OAK_SLAB.get(), "Seitliche Schwarzeichenholzstufe");
            add(UtilcraftBlocks.SIDE_SAKURA_SLAB.get(), "Seitliche Kirschblütenholzstufe");
            add(UtilcraftBlocks.SIDE_GOLD_SLAB.get(), "Seitliche Goldstufe");
            add(UtilcraftBlocks.SAKURA_LEAVES.get(), "Kirschblütenlaub");
            add(UtilcraftBlocks.SAKURA_LOG.get(), "Kirschblütenstamm");
            add(UtilcraftBlocks.SAKURA_PLANKS.get(), "Kirschblütenholzbretter");
            add(UtilcraftBlocks.SAKURA_SAPLING.get(), "Kirschblütensetzling");
            add(UtilcraftBlocks.SAKURA_SLAB.get(), "Kirschblütenholzstufe");
            add(UtilcraftBlocks.SAKURA_STAIRS.get(), "Kirschblütenholztreppe");
            add(UtilcraftBlocks.SAKURA_FENCE.get(), "Kirschblütenholzzaun");
            add(UtilcraftBlocks.SAKURA_FENCE_GATE.get(), "Kirschblütenholzzauntor");
            add(UtilcraftBlocks.SAKURA_PRESSURE_PLATE.get(), "Kirschblütenholzdruckplatte");
            add(UtilcraftBlocks.SAKURA_TRAPDOOR.get(), "Kirschblütenholzfalltür");
            add(UtilcraftBlocks.SAKURA_SIGN.get(), "Kirschblütenholzschild");
            add(UtilcraftBlocks.SAKURA_BUTTON.get(), "Kirschblütenholzknopf");
            add(UtilcraftBlocks.SAKURA_DOOR.get(), "Kirschblütenholztür");
            add(UtilcraftBlocks.DISENCHANTMENT_TABLE.get(), "Entzaubertisch");
            add(UtilcraftBlocks.SECURE_CHEST.get(), "Sichere Truhe");
            add(UtilcraftBlocks.REDSTONE_STAIRS.get(), "Redstonetreppe");
            add(UtilcraftBlocks.REDSTONE_SLAB.get(), "Redstonestufe");
            add(UtilcraftBlocks.SIDE_REDSTONE_SLAB.get(), "Seitliche Redstonestufe");
            add(UtilcraftBlocks.SUSHI_MAKER.get(), "Sushi Maker");
            add(UtilcraftBlocks.GLASS_STAIRS.get(), "Glastreppe");
            add(UtilcraftBlocks.SILVER_BLOCK.get(), "Silberblock");

            add(UtilcraftItems.JUICER.get(), "Entsafter");
            add(UtilcraftItems.APPLE_JUICE.get(), "Apfelsaft");
            add(UtilcraftItems.SWEET_BERRY_JUICE.get(), "Süßbeerenssaft");
            add(UtilcraftItems.FLOUR.get(), "Mehl");
            add(UtilcraftItems.BAGUETTE.get(), "Baguette");
            add(UtilcraftItems.ROSE_QUARTZ.get(), "Rosenquarz");
            add(UtilcraftItems.ROSE_QUARTZ_HELMET.get(), "Rosenquarzhelm");
            add(UtilcraftItems.ROSE_QUARTZ_CHESTPLATE.get(), "Rosenquarzharnisch");
            add(UtilcraftItems.ROSE_QUARTZ_LEGGINGS.get(), "Rosenquarzbeinschutz");
            add(UtilcraftItems.ROSE_QUARTZ_BOOTS.get(), "Rosenquarzstiefel");
            add(UtilcraftItems.ROSE_QUARTZ_PICKAXE.get(), "Rosenquarzspitzhacke");
            add(UtilcraftItems.ROSE_QUARTZ_SHOVEL.get(), "Rosenquarzschaufel");
            add(UtilcraftItems.ROSE_QUARTZ_HOE.get(), "Rosenquarzhacke");
            add(UtilcraftItems.ROSE_QUARTZ_SWORD.get(), "Rosenquarzschwert");
            add(UtilcraftItems.ROSE_QUARTZ_AXE.get(), "Rosenquarzaxt");
            add(UtilcraftItems.ROSE_QUARTZ_SUPER_HAMMER.get(), "Rosenquarzsuperhammer");
            add(UtilcraftItems.ROSE_QUARTZ_SUPER_SHOVEL.get(), "Rosenquarzsuperschaufel");
            add(UtilcraftItems.TRAVELERS_BACKPACK.get(), "Rucksack des Reisenden");
            add(UtilcraftItems.TNT_FINDER.get(), "TNT Sucher");
            add(UtilcraftItems.SILVER_INGOT.get(), "Silberbarren");
            add(UtilcraftItems.BUTCHERS_KNIFE.get(), "Metzgermesser");
            add(UtilcraftItems.SPAWNER_ITEM.get(), "Zerstörter Spawner");

            add(UtilcraftEnchantments.BEHEADING.get(), "Enthauptung");

            add("itemGroup.utilcraft", "Utilcraft");

            add("advancements.utilcraft.root.title", "Grüner Daumen");
            add("advancements.utilcraft.root.description", "Setze einen Kirschblütensetzling");

            add("advancements.utilcraft.mine_rose_quartz.title", "Baue Rosenquarz ab");
            add("advancements.utilcraft.mine_rose_quartz.description", "Finde und baue Rosenquarz ab");

            add("config.section.gui", "GUI Konfiguration");
            add("config.number_player_deaths.description", "Die Ersten x Spieler werden in dem Death-Scoreboard (0 um das Feature deaktivieren)");
            add("config.number_player_deaths.title", "Death-Scoreboard Länge (0 - 20)");

            add("screen.utilcraft.config", "Utilcraft Konfiguration");
            add("screen.utilcraft.disenchantment_table", "Entzaubertisch");
            add("screen.utilcraft.secure_chest", "Sichere Truhe");

            add("protection.utilcraft.block_protected", "Dieser Block ist gesichert, du kannst ihn weder abbauen noch benutzen!");
            add("player_trusted.utilcraft.player_added", "Der Spieler %s kann absofort auf deine Sicheren Truhen zugreifen!");
            add("player_trusted.utilcraft.player_removed", "Der Spieler %s kann nicht mehr auf deine Sicheren Truhen zugreifen!");
            add("player_trusted.utilcraft.list", "Du vertraust folgenden Spielern: %s");

            add("vein_miner.utilcraft.active", "Vein Miner aktiv");
            add("vein_miner.utilcraft.inactive", "Vein Miner inaktiv");

            add("home.utilcraft.set_home", "Zuhause wurde erfolgreich auf die aktuelle Position gesetzt!");
            add("home.utilcraft.not_set", "Zuhause wurde noch nicht gesetzt!");
            add("home.utilcraft.teleported", "Willkommen zuhause!");

            add("key_bindings.utilcraft.category", "Utilcraft");
            add("key_bindings.utilcraft.vein_miner", "Vein Miner ein/ausschalten");

            add("version.utilcraft.new", "[Utilcraft] Neue Version %s verfügbar!");
        }
    }
}
