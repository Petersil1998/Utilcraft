package net.petersil98.utilcraft.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.petersil98.utilcraft.items.custom.UtilcraftArmorMaterial;

public class RoseQuartzHelmet extends ArmorItem {

    public RoseQuartzHelmet(Properties properties) {
        super(UtilcraftArmorMaterial.ROSE_QUARTZ, EquipmentSlot.HEAD, properties);
    }
}
