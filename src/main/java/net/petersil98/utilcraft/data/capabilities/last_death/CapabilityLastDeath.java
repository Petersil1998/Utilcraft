package net.petersil98.utilcraft.data.capabilities.last_death;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.petersil98.utilcraft.data.capabilities.vein_miner.IVeinMiner;

public class CapabilityLastDeath {
    @CapabilityInject(ILastDeath.class)
    public static Capability<ILastDeath> LAST_DEATH_CAPABILITY = null;

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(ILastDeath.class);
    }
}
