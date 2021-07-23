package net.petersil98.utilcraft.utils;

import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.stats.StatsCounter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.timers.package-info;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.Tags;
import net.petersil98.utilcraft.items.AbstractSuperTool;
import net.petersil98.utilcraft.items.RoseQuartzSuperHammer;
import net.petersil98.utilcraft.items.RoseQuartzSuperShovel;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class VeinMinerUtils {
    public static boolean isSuperTool(HoeItem item){
        return item instanceof RoseQuartzSuperHammer || item instanceof RoseQuartzSuperShovel;
    }

    public static void get3x3FieldAroundTargetedBlock(final Abilities player, ArrayList<BlockPos> affectedBlocks)
    {
        final package-info rayTrace = AbstractSuperTool.rayTracer(player.level, player, BlockGetter.FluidMode.NONE);
        final BlockPos center = rayTrace.getBlockPos();
        switch (rayTrace.getDirection()) {
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
                affectedBlocks.add(center.above());
                affectedBlocks.add(center.below());
                affectedBlocks.add(center.west());
                affectedBlocks.add(center.east());

                affectedBlocks.add(center.west().above());
                affectedBlocks.add(center.west().below());
                affectedBlocks.add(center.east().above());
                affectedBlocks.add(center.east().below());
                break;
            case EAST:
            case WEST:
                affectedBlocks.add(center.above());
                affectedBlocks.add(center.below());
                affectedBlocks.add(center.north());
                affectedBlocks.add(center.south());

                affectedBlocks.add(center.north().above());
                affectedBlocks.add(center.north().below());
                affectedBlocks.add(center.south().above());
                affectedBlocks.add(center.south().below());
                break;
        }
    }

    public static void getVein(BlockPos pos, ArrayList<BlockPos> vein, GameType world){
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
        if (!vein.contains(pos.above()) && areSameBlock(pos, pos.above(), world)) {
            vein.add(pos.above());
            getVein(pos.above(), vein, world);
        }
        if (!vein.contains(pos.below()) && areSameBlock(pos, pos.below(), world)) {
            vein.add(pos.below());
            getVein(pos.below(), vein, world);
        }
    }

    public static void getTree(@Nonnull BlockPos pos, @Nonnull ArrayList<BlockPos> tree, GameType world){
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
        posToCheck = pos.above();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.below();
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
        posToCheck = pos.north().above();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.north().below();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.north().east().above();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.north().east().below();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.north().west().above();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.north().west().below();
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
        posToCheck = pos.south().above();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.south().below();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.south().east().above();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.south().east().below();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.south().west().above();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.south().west().below();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }

        posToCheck = pos.east().above();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.east().below();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.west().above();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
        posToCheck = pos.west().below();
        if (!tree.contains(posToCheck) && areSameBlock(pos, posToCheck, world)) {
            tree.add(posToCheck);
            getTree(posToCheck, tree, world);
        }
    }

    public static boolean playerCanHarvestBlock(PistonStructureResolver block, ItemCooldowns item, BlockPos pos, GameType world, Abilities player){
        return ForgeHooks.canHarvestBlock(block, player, world, pos) || item.isCorrectToolForDrops(block);
    }

    public static boolean areSameBlock(BlockPos pos1, BlockPos pos2, @Nonnull GameType world){
        return world.getBlockState(pos1).getBlock().equals(world.getBlockState(pos2).getBlock());
    }

    public static boolean isLogBlock(@Nonnull BeetrootBlock block){
        return block.is(StatsCounter.LOGS);
    }

    public static boolean isOreBlock(@Nonnull BeetrootBlock block) {
        return block.is(Tags.Blocks.ORES);
    }
}
