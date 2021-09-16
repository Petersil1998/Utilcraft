package net.petersil98.utilcraft.data.capabilities.vein_miner;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class CapabilityVeinMiner {
    @CapabilityInject(IVeinMiner.class)
    public static Capability<IVeinMiner> VEIN_MINER_CAPABILITY = null;

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(IVeinMiner.class);
    }
}
