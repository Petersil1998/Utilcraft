package net.petersil98.utilcraft.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.petersil98.utilcraft.blocks.SushiMaker;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

import javax.annotation.Nonnull;

public class ButchersKnife extends Item {

    public ButchersKnife(Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    public InteractionResult useOn(@Nonnull UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        Level world = context.getLevel();
        Block block = world.getBlockState(pos).getBlock();
        if(block.equals(Blocks.FURNACE)) {
            Block toCheck = world.getBlockState(pos.north()).getBlock();
            if(toCheck.equals(Blocks.CRAFTING_TABLE)) {
                replaceBlocks(pos, Direction.NORTH, world);
                return InteractionResult.SUCCESS;
            }
            toCheck = world.getBlockState(pos.south()).getBlock();
            if (toCheck.equals(Blocks.CRAFTING_TABLE)) {
                replaceBlocks(pos, Direction.SOUTH, world);
                return InteractionResult.SUCCESS;
            }
            toCheck = world.getBlockState(pos.east()).getBlock();
            if (toCheck.equals(Blocks.CRAFTING_TABLE)) {
                replaceBlocks(pos, Direction.EAST, world);
                return InteractionResult.SUCCESS;
            }
            toCheck = world.getBlockState(pos.west()).getBlock();
            if (toCheck.equals(Blocks.CRAFTING_TABLE)) {
                replaceBlocks(pos, Direction.WEST, world);
                return InteractionResult.SUCCESS;
            }
        } else if (block.equals(Blocks.CRAFTING_TABLE)) {
            Block toCheck = world.getBlockState(pos.north()).getBlock();
            if(toCheck.equals(Blocks.FURNACE)) {
                replaceBlocks(pos, Direction.NORTH, world);
                return InteractionResult.SUCCESS;
            }
            toCheck = world.getBlockState(pos.south()).getBlock();
            if (toCheck.equals(Blocks.FURNACE)) {
                replaceBlocks(pos, Direction.SOUTH, world);
                return InteractionResult.SUCCESS;
            }
            toCheck = world.getBlockState(pos.east()).getBlock();
            if (toCheck.equals(Blocks.FURNACE)) {
                replaceBlocks(pos, Direction.EAST, world);
                return InteractionResult.SUCCESS;
            }
            toCheck = world.getBlockState(pos.west()).getBlock();
            if (toCheck.equals(Blocks.FURNACE)) {
                replaceBlocks(pos, Direction.WEST, world);
                return InteractionResult.SUCCESS;
            }
        }
        return super.useOn(context);
    }

    private void replaceBlocks(BlockPos firstBlock, Direction secondBlock, @Nonnull Level world) {
        world.setBlockAndUpdate(firstBlock, UtilcraftBlocks.SUSHI_MAKER.get().defaultBlockState().setValue(SushiMaker.FACING, secondBlock));
        world.setBlockAndUpdate(firstBlock.offset(secondBlock.getNormal()), UtilcraftBlocks.SUSHI_MAKER.get().defaultBlockState().setValue(SushiMaker.FACING, secondBlock.getOpposite()));
    }
}
