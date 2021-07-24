package net.petersil98.utilcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.tileentity.EnchantingTableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.INameable;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.petersil98.utilcraft.container.DisenchantmentTableContainer;
import net.petersil98.utilcraft.tile_entities.DisenchantmentTableTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DisenchantmentTable extends Block {
    public DisenchantmentTable() {
        super(Properties
                .of(Material.STONE, MaterialColor.COLOR_RED)
                .requiresCorrectToolForDrops()
                .strength(5.0F, 1200.0F)
        );
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DisenchantmentTableTileEntity();
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType use(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand hand, BlockRayTraceResult hit) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(world, pos));
            return ActionResultType.CONSUME;
        }
    }


    @Nullable
    public INamedContainerProvider getMenuProvider(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos) {
        TileEntity tileentity = world.getBlockEntity(pos);
        if (tileentity instanceof DisenchantmentTableTileEntity) {
            ITextComponent itextcomponent = ((INameable)tileentity).getDisplayName();
            return new SimpleNamedContainerProvider((id, inventory, player) -> new DisenchantmentTableContainer(id, inventory, IWorldPosCallable.create(world, pos)), itextcomponent);
        } else {
            return null;
        }
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void setPlacedBy(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull BlockState state, LivingEntity placer, @Nonnull ItemStack stack) {
        if (stack.hasCustomHoverName()) {
            TileEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof EnchantingTableTileEntity) {
                ((EnchantingTableTileEntity)tileentity).setCustomName(stack.getHoverName());
            }
        }

    }

    public boolean isPathfindable(@Nonnull BlockState state, @Nonnull IBlockReader world, @Nonnull BlockPos pos, @Nonnull PathType type) {
        return false;
    }
}
