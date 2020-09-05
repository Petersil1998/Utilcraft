package net.petersil98.utilcraft.data.tileEntityOwner;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class TileEntityOwnerProvider implements ICapabilitySerializable<CompoundNBT> {

    private final DefaultTileEntityOwner owner = new DefaultTileEntityOwner();
    private final LazyOptional<ITileEntityOwner> ownerOptional = LazyOptional.of(() -> owner);

    public void invalidate(){
        ownerOptional.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        return ownerOptional.cast();
    }

    @Override
    public CompoundNBT serializeNBT() {
        if(CapabilityTileEntityOwner.OWNER_CAPABILITY == null){
            return new CompoundNBT();
        } else {
            return (CompoundNBT) CapabilityTileEntityOwner.OWNER_CAPABILITY.writeNBT(owner, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if(CapabilityTileEntityOwner.OWNER_CAPABILITY != null){
            CapabilityTileEntityOwner.OWNER_CAPABILITY.readNBT(owner, null, nbt);
        }
    }
}
