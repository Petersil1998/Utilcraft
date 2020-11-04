package net.petersil98.utilcraft.renderer;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;
import net.petersil98.utilcraft.blocks.DisenchantmentTableTile;

public class ModTileEntityTypes {
    @ObjectHolder("utilcraft:mod_sign")
    public static TileEntityType<SignTileEntity> MOD_SIGN;

    @ObjectHolder("utilcraft:disenchantment_table")
    public static TileEntityType<DisenchantmentTableTile> DISENCHANTMENT_TABLE;
}
