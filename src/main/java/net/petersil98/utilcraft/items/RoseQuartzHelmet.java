package net.petersil98.utilcraft.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftArmorMaterial;

public class RoseQuartzHelmet extends ArmorItem {
    public RoseQuartzHelmet() {
        super(UtilcraftArmorMaterial.ROSE_QUARTZ, EquipmentSlotType.HEAD, new Item.Properties().group(Utilcraft.ITEM_GROUP));
    }
}
