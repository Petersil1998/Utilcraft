package net.petersil98.utilcraft.renderer;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;
import net.petersil98.utilcraft.blocks.DisenchantmentTableTile;
import net.petersil98.utilcraft.blocks.sakura.SignTileEntity;

public class ModTileEntityTypes {
    @ObjectHolder("utilcraft:mod_sign")
    public static TileEntityType<SignTileEntity> MOD_SIGN;

    @ObjectHolder("utilcraft:disenchantment_table")
    public static TileEntityType<DisenchantmentTableTile> DISENCHANTMENT_TABLE;
}
