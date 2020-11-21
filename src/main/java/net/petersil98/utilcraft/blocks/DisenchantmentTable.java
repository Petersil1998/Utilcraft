package net.petersil98.utilcraft.blocks;

import net.minecraft.block.AbstractBlock;
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
        super(AbstractBlock.Properties
                .create(Material.ROCK, MaterialColor.RED)
                .setRequiresTool()
                .hardnessAndResistance(5.0F, 1200.0F)
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
    public ActionResultType onBlockActivated(@Nonnull BlockState state, World world, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand handIn, BlockRayTraceResult hit) {
        if (world.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            player.openContainer(state.getContainer(world, pos));
            return ActionResultType.CONSUME;
        }
    }


    @Nullable
    public INamedContainerProvider getContainer(@Nonnull BlockState state, World worldIn, @Nonnull BlockPos pos) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof DisenchantmentTableTileEntity) {
            ITextComponent itextcomponent = ((INameable)tileentity).getDisplayName();
            return new SimpleNamedContainerProvider((id, inventory, player) -> new DisenchantmentTableContainer(id, inventory, IWorldPosCallable.of(worldIn, pos)), itextcomponent);
        } else {
            return null;
        }
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void onBlockPlacedBy(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, LivingEntity placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof EnchantingTableTileEntity) {
                ((EnchantingTableTileEntity)tileentity).setCustomName(stack.getDisplayName());
            }
        }

    }

    public boolean allowsMovement(@Nonnull BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull PathType type) {
        return false;
    }
}
