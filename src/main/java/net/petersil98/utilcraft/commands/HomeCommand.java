package net.petersil98.utilcraft.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayerPositionLookPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.TicketType;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.data.capabilities.home.CapabilityHome;

import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.Set;

public class HomeCommand {

    public static void register(@Nonnull CommandDispatcher<CommandSource> dispatcher){
        LiteralCommandNode<CommandSource> commandNode = dispatcher.register(
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

    private static void setHome(@Nonnull CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        BlockPos position = player.blockPosition();
        player.getCapability(CapabilityHome.HOME_CAPABILITY).ifPresent(iHome -> {
            iHome.setHome(position);
            source.sendSuccess(new TranslationTextComponent(String.format("home.%s.set_home", Utilcraft.MOD_ID)), false);
        });
    }

    private static void teleportHome(@Nonnull CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        player.getCapability(CapabilityHome.HOME_CAPABILITY).ifPresent(iHome -> {
            BlockPos home = iHome.getHome();
            if(home != null && !home.equals(BlockPos.ZERO)) {
                teleportToPos(player, home);
                source.sendSuccess(new TranslationTextComponent(String.format("home.%s.teleported", Utilcraft.MOD_ID)), false);
            } else {
                source.sendSuccess(new TranslationTextComponent(String.format("home.%s.not_set", Utilcraft.MOD_ID)), false);
            }
        });
    }

    private static void teleportToPos(@Nonnull ServerPlayerEntity player, BlockPos position) {
        Set<SPlayerPositionLookPacket.Flags> set = EnumSet.noneOf(SPlayerPositionLookPacket.Flags.class);

        set.add(SPlayerPositionLookPacket.Flags.X_ROT);
        set.add(SPlayerPositionLookPacket.Flags.Y_ROT);

        ChunkPos chunkpos = new ChunkPos(position);
        player.getLevel().getChunkSource().addRegionTicket(TicketType.POST_TELEPORT, chunkpos, 1, player.getId());
        player.stopRiding();
        if (player.isSleeping()) {
            player.stopSleepInBed(true, true);
        }

        if (player.getServer().getLevel(World.OVERWORLD).equals(player.getLevel())) {
            player.connection.teleport(position.getX(), position.getY(), position.getZ(), 0, 0, set);
        } else {
            player.teleportTo(player.getServer().getLevel(World.OVERWORLD), position.getX(), position.getY(), position.getZ(), 0, 0);
        }

        player.setYHeadRot(0);

        if (!player.isFallFlying()) {
            player.setDeltaMovement(player.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
            player.setOnGround(true);
        }
    }
}
