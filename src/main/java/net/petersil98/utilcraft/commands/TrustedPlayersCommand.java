package net.petersil98.utilcraft.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.Util;
import net.minecraft.network.chat.TranslatableComponent;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.data.SimplePlayer;
import net.petersil98.utilcraft.data.UtilcraftWorldSavedData;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class TrustedPlayersCommand {

    public static void register(@Nonnull CommandDispatcher<CommandSourceStack> dispatcher){
        LiteralCommandNode<CommandSourceStack> commandNode = dispatcher.register(
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

    private static void grantTrust(@Nonnull CommandSourceStack source, @Nonnull ServerPlayer affectedPlayer) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        UtilcraftWorldSavedData worldSavedData = UtilcraftWorldSavedData.get(player.getLevel());
        worldSavedData.addTrustedPlayer(player.getGameProfile().getId(), new SimplePlayer(affectedPlayer.getName().getString(), affectedPlayer.getGameProfile().getId()));
        player.sendMessage(new TranslatableComponent(String.format("player_trusted.%s.player_added", Utilcraft.MOD_ID), affectedPlayer.getName()), Util.NIL_UUID);
    }

    private static void revokeTrust(@Nonnull CommandSourceStack source, @Nonnull Abilities affectedPlayer) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        UtilcraftWorldSavedData worldSavedData = UtilcraftWorldSavedData.get(player.getLevel());
        worldSavedData.removedTrustedPlayer(player.getGameProfile().getId(), affectedPlayer.getGameProfile().getId());
        player.sendMessage(new TranslatableComponent(String.format("player_trusted.%s.player_removed", Utilcraft.MOD_ID), affectedPlayer.getName()), Util.NIL_UUID);
    }

    private static void sendListOfTrustedPlayers(@Nonnull CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        UtilcraftWorldSavedData worldSavedData = UtilcraftWorldSavedData.get(player.getLevel());
        List<SimplePlayer> trustedPlayers = worldSavedData.getTrustedPlayers(player.getGameProfile().getId());
        List<String> names = new ArrayList<>();
        trustedPlayers.forEach(simplePlayer -> names.add(simplePlayer.getUsername()));
        source.sendSuccess(new TranslatableComponent(String.format("player_trusted.%s.list", Utilcraft.MOD_ID), String.join(", ", names)), false);
    }
}
