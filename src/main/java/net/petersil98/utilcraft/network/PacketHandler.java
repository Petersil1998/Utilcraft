package net.petersil98.utilcraft.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.petersil98.utilcraft.Main;

public class PacketHandler {

    private static final String PROTOCOL_VERSION = "1.0";
    private static int id = 0;
    private static SimpleChannel INSTANCE;

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Main.MOD_ID, "main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );
        INSTANCE.messageBuilder(MyPacket.class, id++)
                .encoder(MyPacket::encode)
                .decoder(MyPacket::new)
                .consumer(MyPacket::handle)
                .add();
    }

    public static void sendToClient(MyPacket myPacket, ServerPlayerEntity player){
        INSTANCE.sendTo(myPacket, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }
}
