package net.petersil98.utilcraft.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.Tags;
import net.petersil98.utilcraft.items.AbstractSuperTool;
import net.petersil98.utilcraft.items.RoseQuartzSuperHammer;
import net.petersil98.utilcraft.items.RoseQuartzSuperShovel;

import java.util.ArrayList;

public class VeinMinerUtils {
    public static boolean isSuperTool(Item item){
        return item instanceof RoseQuartzSuperHammer || item instanceof RoseQuartzSuperShovel;
    }

    public static void get3x3FieldAroundTargetedBlock(final PlayerEntity player, ArrayList<BlockPos> affectedBlocks)
    {
        final BlockRayTraceResult rayTrace = AbstractSuperTool.rayTracer(player.world, player, RayTraceContext.FluidMode.NONE);
        final BlockPos center = rayTrace.getPos();
        switch (rayTrace.getFace()) {
            case DOWN:
            case UP:
                affectedBlocks.add(center.west());
                affectedBlocks.add(center.east());
                affectedBlocks.add(center.north());
                affectedBlocks.add(center.south());

                affectedBlocks.add(center.west().north());
                affectedBlocks.add(center.west().south());
                affectedBlocks.add(center.east().north());
                affectedBlocks.add(center.east().south());
                break;
            case NORTH:
            case SOUTH:
                affectedBlocks.add(center.up());
                affectedBlocks.add(center.down());
                affectedBlocks.add(center.west());
                affectedBlocks.add(center.east());

                affectedBlocks.add(center.west().up());
                affectedBlocks.add(center.west().down());
                affectedBlocks.add(center.east().up());
                affectedBlocks.add(center.east().down());
                break;
            case EAST:
            case WEST:
                affectedBlocks.add(center.up());
                affectedBlocks.add(center.down());
                affectedBlocks.add(center.north());
                affectedBlocks.add(center.south());

                affectedBlocks.add(center.north().up());
                affectedBlocks.add(center.north().down());
                affectedBlocks.add(center.south().up());
                affectedBlocks.add(center.south().down());
                break;
        }
    }

    public static void getVein(BlockPos pos, ArrayList<BlockPos> vein, World world){
        if (!vein.contains(pos.south()) && areSameBlock(pos, pos.south(), world)) {
            vein.add(pos.south());
            getVein(pos.south(), vein, world);
        }
        if (!vein.contains(pos.east()) && areSameBlock(pos, pos.east(), world)) {
            vein.add(pos.east());
            getVein(pos.east(), vein, world);
        }
        if (!vein.contains(pos.north()) && areSameBlock(pos, pos.north(), world)) {
            vein.add(pos.north());
            getVein(pos.north(), vein, world);
        }
        if (!vein.contains(pos.west()) && areSameBlock(pos, pos.west(), world)) {
            vein.add(pos.west());
            getVein(pos.west(), vein, world);
        }
        if (!vein.contains(pos.up()) && areSameBlock(pos, pos.up(), world)) {
            vein.add(pos.up());
            getVein(pos.up(), vein, world);
        }
        if (!vein.contains(pos.down()) && areSameBlock(pos, pos.down(), world)) {
            vein.add(pos.down());
            getVein(pos.down(), vein, world);
        }
    }

    public static void getTree(BlockPos pos, ArrayList<BlockPos> tree, World world){
        BlockPos posToCheck = pos.south();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.east();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.north();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.west();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.up();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.down();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }

        posToCheck = pos.north().east();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.north().west();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.north().up();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.north().down();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.north().east().up();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.north().east().down();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.north().west().up();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.north().west().down();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }

        posToCheck = pos.south().east();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.south().west();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.south().up();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.south().down();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.south().east().up();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.south().east().down();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.south().west().up();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.south().west().down();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }

        posToCheck = pos.east().up();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.east().down();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.west().up();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.west().down();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
    }

    public static boolean playerCanHarvestBlock(BlockState block, ItemStack item, BlockPos pos, World world, PlayerEntity player){
        return ForgeHooks.canHarvestBlock(block, player, world, pos) || item.canHarvestBlock(block);
    }

    public static boolean areSameBlock(BlockPos pos1, BlockPos pos2, World world){
        return world.getBlockState(pos1).getBlock().equals(world.getBlockState(pos2).getBlock());
    }

    public static boolean isLogBlock(Block block){
        return block.isIn(BlockTags.LOGS);
    }

    public static boolean isOreBlock(Block block) {
        return block.isIn(Tags.Blocks.ORES);
    }
}
