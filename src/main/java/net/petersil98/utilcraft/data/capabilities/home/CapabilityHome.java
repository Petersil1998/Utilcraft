package net.petersil98.utilcraft.data.capabilities.home;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class CapabilityHome {
    @CapabilityInject(IHome.class)
    public static Capability<IHome> HOME_CAPABILITY = null;

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(IHome.class);
    }
}
