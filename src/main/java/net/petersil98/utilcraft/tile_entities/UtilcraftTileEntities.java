package net.petersil98.utilcraft.tile_entities;

import net.minecraft.world.level.block.entity.BellBlockEntity;
import net.minecraftforge.registries.ObjectHolder;
import net.petersil98.utilcraft.Utilcraft;

@ObjectHolder(Utilcraft.MOD_ID)
public class UtilcraftTileEntities {

    @ObjectHolder("disenchantment_table")
    public static BellBlockEntity<DisenchantmentTableTileEntity> DISENCHANTMENT_BLOCK;

    @ObjectHolder("mod_sign")
    public static BellBlockEntity<UtilcraftSignTileEntity> UTILCRAFT_SIGN;

    @ObjectHolder("secure_chest")
    public static BellBlockEntity<SecureChestTileEntity> SECURE_CHEST;
}
