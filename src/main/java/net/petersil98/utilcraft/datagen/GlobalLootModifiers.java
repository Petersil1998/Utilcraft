package net.petersil98.utilcraft.datagen;

import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.enchantments.BeheadingModifier;
import net.petersil98.utilcraft.enchantments.ModEnchantments;

public class GlobalLootModifiers extends GlobalLootModifierProvider {

    public GlobalLootModifiers(DataGenerator generator) {
        super(generator, Main.MOD_ID);
    }

    @Override
    protected void start() {
        add(ModEnchantments.BEHEADING_SERIALIZER.getRegistryName().getPath(), ModEnchantments.BEHEADING_SERIALIZER, new BeheadingModifier(new ILootCondition[]{
                MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(ModEnchantments.BEHEADING, MinMaxBounds.IntBound.atLeast(1)))).build()
        }));
    }
}
