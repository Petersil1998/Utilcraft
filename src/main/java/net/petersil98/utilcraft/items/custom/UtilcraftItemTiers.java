package net.petersil98.utilcraft.items.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.UtilcraftItems;
import net.petersil98.utilcraft.tags.UtilcraftTags;

import java.util.List;

public class UtilcraftItemTiers {

    public static final Tier ROSE_QUARTZ = TierSortingRegistry.registerTier(
            new ForgeTier(5,3021,10.0F,5.0F, 20, UtilcraftTags.BlockTags.NEEDS_ROSE_QUARTZ_TOOL, () -> Ingredient.of(UtilcraftItems.ROSE_QUARTZ.get())),
            new ResourceLocation(Utilcraft.MOD_ID, "rose_quartz"),
            List.of(Tiers.NETHERITE), List.of());
}
