package net.petersil98.utilcraft.items.custom;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadedValue;
import net.petersil98.utilcraft.items.UtilcraftItems;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public enum UtilcraftItemTier implements Tier {
    ROSE_QUARTZ(5,3021,10.0F,5.0F, 20,()-> {
        return Ingredient.of(UtilcraftItems.ROSE_QUARTZ.get());
    }),
    SUPER_ROSE_QUARTZ(5,3021*9,10.0F,5.0F, 20,()-> {
        return Ingredient.of(UtilcraftItems.ROSE_QUARTZ.get());
    });

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadedValue<Ingredient> repairMaterial;

    UtilcraftItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyLoadedValue<>(repairMaterial);
    }

    @Override
    public int getUses() {
        return this.maxUses;
    }

    @Override
    public float getSpeed() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Nonnull
    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }
}
