package net.petersil98.utilcraft.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.petersil98.utilcraft.container.EntropyTableContainer;

import javax.annotation.Nonnull;

public class EntropyTable extends Block {

    public EntropyTable(Properties properties){
        super(properties);
    }

    @Nonnull
    @Override
    public InteractionResult use(@Nonnull BlockState state, @Nonnull Level world, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(world, pos));
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public MenuProvider getMenuProvider(@Nonnull BlockState state, @Nonnull Level world, @Nonnull BlockPos pos) {
        return new SimpleMenuProvider((id, inventory, player) -> new EntropyTableContainer(id, inventory, ContainerLevelAccess.create(world, pos)), new TextComponent("Sushi Maker"));
    }
}
