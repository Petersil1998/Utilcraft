package net.petersil98.utilcraft.items;

import net.minecraft.world.entity.EntityEvent;
import net.minecraft.item.*;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftArmorMaterial;

import net.minecraft.world.inventory.StonecutterMenu;
import net.minecraft.world.item.HoeItem;

public class RoseQuartzHelmet extends StonecutterMenu {
    public RoseQuartzHelmet() {
        super(UtilcraftArmorMaterial.ROSE_QUARTZ, EntityEvent.HEAD, new HoeItem.Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
