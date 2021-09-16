package net.petersil98.utilcraft.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.petersil98.utilcraft.items.custom.UtilcraftArmorMaterial;

public class RoseQuartzHelmet extends ArmorItem {

    public RoseQuartzHelmet(Properties properties) {
        super(UtilcraftArmorMaterial.ROSE_QUARTZ, EquipmentSlotType.HEAD, properties);
    }
}
