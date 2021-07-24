package net.petersil98.utilcraft.items.custom;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.UtilcraftItems;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public enum UtilcraftArmorMaterial implements ArmorMaterial {
    ROSE_QUARTZ(Utilcraft.MOD_ID+":rose_quartz", 40, new int[]{4, 7, 9, 4}, 20, SoundEvents.ARMOR_EQUIP_DIAMOND, 5.0F, 0.1F, () -> {
        return Ingredient.of(UtilcraftItems.ROSE_QUARTZ);
    });

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairMaterial;

    UtilcraftArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = new LazyLoadedValue<>(repairMaterial);
    }

    @Override
    public int getDurabilityForSlot(@Nonnull EquipmentSlot slot) {
        return MAX_DAMAGE_ARRAY[slot.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForSlot(@Nonnull EquipmentSlot slot) {
        return this.damageReductionAmountArray[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Nonnull
    @Override
    public SoundEvent getEquipSound() {
        return this.soundEvent;
    }

    @Nonnull
    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Nonnull
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
