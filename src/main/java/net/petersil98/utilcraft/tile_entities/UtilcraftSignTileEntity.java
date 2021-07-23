package net.petersil98.utilcraft.tile_entities;

import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.BellBlockEntity;

import javax.annotation.Nonnull;

public class UtilcraftSignTileEntity extends LidBlockEntity {

    @Nonnull
    @Override
    public BellBlockEntity<?> getType() {
        return UtilcraftTileEntities.UTILCRAFT_SIGN;
    }
}
