package net.petersil98.utilcraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.EnchantmentContainer;
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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

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
        return new DisenchantmentTableTile();
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (world.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            player.openContainer(state.getContainer(world, pos));
            return ActionResultType.CONSUME;
        }
    }


    @Nullable
    public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof DisenchantmentTableTile) {
            ITextComponent itextcomponent = ((INameable)tileentity).getDisplayName();
            return new SimpleNamedContainerProvider((id, inventory, player) -> new DisenchantmentTableContainer(id, inventory, IWorldPosCallable.of(worldIn, pos)), itextcomponent);
        } else {
            return null;
        }
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof EnchantingTableTileEntity) {
                ((EnchantingTableTileEntity)tileentity).setCustomName(stack.getDisplayName());
            }
        }

    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }
}
