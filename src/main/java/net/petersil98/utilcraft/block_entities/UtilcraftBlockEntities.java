package net.petersil98.utilcraft.block_entities;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

public class UtilcraftBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES, Utilcraft.MOD_ID);

    public static final RegistryObject<BlockEntityType<DisenchantmentTableBlockEntity>> DISENCHANTMENT_BLOCK = BLOCK_ENTITY_TYPES.register("disenchantment_table", () -> BlockEntityType.Builder.of(DisenchantmentTableBlockEntity::new, UtilcraftBlocks.DISENCHANTMENT_TABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<UtilcraftSignBlockEntity>> UTILCRAFT_SIGN = BLOCK_ENTITY_TYPES.register("mod_sign", () -> BlockEntityType.Builder.of(UtilcraftSignBlockEntity::new, UtilcraftBlocks.SAKURA_SIGN.get(), UtilcraftBlocks.SAKURA_WALL_SIGN.get()).build(null));
    public static final RegistryObject<BlockEntityType<SecureChestBlockEntity>> SECURE_CHEST = BLOCK_ENTITY_TYPES.register("secure_chest", () -> BlockEntityType.Builder.of(SecureChestBlockEntity::new, UtilcraftBlocks.SECURE_CHEST.get()).build(null));
}
