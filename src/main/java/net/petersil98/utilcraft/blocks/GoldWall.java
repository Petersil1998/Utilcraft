package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.TwistingVinesPlant;

public class GoldWall extends TwistingVinesPlant {

    public GoldWall() {
        super(PistonMovingBlockEntity.Properties.copy(new GoldBrick()));
    }
}
