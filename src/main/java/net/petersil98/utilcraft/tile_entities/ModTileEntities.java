package net.petersil98.utilcraft.tile_entities;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModTileEntities {

    @ObjectHolder("utilcraft:disenchantment_table")
    public static TileEntityType<DisenchantmentTableTileEntity> DISENCHANTMENT_BLOCK;

    @ObjectHolder("utilcraft:mod_sign")
    public static TileEntityType<ModSignTileEntity> MOD_SIGN;

    @ObjectHolder("utilcraft:secure_chest")
    public static TileEntityType<SecureChestTileEntity> SECURE_CHEST;
}
