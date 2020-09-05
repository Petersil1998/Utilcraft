package net.petersil98.utilcraft.data.tileEntityOwner;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nonnull;
import java.util.UUID;

public class CapabilityTileEntityOwner {

    @CapabilityInject(ITileEntityOwner.class)
    public static Capability<ITileEntityOwner> OWNER_CAPABILITY;

    public static void register(){
        CapabilityManager.INSTANCE.register(ITileEntityOwner.class, new Storage(), DefaultTileEntityOwner::new);
    }

    public static class Storage implements Capability.IStorage<ITileEntityOwner> {
        @Nonnull
        @Override
        public INBT writeNBT(Capability<ITileEntityOwner> capability, ITileEntityOwner instance, Direction side) {
            CompoundNBT tag = new CompoundNBT();
            if(instance.getOwner() != null) {
                tag.putUniqueId("owner", instance.getOwner());
            }
            return tag;
        }

        @Override
        public void readNBT(Capability<ITileEntityOwner> capability, ITileEntityOwner instance, Direction side, INBT nbt) {
            try {
                UUID playerUUID = ((CompoundNBT) nbt).getUniqueId("owner");
                instance.setOwner(playerUUID);
            } catch (Exception e){
                instance.setOwner(null);
            }
        }
    }
}
