package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.entity.EntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class SakuraTrapdoor extends TrapDoorBlock {

    public SakuraTrapdoor() {
        super(BlockBehaviour.Properties
                .of(Material.WOOD, MaterialColor.WOOD)
                .strength(3.0F)
                .sound(SoundType.WOOD)
                .noOcclusion()
                .isValidSpawn(SakuraTrapdoor::neverAllowSpawn)
        );
    }

    private static Boolean neverAllowSpawn(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> entity) {
        return false;
    }
}
