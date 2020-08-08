package net.petersil98.utilcraft.event;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.petersil98.utilcraft.items.AbstractSuperTool;
import net.petersil98.utilcraft.items.RoseQuartzSuperHammer;
import net.petersil98.utilcraft.items.RoseQuartzSuperShovel;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = "utilcraft")
public class EventHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onBlockBreak(final BlockEvent.BreakEvent event) {
        Block minedBlock = event.getState().getBlock();
        PlayerEntity player = event.getPlayer();
        World world = event.getWorld().getWorld();
        ItemStack mainItem = player.getHeldItemMainhand();
        if(playerCanHarvestBlock(event.getState(), mainItem, event.getPos(), world)){
            ArrayList<BlockPos> blocksToHarvest = new ArrayList<>();
            if(isSuperTool(mainItem.getItem())){
                get3x3FieldAroundTargetedBlock(player, blocksToHarvest);
            }
            if(minedBlock instanceof OreBlock || minedBlock instanceof RedstoneOreBlock) {
                getVein(event.getPos(), blocksToHarvest, world);
            }
            for (BlockPos blockpos: blocksToHarvest) {
                BlockState blockstate = world.getBlockState(blockpos);
                if(playerCanHarvestBlock(blockstate, mainItem, blockpos, world)) {
                    if (mainItem.getMaxDamage() > mainItem.getDamage() + 1) {
                        if (blockstate.removedByPlayer(world, blockpos, player, true, world.getFluidState(blockpos))) {
                            Block block = blockstate.getBlock();
                            block.harvestBlock(world, player, blockpos, blockstate, null, player.getHeldItemMainhand());
                            block.onBlockHarvested(world, blockpos, blockstate, player);
                            if (!blockpos.equals(event.getPos())) {
                                player.getHeldItemMainhand().damageItem(1, player, (onBroken) -> onBroken.sendBreakAnimation(player.getActiveHand()));
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean isSuperTool(Item mainItem){
        return mainItem instanceof RoseQuartzSuperHammer || mainItem instanceof RoseQuartzSuperShovel;
    }

    private static void get3x3FieldAroundTargetedBlock(final PlayerEntity player, ArrayList<BlockPos> affectedBlocks)
    {
        final BlockRayTraceResult rayTrace = AbstractSuperTool.rayTracer(player.world, player, RayTraceContext.FluidMode.ANY);
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

    private static void getVein(BlockPos pos, ArrayList<BlockPos> vein, World world){
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

    private static boolean playerCanHarvestBlock(BlockState block, ItemStack item, BlockPos pos, World world){
        return ForgeHooks.canToolHarvestBlock(world, pos, item) || item.canHarvestBlock(block);
    }

    private static boolean areSameBlock(BlockPos pos1, BlockPos po2, World world){
        return world.getBlockState(pos1).getBlock().equals(world.getBlockState(po2).getBlock());
    }
}
