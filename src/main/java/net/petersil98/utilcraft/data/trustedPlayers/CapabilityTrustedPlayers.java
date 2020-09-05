package net.petersil98.utilcraft.data.trustedPlayers;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.UUID;

public class CapabilityTrustedPlayers {

    @CapabilityInject(ITrustedPlayers.class)
    public static Capability<ITrustedPlayers> TRUSTED_PLAYERS_CAPABILITY;

    public static void register(){
        CapabilityManager.INSTANCE.register(ITrustedPlayers.class, new Capability.IStorage<ITrustedPlayers>() {

            @Nonnull
            @Override
            public INBT writeNBT(Capability<ITrustedPlayers> capability, ITrustedPlayers instance, Direction side) {
                CompoundNBT compound = new CompoundNBT(); //your main compound
                ListNBT owners = new ListNBT(); //list which will store your data
                for(PlayerEntity player: instance.getTrustedPlayers()){
                    CompoundNBT tag = new CompoundNBT();
                    tag.putUniqueId("player", player.getGameProfile().getId());
                    owners.add(tag);
                }
                compound.put("trustedPlayers", owners);
                return compound;
            }

            @Override
            public void readNBT(Capability<ITrustedPlayers> capability, ITrustedPlayers instance, Direction side, INBT nbt) {
                try {
                    ArrayList<PlayerEntity> players = new ArrayList<>();
                    ListNBT owners = (ListNBT)((CompoundNBT) nbt).get("trustedPlayers");
                    for (INBT owner: owners) {
                        UUID playerUuid = ((CompoundNBT) owner).getUniqueId("player");
                        players.add(Minecraft.getInstance().world.getServer().getPlayerList().getPlayerByUUID(playerUuid));
                    }
                    instance.setTrustedPlayers(players);
                } catch (Exception e){
                    instance.setTrustedPlayers(Lists.newArrayList());
                }
            }
        }, DefaultTrustedPlayers::new);
    }
}
