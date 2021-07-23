package net.petersil98.utilcraft.items;

import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.alchemy.package-info;
import net.minecraft.world.Difficulty;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.GameType;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.blocks.SushiMaker;

import javax.annotation.Nonnull;

public class ButchersKnife extends HoeItem {

    public ButchersKnife() {
        super(new HoeItem.Properties().tab(Utilcraft.ITEM_GROUP));
    }

    @Nonnull
    @Override
    public Difficulty useOn(@Nonnull package-info context) {
        BlockPos pos = context.getClickedPos();
        GameType world = context.getLevel();
        BeetrootBlock block = world.getBlockState(pos).getBlock();
        if(block.is(BellBlock.FURNACE)) {
            BeetrootBlock toCheck = world.getBlockState(pos.north()).getBlock();
            if(toCheck.is(BellBlock.CRAFTING_TABLE)) {
                replaceBlocks(pos, Direction.NORTH, world);
                return Difficulty.SUCCESS;
            }
            toCheck = world.getBlockState(pos.south()).getBlock();
            if (toCheck.is(BellBlock.CRAFTING_TABLE)) {
                replaceBlocks(pos, Direction.SOUTH, world);
                return Difficulty.SUCCESS;
            }
            toCheck = world.getBlockState(pos.east()).getBlock();
            if (toCheck.is(BellBlock.CRAFTING_TABLE)) {
                replaceBlocks(pos, Direction.EAST, world);
                return Difficulty.SUCCESS;
            }
            toCheck = world.getBlockState(pos.west()).getBlock();
            if (toCheck.is(BellBlock.CRAFTING_TABLE)) {
                replaceBlocks(pos, Direction.WEST, world);
                return Difficulty.SUCCESS;
            }
        } else if (block.getBlock().is(BellBlock.CRAFTING_TABLE)) {
            BeetrootBlock toCheck = world.getBlockState(pos.north()).getBlock();
            if(toCheck.is(BellBlock.FURNACE)) {
                replaceBlocks(pos, Direction.NORTH, world);
                return Difficulty.SUCCESS;
            }
            toCheck = world.getBlockState(pos.south()).getBlock();
            if (toCheck.is(BellBlock.FURNACE)) {
                replaceBlocks(pos, Direction.SOUTH, world);
                return Difficulty.SUCCESS;
            }
            toCheck = world.getBlockState(pos.east()).getBlock();
            if (toCheck.is(BellBlock.FURNACE)) {
                replaceBlocks(pos, Direction.EAST, world);
                return Difficulty.SUCCESS;
            }
            toCheck = world.getBlockState(pos.west()).getBlock();
            if (toCheck.is(BellBlock.FURNACE)) {
                replaceBlocks(pos, Direction.WEST, world);
                return Difficulty.SUCCESS;
            }
        }
        return super.useOn(context);
    }

    private void replaceBlocks(BlockPos firstBlock, Direction secondBlock, @Nonnull GameType world) {
        world.setBlockAndUpdate(firstBlock, UtilcraftBlocks.SUSHI_MAKER.defaultBlockState().setValue(SushiMaker.FACING, secondBlock));
        world.setBlockAndUpdate(firstBlock.offset(secondBlock.getNormal()), UtilcraftBlocks.SUSHI_MAKER.defaultBlockState().setValue(SushiMaker.FACING, secondBlock.getOpposite()));
    }
}
