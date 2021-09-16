package net.petersil98.utilcraft.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import net.petersil98.utilcraft.utils.PlayerUtils;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class SyncDeathPoint {

    private BlockPos deathPoint;
    private ResourceLocation dimension;

    public SyncDeathPoint(BlockPos deathPoint, ResourceLocation dimension) {
        this.deathPoint = deathPoint;
        this.dimension = dimension;
    }

    public SyncDeathPoint(@Nonnull FriendlyByteBuf packetBuffer) {
        try {
            this.deathPoint = packetBuffer.readBlockPos();
            this.dimension = packetBuffer.readResourceLocation();
        } catch (IndexOutOfBoundsException ignored) {
            //DeathPoint and Dimension are getting reset to null because Player ran over the deathPoint
            this.deathPoint = null;
            this.dimension = null;
        }
    }

    public void encode(@Nonnull FriendlyByteBuf buf) {
        if(this.deathPoint != null && this.dimension != null) {
            buf.writeBlockPos(this.deathPoint);
            buf.writeResourceLocation(this.dimension);
        }
    }

    public boolean handle(@Nonnull Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> PlayerUtils.setLastDeath(this.deathPoint, this.dimension));
        return true;
    }
}
