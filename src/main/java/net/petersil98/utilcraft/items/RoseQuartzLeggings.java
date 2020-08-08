package net.petersil98.utilcraft.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.items.custom.ModArmorMaterial;

public class RoseQuartzLeggings extends ArmorItem {
    public RoseQuartzLeggings() {
        super(ModArmorMaterial.ROSE_QUARTZ, EquipmentSlotType.LEGS, (new Properties()).group(Main.setup.itemGroup));
    }
}
