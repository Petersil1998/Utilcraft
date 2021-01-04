package net.petersil98.utilcraft.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.petersil98.utilcraft.Utilcraft;

public class PacketHandler {

    private static final String PROTOCOL_VERSION = "1.0";
    private static int id = 0;
    private static SimpleChannel INSTANCE;

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Utilcraft.MOD_ID, "main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );
        INSTANCE.messageBuilder(ToggleVeinMiner.class, id++)
                .encoder(ToggleVeinMiner::encode)
                .decoder(ToggleVeinMiner::new)
                .consumer(ToggleVeinMiner::handle)
                .add();

        INSTANCE.messageBuilder(PlayerDeathStats.class, id++)
                .encoder(PlayerDeathStats::encode)
                .decoder(PlayerDeathStats::new)
                .consumer(PlayerDeathStats::handle)
                .add();
    }

    public static void sendToServer(ToggleVeinMiner myPacket){
        INSTANCE.sendToServer(myPacket);
    }

    public static void sendToClients(PlayerDeathStats myPacket) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), myPacket);
    }
}
