package net.petersil98.utilcraft.blocks;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.sakura.*;
import net.petersil98.utilcraft.blocks.sideslabs.*;

public class UtilcraftBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Utilcraft.MOD_ID);

    public static final RegistryObject<GoldBrick> GOLD_BRICK = BLOCKS.register("gold_brick", () -> new GoldBrick(Block.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3,6)));
    public static final RegistryObject<GoldStairs> GOLD_STAIRS = BLOCKS.register("gold_stairs", () -> new GoldStairs(Block.Properties.copy(GOLD_BRICK.get())));
    public static final RegistryObject<GoldSlab> GOLD_SLAB = BLOCKS.register("gold_slab", () -> new GoldSlab(Block.Properties.copy(GOLD_BRICK.get())));
    public static final RegistryObject<GoldWall> GOLD_WALL = BLOCKS.register("gold_wall", () -> new GoldWall(Block.Properties.copy(GOLD_BRICK.get())));
    public static final RegistryObject<CompressedCobblestone> COMPRESSED_COBBLESTONE = BLOCKS.register("compressed_cobblestone", () -> new CompressedCobblestone(Block.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<SilverOre> SILVER_ORE = BLOCKS.register("silver_ore", () -> new SilverOre(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<RoseQuartzOre> ROSE_QUARTZ_ORE = BLOCKS.register("rose_quartz_ore", () -> new RoseQuartzOre(Block.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<RoseQuartzBlock> ROSE_QUARTZ_BLOCK = BLOCKS.register("rose_quartz_block", () -> new RoseQuartzBlock(Block.Properties.of(Material.METAL, MaterialColor.COLOR_PINK).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<SideStoneSlab> SIDE_STONE_SLAB= BLOCKS.register("side_stone_slab", () -> new SideStoneSlab(Block.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<SideCobblestoneSlab> SIDE_COBBLESTONE_SLAB = BLOCKS.register("side_cobblestone_slab", () -> new SideCobblestoneSlab(Block.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<SideOakSlab> SIDE_OAK_SLAB = BLOCKS.register("side_oak_slab", () -> new SideOakSlab(Block.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<SideSpruceSlab> SIDE_SPRUCE_SLAB = BLOCKS.register("side_spruce_slab", () -> new SideSpruceSlab(Block.Properties.copy(Blocks.SPRUCE_WOOD)));
    public static final RegistryObject<SideBirchSlab> SIDE_BIRCH_SLAB = BLOCKS.register("side_birch_slab", () -> new SideBirchSlab(Block.Properties.copy(Blocks.BIRCH_WOOD)));
    public static final RegistryObject<SideJungleSlab> SIDE_JUNGLE_SLAB = BLOCKS.register("side_jungle_slab", () -> new SideJungleSlab(Block.Properties.copy(Blocks.JUNGLE_WOOD)));
    public static final RegistryObject<SideAcaciaSlab> SIDE_ACACIA_SLAB = BLOCKS.register("side_acacia_slab", () -> new SideAcaciaSlab(Block.Properties.copy(Blocks.ACACIA_WOOD)));
    public static final RegistryObject<SideDarkOakSlab> SIDE_DARK_OAK_SLAB = BLOCKS.register("side_dark_oak_slab", () -> new SideDarkOakSlab(Block.Properties.copy(Blocks.DARK_OAK_WOOD)));
    public static final RegistryObject<SakuraPlanks> SAKURA_PLANKS = BLOCKS.register("sakura_planks", () -> new SakuraPlanks(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<SideSakuraSlab> SIDE_SAKURA_SLAB = BLOCKS.register("side_sakura_slab", () -> new SideSakuraSlab(Block.Properties.copy(SAKURA_PLANKS.get())));
    public static final RegistryObject<SideGoldSlab> SIDE_GOLD_SLAB = BLOCKS.register("side_gold_slab", () -> new SideGoldSlab(Block.Properties.copy(GOLD_BRICK.get())));
    public static final RegistryObject<SakuraLeaves> SAKURA_LEAVES = BLOCKS.register("sakura_leaves", () -> new SakuraLeaves(Block.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(SakuraLeaves::allowsSpawnOnLeaves).isSuffocating(SakuraLeaves::isntSolid).isViewBlocking(SakuraLeaves::isntSolid)));
    public static final RegistryObject<SakuraLog> SAKURA_LOG = BLOCKS.register("sakura_log", () -> new SakuraLog(Block.Properties.of(Material.WOOD, (blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.COLOR_PINK : MaterialColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<SakuraSapling> SAKURA_SAPLING = BLOCKS.register("sakura_sapling", () -> new SakuraSapling(Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<SakuraSlab> SAKURA_SLAB = BLOCKS.register("sakura_slab", () -> new SakuraSlab(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<SakuraStairs> SAKURA_STAIRS = BLOCKS.register("sakura_stairs", () -> new SakuraStairs(Block.Properties.copy(SAKURA_PLANKS.get())));
    public static final RegistryObject<SakuraFence> SAKURA_FENCE = BLOCKS.register("sakura_fence", () -> new SakuraFence(Block.Properties.of(Material.WOOD, SAKURA_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<SakuraFenceGate> SAKURA_FENCE_GATE = BLOCKS.register("sakura_fence_gate", () -> new SakuraFenceGate(Block.Properties.of(Material.WOOD, SAKURA_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<SakuraPressurePlate> SAKURA_PRESSURE_PLATE = BLOCKS.register("sakura_pressure_plate", () -> new SakuraPressurePlate(Block.Properties.of(Material.WOOD, SAKURA_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<SakuraTrapdoor> SAKURA_TRAPDOOR = BLOCKS.register("sakura_trapdoor", () -> new SakuraTrapdoor(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(SakuraTrapdoor::neverAllowSpawn)));
    public static final RegistryObject<SakuraSign> SAKURA_SIGN = BLOCKS.register("sakura_sign", () -> new SakuraSign(Block.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<SakuraWallSign> SAKURA_WALL_SIGN = BLOCKS.register("sakura_wall_sign", () -> new SakuraWallSign(Block.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(SAKURA_SIGN)));
    public static final RegistryObject<SakuraButton> SAKURA_BUTTON = BLOCKS.register("sakura_button", () -> new SakuraButton(Block.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<SakuraDoor> SAKURA_DOOR = BLOCKS.register("sakura_door", () -> new SakuraDoor(Block.Properties.of(Material.WOOD, SAKURA_PLANKS.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<DisenchantmentTable> DISENCHANTMENT_TABLE = BLOCKS.register("disenchantment_table", () -> new DisenchantmentTable(Block.Properties.of(Material.STONE, MaterialColor.COLOR_RED).requiresCorrectToolForDrops().strength(5.0F, 1200.0F)));
    public static final RegistryObject<SecureChest> SECURE_CHEST = BLOCKS.register("secure_chest", () -> new SecureChest(Block.Properties.copy(Blocks.CHEST)));
    public static final RegistryObject<RedstoneStairs> REDSTONE_STAIRS = BLOCKS.register("redstone_stairs", () -> new RedstoneStairs(Block.Properties.copy(Blocks.REDSTONE_BLOCK)));
    public static final RegistryObject<RedstoneSlab> REDSTONE_SLAB = BLOCKS.register("redstone_slab", () -> new RedstoneSlab(Block.Properties.copy(Blocks.REDSTONE_BLOCK)));
    public static final RegistryObject<SideRedstoneSlab> SIDE_REDSTONE_SLAB = BLOCKS.register("side_redstone_slab", () -> new SideRedstoneSlab(Block.Properties.copy(Blocks.REDSTONE_BLOCK)));
    public static final RegistryObject<SushiMaker> SUSHI_MAKER = BLOCKS.register("sushi_maker", () -> new SushiMaker(Block.Properties.copy(Blocks.CRAFTING_TABLE)));
    public static final RegistryObject<GlassStairs> GLASS_STAIRS = BLOCKS.register("glass_stairs", () -> new GlassStairs(Block.Properties.copy(Blocks.GLASS)));
    public static final RegistryObject<FlowerPotBlock> POTTED_SAKURA_SAPLING = BLOCKS.register("potted_sakura_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT.delegate.get() , SAKURA_SAPLING, Block.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<SilverBlock> SILVER_BLOCK = BLOCKS.register("silver_block", () -> new SilverBlock(Block.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_WHITE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.METAL)));
    public static final RegistryObject<ChunkLoader> CHUNK_LOADER = BLOCKS.register("chunk_loader", () -> new ChunkLoader(Block.Properties.of(Material.STONE)));
    public static final RegistryObject<EntropyTable> ENTROPY_TABLE = BLOCKS.register("entropy_table", () -> new EntropyTable(Block.Properties.copy(Blocks.CRAFTING_TABLE)));
}