package net.petersil98.utilcraft.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftArmorMaterial;

public class RoseQuartzChestplate extends ArmorItem {
    public RoseQuartzChestplate() {
        super(UtilcraftArmorMaterial.ROSE_QUARTZ, EquipmentSlotType.CHEST, new Properties().group(Utilcraft.ITEM_GROUP));
    }
}
