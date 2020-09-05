package net.petersil98.utilcraft.commands;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;
import net.petersil98.utilcraft.data.ModWorldSavedData;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TrustedPlayersCommand {

    public static ArgumentBuilder<CommandSource, ?> register(){
        return Commands.literal("trust")
                .then(Commands.literal("grant").then(Commands.argument("player", EntityArgument.player()).executes(context -> {
                    grantTrust(context.getSource(), EntityArgument.getPlayer(context, "player"));
                    return 1;
                })))
                .then(Commands.literal("revoke").then(Commands.argument("player", EntityArgument.player()).executes(context -> {
                    revokeTrust(context.getSource(), EntityArgument.getPlayer(context, "player"));
                    return 1;
                })))
                .then(Commands.literal("list").executes(context -> {
                    sendListOfTrustedPlayers(context.getSource());
                    return 1;
                }));
    }

    private static void grantTrust(CommandSource source, ServerPlayerEntity affectedPlayer) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        ModWorldSavedData worldSavedData = ModWorldSavedData.get(player.getServerWorld());
        worldSavedData.addTrustedPlayer(player.getGameProfile().getId(), affectedPlayer.getGameProfile().getId(), affectedPlayer.getName().getString());
        player.sendMessage(new TranslationTextComponent("player_trusted.utilcraft.player_added", affectedPlayer.getName()), Util.DUMMY_UUID);
    }

    private static void revokeTrust(CommandSource source, PlayerEntity affectedPlayer) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        ModWorldSavedData worldSavedData = ModWorldSavedData.get(player.getServerWorld());
        worldSavedData.removedTrustedPlayer(player.getGameProfile().getId(), affectedPlayer.getGameProfile().getId());
        player.sendMessage(new TranslationTextComponent("player_trusted.utilcraft.player_removed", affectedPlayer.getName()), Util.DUMMY_UUID);
    }

    private static void sendListOfTrustedPlayers(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        ModWorldSavedData worldSavedData = ModWorldSavedData.get(player.getServerWorld());
        List<String> trustedPlayers = worldSavedData.getTrustedPlayerNames(player.getGameProfile().getId());
        source.sendFeedback(new TranslationTextComponent("player_trusted.utilcraft.list", String.join(", ", trustedPlayers)), true);
    }
}
