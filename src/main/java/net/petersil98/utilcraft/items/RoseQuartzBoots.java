package net.petersil98.utilcraft.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.items.custom.ModArmorMaterial;

public class RoseQuartzBoots extends ArmorItem {
    public RoseQuartzBoots() {
        super(ModArmorMaterial.ROSE_QUARTZ, EquipmentSlotType.FEET, new Properties().group(Main.ITEM_GROUP));
    }
}
