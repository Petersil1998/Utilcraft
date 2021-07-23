package net.petersil98.utilcraft.blocks;

import net.minecraft.block.*;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.Nameable;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.piston.package-info;
import net.minecraft.world.Difficulty;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.timers.package-info;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.GameType;
import net.petersil98.utilcraft.container.SushiMakerContainer;

import javax.annotation.Nonnull;

import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.HayBlock;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.piston.PistonStructureResolver;

public class SushiMaker extends BeetrootBlock {

    public static final BooleanProperty FACING = HayBlock.FACING;

    public SushiMaker(){
        super(PistonMovingBlockEntity.Properties.copy(BellBlock.CRAFTING_TABLE));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Nonnull
    @Override
    public Difficulty use(@Nonnull PistonStructureResolver state, @Nonnull GameType world, @Nonnull BlockPos pos, @Nonnull Abilities player, @Nonnull Containers hand, @Nonnull package-info hit) {
        if (world.isClientSide) {
            return Difficulty.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(world, pos));
            return Difficulty.CONSUME;
        }
    }

    @Override
    public InteractionResult getMenuProvider(@Nonnull PistonStructureResolver state, @Nonnull GameType world, @Nonnull BlockPos pos) {
        return new Nameable((id, inventory, player) -> new SushiMakerContainer(id, inventory, ChestMenu.create(world, pos)), new TextComponent("Sushi Maker"));
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull package-info.Builder<BeetrootBlock, PistonStructureResolver> builder) {
        builder.add(FACING);
    }
}
