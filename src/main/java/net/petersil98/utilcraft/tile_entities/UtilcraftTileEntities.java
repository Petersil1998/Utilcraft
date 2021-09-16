package net.petersil98.utilcraft.tile_entities;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

public class UtilcraftTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Utilcraft.MOD_ID);

    public static final RegistryObject<TileEntityType<DisenchantmentTableTileEntity>> DISENCHANTMENT_BLOCK = TILE_ENTITIES.register("disenchantment_table", () -> TileEntityType.Builder.of(DisenchantmentTableTileEntity::new, UtilcraftBlocks.DISENCHANTMENT_TABLE.get()).build(null));
    public static final RegistryObject<TileEntityType<UtilcraftSignTileEntity>> UTILCRAFT_SIGN = TILE_ENTITIES.register("mod_sign", () -> TileEntityType.Builder.of(UtilcraftSignTileEntity::new, UtilcraftBlocks.SAKURA_SIGN.get(), UtilcraftBlocks.SAKURA_WALL_SIGN.get()).build(null));
    public static final RegistryObject<TileEntityType<SecureChestTileEntity>> SECURE_CHEST = TILE_ENTITIES.register("secure_chest", () -> TileEntityType.Builder.of(SecureChestTileEntity::new, UtilcraftBlocks.SECURE_CHEST.get()).build(null));
}
