package net.petersil98.utilcraft.tile_entities;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nonnull;

public class UtilcraftSignTileEntity extends SignTileEntity {

    @Nonnull
    @Override
    public TileEntityType<?> getType() {
        return UtilcraftTileEntities.UTILCRAFT_SIGN;
    }
}
