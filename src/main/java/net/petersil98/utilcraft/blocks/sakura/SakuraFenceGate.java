package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

public class SakuraFenceGate extends FenceGateBlock {

    public SakuraFenceGate() {
        super(Properties
                .of(Material.WOOD, new SakuraPlanks().defaultMaterialColor())
                .strength(2.0F, 3.0F)
                .sound(SoundType.WOOD)
        );
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }
}
