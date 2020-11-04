package net.petersil98.utilcraft.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class ModCommands {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        LiteralCommandNode<CommandSource> commands = dispatcher.register(
                Commands.literal("utilcraft").requires(source -> source.hasPermissionLevel(2))
                        .then(RefillCommand.register())
        );

        LiteralCommandNode<CommandSource> command2 = dispatcher.register(
                Commands.literal("utilcraft").then(TrustedPlayersCommand.register())
        );
    }
}
