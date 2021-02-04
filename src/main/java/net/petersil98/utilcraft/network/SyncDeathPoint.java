package net.petersil98.utilcraft.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;
import net.petersil98.utilcraft.data.capabilities.last_death.CapabilityLastDeath;
import net.petersil98.utilcraft.data.capabilities.last_death.ILastDeath;

import java.util.function.Supplier;

public class SyncDeathPoint {

    private final BlockPos deathPoint;
    private final ResourceLocation dimension;

    public SyncDeathPoint(BlockPos deathPoint, ResourceLocation dimension) {
        this.deathPoint = deathPoint;
        this.dimension = dimension;
    }

    public SyncDeathPoint(PacketBuffer packetBuffer) {
        deathPoint = packetBuffer.readBlockPos();
        dimension =  packetBuffer.readResourceLocation();
    }

    public void encode(PacketBuffer buf) {
        buf.writeBlockPos(deathPoint);
        buf.writeResourceLocation(dimension);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientPlayerEntity player = Minecraft.getInstance().player;
            LazyOptional<ILastDeath> optional = player.getCapability(CapabilityLastDeath.LAST_DEATH_CAPABILITY);
            player.getCapability(CapabilityLastDeath.LAST_DEATH_CAPABILITY).ifPresent(iLastDeath -> {
                iLastDeath.setDeathPoint(deathPoint);
                iLastDeath.setDeathDimension(dimension);
            });
        });
        return true;
    }
}
