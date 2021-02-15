package net.petersil98.utilcraft.items;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.blocks.SushiMaker;

import javax.annotation.Nonnull;

public class ButchersKnife extends Item {

    public ButchersKnife() {
        super(new Item.Properties().group(Utilcraft.ITEM_GROUP));
    }

    @Nonnull
    @Override
    public ActionResultType onItemUse(@Nonnull ItemUseContext context) {
        BlockPos pos = context.getPos();
        World world = context.getWorld();
        Block block = world.getBlockState(pos).getBlock();
        if(block.matchesBlock(Blocks.FURNACE)) {
            Block toCheck = world.getBlockState(pos.north()).getBlock();
            if(toCheck.matchesBlock(Blocks.CRAFTING_TABLE)) {
                replaceBlocks(pos, Direction.NORTH, world);
                return ActionResultType.SUCCESS;
            }
            toCheck = world.getBlockState(pos.south()).getBlock();
            if (toCheck.matchesBlock(Blocks.CRAFTING_TABLE)) {
                replaceBlocks(pos, Direction.SOUTH, world);
                return ActionResultType.SUCCESS;
            }
            toCheck = world.getBlockState(pos.east()).getBlock();
            if (toCheck.matchesBlock(Blocks.CRAFTING_TABLE)) {
                replaceBlocks(pos, Direction.EAST, world);
                return ActionResultType.SUCCESS;
            }
            toCheck = world.getBlockState(pos.west()).getBlock();
            if (toCheck.matchesBlock(Blocks.CRAFTING_TABLE)) {
                replaceBlocks(pos, Direction.WEST, world);
                return ActionResultType.SUCCESS;
            }
        } else if (block.getBlock().matchesBlock(Blocks.CRAFTING_TABLE)) {
            Block toCheck = world.getBlockState(pos.north()).getBlock();
            if(toCheck.matchesBlock(Blocks.FURNACE)) {
                replaceBlocks(pos, Direction.NORTH, world);
                return ActionResultType.SUCCESS;
            }
            toCheck = world.getBlockState(pos.south()).getBlock();
            if (toCheck.matchesBlock(Blocks.FURNACE)) {
                replaceBlocks(pos, Direction.SOUTH, world);
                return ActionResultType.SUCCESS;
            }
            toCheck = world.getBlockState(pos.east()).getBlock();
            if (toCheck.matchesBlock(Blocks.FURNACE)) {
                replaceBlocks(pos, Direction.EAST, world);
                return ActionResultType.SUCCESS;
            }
            toCheck = world.getBlockState(pos.west()).getBlock();
            if (toCheck.matchesBlock(Blocks.FURNACE)) {
                replaceBlocks(pos, Direction.WEST, world);
                return ActionResultType.SUCCESS;
            }
        }
        return super.onItemUse(context);
    }

    private void replaceBlocks(BlockPos firstBlock, Direction secondBlock, @Nonnull World world) {
        world.setBlockState(firstBlock, UtilcraftBlocks.SUSHI_MAKER.getDefaultState().with(SushiMaker.FACING, secondBlock));
        world.setBlockState(firstBlock.add(secondBlock.getDirectionVec()), UtilcraftBlocks.SUSHI_MAKER.getDefaultState().with(SushiMaker.FACING, secondBlock.getOpposite()));
    }
}
