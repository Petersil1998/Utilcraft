package net.petersil98.utilcraft.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.petersil98.utilcraft.items.custom.UtilcraftArmorMaterial;

public class RoseQuartzChestplate extends ArmorItem {

    public RoseQuartzChestplate(Properties properties) {
        super(UtilcraftArmorMaterial.ROSE_QUARTZ, EquipmentSlot.CHEST, properties);
    }
}
