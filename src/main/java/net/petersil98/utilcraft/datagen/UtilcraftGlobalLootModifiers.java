package net.petersil98.utilcraft.datagen;

import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
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
        add(UtilcraftLootModifiers.BEHEADING_SERIALIZER.getId().getPath(), UtilcraftLootModifiers.BEHEADING_SERIALIZER.get(), new BeheadingModifier(new ILootCondition[]{
                MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(UtilcraftEnchantments.BEHEADING.get(), MinMaxBounds.IntBound.atLeast(1)))).build()
        }));
    }
}
