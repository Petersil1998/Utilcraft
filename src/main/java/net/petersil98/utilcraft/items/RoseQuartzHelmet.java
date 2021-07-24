package net.petersil98.utilcraft.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftArmorMaterial;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class RoseQuartzHelmet extends ArmorItem {
    public RoseQuartzHelmet() {
        super(UtilcraftArmorMaterial.ROSE_QUARTZ, EquipmentSlot.HEAD, new Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
