package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.petersil98.utilcraft.tile_entities.UtilcraftSignTileEntity;

import javax.annotation.Nullable;

public class SakuraWallSign extends WallSignBlock {

    public SakuraWallSign(Block sign) {
        super(AbstractBlock.Properties
                .of(Material.WOOD)
                .noCollission()
                .strength(1.0F)
                .sound(SoundType.WOOD)
                .dropsLike(sign)
                , WoodType.OAK)
        ;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new UtilcraftSignTileEntity();
    }
}
