package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.petersil98.utilcraft.tile_entities.UtilcraftSignTileEntity;

import javax.annotation.Nullable;

public class SakuraSign extends StandingSignBlock {

    public SakuraSign() {
        super(AbstractBlock.Properties
                .create(Material.WOOD)
                .doesNotBlockMovement()
                .hardnessAndResistance(1.0F)
                .sound(SoundType.WOOD),
                WoodType.OAK);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new UtilcraftSignTileEntity();
    }
}
