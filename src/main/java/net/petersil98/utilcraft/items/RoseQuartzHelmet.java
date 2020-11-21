package net.petersil98.utilcraft.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.items.custom.ModArmorMaterial;

public class RoseQuartzHelmet extends ArmorItem {
    public RoseQuartzHelmet() {
        super(ModArmorMaterial.ROSE_QUARTZ, EquipmentSlotType.HEAD, (new Item.Properties()).group(Main.itemGroup));
    }
}
