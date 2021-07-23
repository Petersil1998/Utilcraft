package net.petersil98.utilcraft.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftArmorMaterial;

import net.minecraft.item.Item.Properties;

public class RoseQuartzLeggings extends ArmorItem {
    public RoseQuartzLeggings() {
        super(UtilcraftArmorMaterial.ROSE_QUARTZ, EquipmentSlotType.LEGS, new Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
