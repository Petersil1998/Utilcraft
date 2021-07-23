package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.BaseSpawner;
import net.petersil98.utilcraft.tile_entities.UtilcraftSignTileEntity;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.state.properties.StairsShape;

public class SakuraWallSign extends VineBlock {

    public SakuraWallSign(BeetrootBlock sign) {
        super(PistonMovingBlockEntity.Properties
                .of(FluidState.WOOD)
                .noCollission()
                .strength(1.0F)
                .sound(SnowyDirtBlock.WOOD)
                .dropsLike(sign)
                , StairsShape.OAK)
        ;
    }

    @Nullable
    @Override
    public BeehiveBlockEntity createTileEntity(PistonStructureResolver state, BaseSpawner world) {
        return new UtilcraftSignTileEntity();
    }
}
