package net.petersil98.utilcraft.items;

import net.minecraft.item.*;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTier;

import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.HoeItem;

public class RoseQuartzAxe extends ArmorMaterials {
    public RoseQuartzAxe() {
        super(UtilcraftItemTier.ROSE_QUARTZ, 5.0F, -3.0F, new HoeItem.Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
