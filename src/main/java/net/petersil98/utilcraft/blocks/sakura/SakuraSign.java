package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.petersil98.utilcraft.tile_entities.UtilcraftSignTileEntity;

import javax.annotation.Nullable;

public class SakuraSign extends StandingSignBlock {

    public SakuraSign(Properties properties) {
        super(properties, WoodType.OAK);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new UtilcraftSignTileEntity();
    }
}
