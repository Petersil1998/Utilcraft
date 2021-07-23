package net.petersil98.utilcraft.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.GameType;
import net.minecraft.server.level.TicketType;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.data.capabilities.home.CapabilityHome;

import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.Set;

public class HomeCommand {

    public static void register(@Nonnull CommandDispatcher<CommandSourceStack> dispatcher){
        LiteralCommandNode<CommandSourceStack> commandNode = dispatcher.register(
            Commands.literal(Utilcraft.MOD_ID).then(Commands.literal("home")
                .executes(context -> {
                    teleportHome(context.getSource());
                    return 1;
                })
                .then(Commands.literal("set").executes(context -> {
                    setHome(context.getSource());
                    return 1;
                }))
            )
        );
        dispatcher.register(Commands.literal(Utilcraft.MOD_ID_SHORT).redirect(commandNode));
    }

    private static void setHome(@Nonnull CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        BlockPos position = player.blockPosition();
        player.getCapability(CapabilityHome.HOME_CAPABILITY).ifPresent(iHome -> {
            iHome.setHome(position);
            source.sendSuccess(new TranslatableComponent(String.format("home.%s.set_home", Utilcraft.MOD_ID)), false);
        });
    }

    private static void teleportHome(@Nonnull CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        player.getCapability(CapabilityHome.HOME_CAPABILITY).ifPresent(iHome -> {
            BlockPos home = iHome.getHome();
            if(home != null && !home.equals(BlockPos.ZERO)) {
                teleportToPos(player, home);
                source.sendSuccess(new TranslatableComponent(String.format("home.%s.teleported", Utilcraft.MOD_ID)), false);
            } else {
                source.sendSuccess(new TranslatableComponent(String.format("home.%s.not_set", Utilcraft.MOD_ID)), false);
            }
        });
    }

    private static void teleportToPos(@Nonnull ServerPlayer player, BlockPos position) {
        Set<ClientboundPlayerPositionPacket.RelativeArgument> set = EnumSet.noneOf(ClientboundPlayerPositionPacket.RelativeArgument.class);

        set.add(ClientboundPlayerPositionPacket.RelativeArgument.X_ROT);
        set.add(ClientboundPlayerPositionPacket.RelativeArgument.Y_ROT);

        BlockAndTintGetter chunkpos = new BlockAndTintGetter(position);
        player.getLevel().getChunkSource().addRegionTicket(TicketType.POST_TELEPORT, chunkpos, 1, player.getId());
        player.stopRiding();
        if (player.isSleeping()) {
            player.stopSleepInBed(true, true);
        }

        if (player.getServer().getLevel(GameType.OVERWORLD).equals(player.getLevel())) {
            player.connection.teleport(position.getX(), position.getY(), position.getZ(), 0, 0, set);
        } else {
            player.teleportTo(player.getServer().getLevel(GameType.OVERWORLD), position.getX(), position.getY(), position.getZ(), 0, 0);
        }

        player.setYHeadRot(0);

        if (!player.isFallFlying()) {
            player.setDeltaMovement(player.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
            player.setOnGround(true);
        }
    }
}
