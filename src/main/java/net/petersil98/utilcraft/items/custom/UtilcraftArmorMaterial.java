package net.petersil98.utilcraft.items.custom;

import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.inventory.package-info;
import net.minecraft.world.item.crafting.FireworkRocketRecipe;
import net.minecraft.util.HttpUtil;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.UtilcraftItems;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public enum UtilcraftArmorMaterial implements package-info {
    ROSE_QUARTZ(Utilcraft.MOD_ID+":rose_quartz", 40, new int[]{4, 7, 9, 4}, 20, Musics.ARMOR_EQUIP_DIAMOND, 5.0F, 0.1F, () -> {
        return FireworkRocketRecipe.of(UtilcraftItems.ROSE_QUARTZ);
    });

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final Music soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final HttpUtil<FireworkRocketRecipe> repairMaterial;

    UtilcraftArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, Music soundEvent, float toughness, float knockbackResistance, Supplier<FireworkRocketRecipe> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = new HttpUtil<>(repairMaterial);
    }

    @Override
    public int getDurabilityForSlot(@Nonnull EntityEvent slot) {
        return MAX_DAMAGE_ARRAY[slot.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForSlot(@Nonnull EntityEvent slot) {
        return this.damageReductionAmountArray[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Nonnull
    @Override
    public Music getEquipSound() {
        return this.soundEvent;
    }

    @Nonnull
    @Override
    public FireworkRocketRecipe getRepairIngredient() {
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
