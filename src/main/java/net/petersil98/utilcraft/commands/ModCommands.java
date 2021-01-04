package net.petersil98.utilcraft.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.petersil98.utilcraft.Utilcraft;

public class ModCommands {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        LiteralCommandNode<CommandSource> command = dispatcher.register(
                Commands.literal(Utilcraft.MOD_ID).then(TrustedPlayersCommand.register())
                .then(HomeCommand.register())
        );
    }
}
