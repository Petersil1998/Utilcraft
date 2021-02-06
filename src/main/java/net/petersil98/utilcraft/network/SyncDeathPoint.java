package net.petersil98.utilcraft.network;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import net.petersil98.utilcraft.utils.PlayerUtils;

import java.util.function.Supplier;

public class SyncDeathPoint {

    private BlockPos deathPoint;
    private ResourceLocation dimension;

    public SyncDeathPoint(BlockPos deathPoint, ResourceLocation dimension) {
        this.deathPoint = deathPoint;
        this.dimension = dimension;
    }

    public SyncDeathPoint(PacketBuffer packetBuffer) {
        try {
            deathPoint = packetBuffer.readBlockPos();
            dimension = packetBuffer.readResourceLocation();
        } catch (IndexOutOfBoundsException ignored) {
            deathPoint = null;
            dimension = null;
        }
    }

    public void encode(PacketBuffer buf) {
        if(deathPoint != null && dimension != null) {
            buf.writeBlockPos(deathPoint);
            buf.writeResourceLocation(dimension);
        }
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> PlayerUtils.setLastDeath(deathPoint, dimension));
        return true;
    }
}
