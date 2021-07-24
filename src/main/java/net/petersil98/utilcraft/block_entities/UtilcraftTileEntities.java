package net.petersil98.utilcraft.block_entities;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ObjectHolder;
import net.petersil98.utilcraft.Utilcraft;

@ObjectHolder(Utilcraft.MOD_ID)
public class UtilcraftTileEntities {

    @ObjectHolder("disenchantment_table")
    public static BlockEntityType<DisenchantmentTableTileEntity> DISENCHANTMENT_BLOCK;

    @ObjectHolder("mod_sign")
    public static BlockEntityType<UtilcraftSignTileEntity> UTILCRAFT_SIGN;

    @ObjectHolder("secure_chest")
    public static BlockEntityType<SecureChestTileEntity> SECURE_CHEST;
}
