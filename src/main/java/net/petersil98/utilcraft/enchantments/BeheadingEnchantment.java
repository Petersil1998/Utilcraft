package net.petersil98.utilcraft.enchantments;

import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.DigDurabilityEnchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.FishingSpeedEnchantment;
import net.minecraft.world.effect.package-info;
import net.minecraft.world.entity.ItemBasedSteering;
import net.minecraft.world.entity.EntityEvent;

import javax.annotation.Nonnull;

import net.minecraft.enchantment.Enchantment.Rarity;

public class BeheadingEnchantment extends FishingSpeedEnchantment {
    public BeheadingEnchantment() {
        super(Rarity.RARE, DigDurabilityEnchantment.WEAPON, EntityEvent.MAINHAND);
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 4;
    }

    public boolean checkCompatibility(@Nonnull DamageEnchantment enchantment) {
        return super.checkCompatibility(enchantment) && enchantment != EnchantmentCategory.MOB_LOOTING;
    }

    @Override
    public void doPostAttack(@Nonnull ItemBasedSteering user, @Nonnull package-info target, int level) {
        super.doPostAttack(user, target, level);
    }

    public static int getHeadDropChance(int level){
        return level*10;
    }
}
