package net.petersil98.utilcraft.items;

import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.inventory.StonecutterMenu;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftArmorMaterial;

import net.minecraft.item.Item.Properties;

public class RoseQuartzChestplate extends StonecutterMenu {
    public RoseQuartzChestplate() {
        super(UtilcraftArmorMaterial.ROSE_QUARTZ, EntityEvent.CHEST, new Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
