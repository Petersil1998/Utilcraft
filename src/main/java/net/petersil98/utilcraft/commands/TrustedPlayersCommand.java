package net.petersil98.utilcraft.commands;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.petersil98.utilcraft.data.trustedPlayers.CapabilityTrustedPlayers;

public class TrustedPlayersCommand {

    public static ArgumentBuilder<CommandSource, ?> register(){
        return Commands.literal("trust")
                .then(Commands.literal("grant").then(Commands.argument("player", EntityArgument.player()).executes((context -> {
                    grantTrust(context.getSource(), EntityArgument.getPlayer(context, "player"));
                    return 1;
                }))))
                .then(Commands.literal("revoke").then(Commands.argument("player", EntityArgument.player()).executes((context -> {
                    revokeTrust(context.getSource(), EntityArgument.getPlayer(context, "player"));
                    return 1;
                }))));
    }

    private static void grantTrust(CommandSource source, PlayerEntity playerEntity) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        player.getCapability(CapabilityTrustedPlayers.TRUSTED_PLAYERS_CAPABILITY).ifPresent(iTrustedPlayers -> {
            if(!iTrustedPlayers.getTrustedPlayers().contains(playerEntity)){
                iTrustedPlayers.addTrustedPlayer(playerEntity);
            }
        });
    }

    private static void revokeTrust(CommandSource source, PlayerEntity playerEntity) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        player.getCapability(CapabilityTrustedPlayers.TRUSTED_PLAYERS_CAPABILITY).ifPresent(iTrustedPlayers -> {
            if(iTrustedPlayers.getTrustedPlayers().contains(playerEntity)){
                iTrustedPlayers.removeTrustedPlayer(playerEntity);
            }
        });
    }
}
