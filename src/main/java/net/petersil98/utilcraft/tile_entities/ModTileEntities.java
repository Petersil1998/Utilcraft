package net.petersil98.utilcraft.tile_entities;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;
import net.petersil98.utilcraft.Main;

@ObjectHolder(Main.MOD_ID)
public class ModTileEntities {

    @ObjectHolder("disenchantment_table")
    public static TileEntityType<DisenchantmentTableTileEntity> DISENCHANTMENT_BLOCK;

    @ObjectHolder("mod_sign")
    public static TileEntityType<ModSignTileEntity> MOD_SIGN;

    @ObjectHolder("secure_chest")
    public static TileEntityType<SecureChestTileEntity> SECURE_CHEST;
}
