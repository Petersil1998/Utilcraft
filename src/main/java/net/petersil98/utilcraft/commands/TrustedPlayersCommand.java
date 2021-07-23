package net.petersil98.utilcraft.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.data.SimplePlayer;
import net.petersil98.utilcraft.data.UtilcraftWorldSavedData;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class TrustedPlayersCommand {

    public static void register(@Nonnull CommandDispatcher<CommandSource> dispatcher){
        LiteralCommandNode<CommandSource> commandNode = dispatcher.register(
                Commands.literal(Utilcraft.MOD_ID).then(Commands.literal("trust")
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
                        }))
                )
        );
        dispatcher.register(Commands.literal(Utilcraft.MOD_ID_SHORT).redirect(commandNode));
    }

    private static void grantTrust(@Nonnull CommandSource source, @Nonnull ServerPlayerEntity affectedPlayer) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        UtilcraftWorldSavedData worldSavedData = UtilcraftWorldSavedData.get(player.getLevel());
        worldSavedData.addTrustedPlayer(player.getGameProfile().getId(), new SimplePlayer(affectedPlayer.getName().getString(), affectedPlayer.getGameProfile().getId()));
        player.sendMessage(new TranslationTextComponent(String.format("player_trusted.%s.player_added", Utilcraft.MOD_ID), affectedPlayer.getName()), Util.NIL_UUID);
    }

    private static void revokeTrust(@Nonnull CommandSource source, @Nonnull PlayerEntity affectedPlayer) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        UtilcraftWorldSavedData worldSavedData = UtilcraftWorldSavedData.get(player.getLevel());
        worldSavedData.removedTrustedPlayer(player.getGameProfile().getId(), affectedPlayer.getGameProfile().getId());
        player.sendMessage(new TranslationTextComponent(String.format("player_trusted.%s.player_removed", Utilcraft.MOD_ID), affectedPlayer.getName()), Util.NIL_UUID);
    }

    private static void sendListOfTrustedPlayers(@Nonnull CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        UtilcraftWorldSavedData worldSavedData = UtilcraftWorldSavedData.get(player.getLevel());
        List<SimplePlayer> trustedPlayers = worldSavedData.getTrustedPlayers(player.getGameProfile().getId());
        List<String> names = new ArrayList<>();
        trustedPlayers.forEach(simplePlayer -> names.add(simplePlayer.getUsername()));
        source.sendSuccess(new TranslationTextComponent(String.format("player_trusted.%s.list", Utilcraft.MOD_ID), String.join(", ", names)), false);
    }
}
