package net.petersil98.utilcraft.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.ModArmorMaterial;

public class RoseQuartzChestplate extends ArmorItem {
    public RoseQuartzChestplate() {
        super(ModArmorMaterial.ROSE_QUARTZ, EquipmentSlotType.CHEST, new Properties().group(Utilcraft.ITEM_GROUP));
    }
}
