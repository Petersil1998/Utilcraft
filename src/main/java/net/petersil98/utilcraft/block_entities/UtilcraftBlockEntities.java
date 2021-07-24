package net.petersil98.utilcraft.block_entities;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ObjectHolder;
import net.petersil98.utilcraft.Utilcraft;

@ObjectHolder(Utilcraft.MOD_ID)
public class UtilcraftBlockEntities {

    @ObjectHolder("disenchantment_table")
    public static BlockEntityType<DisenchantmentTableBlockEntity> DISENCHANTMENT_BLOCK;

    @ObjectHolder("mod_sign")
    public static BlockEntityType<UtilcraftSignBlockEntity> UTILCRAFT_SIGN;

    @ObjectHolder("secure_chest")
    public static BlockEntityType<SecureChestBlockEntity> SECURE_CHEST;
}
