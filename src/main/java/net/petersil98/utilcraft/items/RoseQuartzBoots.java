package net.petersil98.utilcraft.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftArmorMaterial;

public class RoseQuartzBoots extends ArmorItem {
    public RoseQuartzBoots() {
        super(UtilcraftArmorMaterial.ROSE_QUARTZ, EquipmentSlotType.FEET, new Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
