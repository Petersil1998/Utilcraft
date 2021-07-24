package net.petersil98.utilcraft.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.LootBonusEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;

import javax.annotation.Nonnull;

public class BeheadingEnchantment extends LootBonusEnchantment {
    public BeheadingEnchantment() {
        super(Rarity.RARE, EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND);
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 4;
    }

    public boolean checkCompatibility(@Nonnull Enchantment enchantment) {
        return super.checkCompatibility(enchantment) && enchantment != Enchantments.MOB_LOOTING;
    }

    @Override
    public void doPostAttack(@Nonnull LivingEntity user, @Nonnull Entity target, int level) {
        super.doPostAttack(user, target, level);
    }

    public static int getHeadDropChance(int level){
        return level*10;
    }
}
