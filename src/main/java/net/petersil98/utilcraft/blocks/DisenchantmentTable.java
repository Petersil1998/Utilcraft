package net.petersil98.utilcraft.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.EnchantmentTableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.petersil98.utilcraft.block_entities.DisenchantmentTableBlockEntity;
import net.petersil98.utilcraft.container.DisenchantmentTableContainer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class DisenchantmentTable extends Block implements EntityBlock {

    public DisenchantmentTable(Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public InteractionResult use(@Nonnull BlockState state, @Nonnull Level world, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, BlockHitResult hit) {
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(world, pos));
            return InteractionResult.CONSUME;
        }
    }


    @Nullable
    public MenuProvider getMenuProvider(@Nonnull BlockState state, @Nonnull Level world, @Nonnull BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof DisenchantmentTableBlockEntity) {
            Component name = ((Nameable)blockEntity).getDisplayName();
            return new SimpleMenuProvider((id, inventory, player) -> new DisenchantmentTableContainer(id, inventory, ContainerLevelAccess.create(world, pos)), name);
        } else {
            return null;
        }
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void setPlacedBy(@Nonnull Level world, @Nonnull BlockPos pos, @Nonnull BlockState state, LivingEntity placer, @Nonnull ItemStack stack) {
        if (stack.hasCustomHoverName()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof EnchantmentTableBlockEntity) {
                ((EnchantmentTableBlockEntity)blockEntity).setCustomName(stack.getHoverName());
            }
        }

    }

    public boolean isPathfindable(@Nonnull BlockState state, @Nonnull BlockGetter world, @Nonnull BlockPos pos, @Nonnull PathComputationType type) {
        return false;
    }

    @Nullable
    @Override
    @ParametersAreNonnullByDefault
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new DisenchantmentTableBlockEntity(blockPos, blockState);
    }
}
