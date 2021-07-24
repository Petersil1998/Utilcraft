package net.petersil98.utilcraft.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftArmorMaterial;

import net.minecraft.world.item.Item.Properties;

public class RoseQuartzBoots extends ArmorItem {
    public RoseQuartzBoots() {
        super(UtilcraftArmorMaterial.ROSE_QUARTZ, EquipmentSlot.FEET, new Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
