package net.petersil98.utilcraft.datagen;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.loot_modifiers.BeheadingModifier;
import net.petersil98.utilcraft.enchantments.UtilcraftEnchantments;
import net.petersil98.utilcraft.loot_modifiers.UtilcraftLootModifiers;

public class UtilcraftGlobalLootModifiers extends GlobalLootModifierProvider {

    public UtilcraftGlobalLootModifiers(DataGenerator generator) {
        super(generator, Utilcraft.MOD_ID);
    }

    @Override
    protected void start() {
        add(UtilcraftLootModifiers.BEHEADING_SERIALIZER.getRegistryName().getPath(), UtilcraftLootModifiers.BEHEADING_SERIALIZER, new BeheadingModifier(new InvertedLootItemCondition[]{
                LootItemKilledByPlayerCondition.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(UtilcraftEnchantments.BEHEADING, MinMaxBounds.Ints.atLeast(1)))).build()
        }));
    }
}
