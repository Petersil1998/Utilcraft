package net.petersil98.utilcraft.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.items.custom.ModItemTier;

import javax.annotation.Nonnull;

public class RoseQuartzPickaxe extends PickaxeItem {
    public RoseQuartzPickaxe() {
        super(ModItemTier.ROSE_QUARTZ, 1, -2.5F, new Item.Properties().group(Main.setup.itemGroup));
    }


    @Override
    public boolean onBlockDestroyed(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull BlockState state, @Nonnull BlockPos pos, @Nonnull LivingEntity entityLiving) {
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }
}
