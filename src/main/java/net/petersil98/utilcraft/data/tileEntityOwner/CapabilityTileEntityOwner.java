package net.petersil98.utilcraft.data.tileEntityOwner;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
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
        CapabilityManager.INSTANCE.register(ITileEntityOwner.class, new Capability.IStorage<ITileEntityOwner>() {

            @Nonnull
            @Override
            public INBT writeNBT(Capability<ITileEntityOwner> capability, ITileEntityOwner instance, Direction side) {
                CompoundNBT tag = new CompoundNBT();
                if(instance.getOwner() != null) {
                    tag.putUniqueId("owner", instance.getOwner().getGameProfile().getId());
                }
                return tag;
            }

            @Override
            public void readNBT(Capability<ITileEntityOwner> capability, ITileEntityOwner instance, Direction side, INBT nbt) {
                try {
                    UUID playerUUID = ((CompoundNBT) nbt).getUniqueId("owner");
                    PlayerEntity player = Minecraft.getInstance().world.getServer().getPlayerList().getPlayerByUUID(playerUUID);
                    instance.setOwner(player);
                } catch (Exception e){
                    instance.setOwner(null);
                }
            }
        }, DefaultTileEntityOwner::new);
    }
}
