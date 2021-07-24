package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.block.entity.EnchantmentTableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.Nameable;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.petersil98.utilcraft.block_entities.SecureChestTileEntity;
import net.petersil98.utilcraft.container.DisenchantmentTableContainer;
import net.petersil98.utilcraft.block_entities.DisenchantmentTableTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class DisenchantmentTable extends Block implements EntityBlock {
    public DisenchantmentTable() {
        super(BlockBehaviour.Properties
                .of(Material.STONE, MaterialColor.COLOR_RED)
                .requiresCorrectToolForDrops()
                .strength(5.0F, 1200.0F)
        );
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
        BlockEntity tileentity = world.getBlockEntity(pos);
        if (tileentity instanceof DisenchantmentTableTileEntity) {
            Component itextcomponent = ((Nameable)tileentity).getDisplayName();
            return new SimpleMenuProvider((id, inventory, player) -> new DisenchantmentTableContainer(id, inventory, ContainerLevelAccess.create(world, pos)), itextcomponent);
        } else {
            return null;
        }
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void setPlacedBy(@Nonnull Level world, @Nonnull BlockPos pos, @Nonnull BlockState state, LivingEntity placer, @Nonnull ItemStack stack) {
        if (stack.hasCustomHoverName()) {
            BlockEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof EnchantmentTableBlockEntity) {
                ((EnchantmentTableBlockEntity)tileentity).setCustomName(stack.getHoverName());
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
        return new DisenchantmentTableTileEntity(blockPos, blockState);
    }
}
