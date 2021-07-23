package net.petersil98.utilcraft.items;

import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.inventory.StonecutterMenu;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftArmorMaterial;

import net.minecraft.item.Item.Properties;

public class RoseQuartzLeggings extends StonecutterMenu {
    public RoseQuartzLeggings() {
        super(UtilcraftArmorMaterial.ROSE_QUARTZ, EntityEvent.LEGS, new Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
