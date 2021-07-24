package net.petersil98.utilcraft.enchantments;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.LootBonusEnchantment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;

import javax.annotation.Nonnull;

public class BeheadingEnchantment extends LootBonusEnchantment {
    public BeheadingEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND);
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
