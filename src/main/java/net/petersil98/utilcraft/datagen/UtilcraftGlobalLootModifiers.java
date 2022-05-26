package net.petersil98.utilcraft.datagen;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.loot_modifiers.BeheadingModifier;
import net.petersil98.utilcraft.enchantments.UtilcraftEnchantments;
import net.petersil98.utilcraft.loot_modifiers.RiceModifier;
import net.petersil98.utilcraft.loot_modifiers.UtilcraftLootModifiers;

public class UtilcraftGlobalLootModifiers extends GlobalLootModifierProvider {

    public UtilcraftGlobalLootModifiers(DataGenerator generator) {
        super(generator, Utilcraft.MOD_ID);
    }

    @Override
    protected void start() {
        add(UtilcraftLootModifiers.BEHEADING_SERIALIZER.getId().getPath(), UtilcraftLootModifiers.BEHEADING_SERIALIZER.get(), new BeheadingModifier(new LootItemCondition[]{
                MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(UtilcraftEnchantments.BEHEADING.get(), MinMaxBounds.Ints.atLeast(1)))).build(),
                LootItemKilledByPlayerCondition.killedByPlayer().build()
        }));

        add(UtilcraftLootModifiers.RICE_SERIALIZER.getId().getPath(), UtilcraftLootModifiers.RICE_SERIALIZER.get(), new RiceModifier(new LootItemCondition[]{
                LootItemRandomChanceCondition.randomChance(0.125f).build(),
                LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiome(Biomes.JUNGLE))
                        .or(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiome(Biomes.BAMBOO_JUNGLE)))
                        .or(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiome(Biomes.SPARSE_JUNGLE)))
                        .build()
        }));
    }
}
