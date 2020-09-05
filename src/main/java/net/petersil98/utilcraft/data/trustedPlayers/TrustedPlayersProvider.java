package net.petersil98.utilcraft.data.trustedPlayers;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class TrustedPlayersProvider implements ICapabilitySerializable<CompoundNBT> {

    private final DefaultTrustedPlayers trustedPlayers = new DefaultTrustedPlayers();
    private final LazyOptional<ITrustedPlayers> trustedPlayersOptional = LazyOptional.of(() -> trustedPlayers);

    public void invalidate(){
        trustedPlayersOptional.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        return trustedPlayersOptional.cast();
    }

    @Override
    public CompoundNBT serializeNBT() {
        if(CapabilityTrustedPlayers.TRUSTED_PLAYERS_CAPABILITY == null){
            return new CompoundNBT();
        } else {
            return (CompoundNBT) CapabilityTrustedPlayers.TRUSTED_PLAYERS_CAPABILITY.writeNBT(trustedPlayers, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if(CapabilityTrustedPlayers.TRUSTED_PLAYERS_CAPABILITY != null){
            CapabilityTrustedPlayers.TRUSTED_PLAYERS_CAPABILITY.readNBT(trustedPlayers, null, nbt);
        }
    }
}
