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
        return 1;
    }

    public boolean canApplyTogether(@Nonnull Enchantment enchantment) {
        return super.canApplyTogether(enchantment) && enchantment != Enchantments.LOOTING;
    }

    @Override
    public void onEntityDamaged(@Nonnull LivingEntity user, @Nonnull Entity target, int level) {
        super.onEntityDamaged(user, target, level);
    }
}
