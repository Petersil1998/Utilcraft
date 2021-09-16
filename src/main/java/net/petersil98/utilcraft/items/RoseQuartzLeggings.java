package net.petersil98.utilcraft.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.petersil98.utilcraft.items.custom.UtilcraftArmorMaterial;

public class RoseQuartzLeggings extends ArmorItem {

    public RoseQuartzLeggings(Properties properties) {
        super(UtilcraftArmorMaterial.ROSE_QUARTZ, EquipmentSlot.LEGS, properties);
    }
}
