package net.petersil98.utilcraft.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.items.custom.ModArmorMaterial;

public class RoseQuartzChestplate extends ArmorItem {
    public RoseQuartzChestplate() {
        super(ModArmorMaterial.ROSE_QUARTZ, EquipmentSlotType.CHEST, (new Properties()).group(Main.itemGroup));
    }
}
