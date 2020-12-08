package net.petersil98.utilcraft.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.petersil98.utilcraft.data.capabilities.vein_miner.CapabilityVeinMiner;

import java.util.UUID;
import java.util.function.Supplier;

public class ToggleVeinMiner {

    private final UUID player;
    private final boolean state;

    public ToggleVeinMiner(UUID player, boolean state) {
        this.player = player;
        this.state = state;
    }

    public ToggleVeinMiner(PacketBuffer packetBuffer) {
        this.player = packetBuffer.readUniqueId();
        this.state = packetBuffer.readBoolean();
    }

    public void encode(PacketBuffer buf) {
        buf.writeUniqueId(player);
        buf.writeBoolean(state);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity playerEntity = ctx.get().getSender();
            if(playerEntity != null) {
                playerEntity.getCapability(CapabilityVeinMiner.VEIN_MINER_CAPABILITY).ifPresent(iVeinMiner -> {
                    iVeinMiner.setVeinMiner(state);
                });
            }
        });
        return true;
    }
}
