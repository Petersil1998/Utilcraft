package net.petersil98.utilcraft.tile_entities;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;
import net.petersil98.utilcraft.Utilcraft;

@ObjectHolder(Utilcraft.MOD_ID)
public class UtilcraftTileEntities {

    @ObjectHolder("disenchantment_table")
    public static TileEntityType<DisenchantmentTableTileEntity> DISENCHANTMENT_BLOCK;

    @ObjectHolder("utilcraft")
    public static TileEntityType<UtilcraftSignTileEntity> UTILCRAFT_SIGN;

    @ObjectHolder("secure_chest")
    public static TileEntityType<SecureChestTileEntity> SECURE_CHEST;
}
