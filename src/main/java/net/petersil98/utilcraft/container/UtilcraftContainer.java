package net.petersil98.utilcraft.container;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.petersil98.utilcraft.Utilcraft;

public class UtilcraftContainer {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Utilcraft.MOD_ID);

    public static final RegistryObject<ContainerType<DisenchantmentTableContainer>> DISENCHANTMENT_BLOCK_CONTAINER = CONTAINERS.register("disenchantment_table", () -> IForgeContainerType.create((windowId, inv, data) -> new DisenchantmentTableContainer(windowId, inv)));
    public static final RegistryObject<ContainerType<SecureChestContainer>> SECURE_CHEST_CONTAINER = CONTAINERS.register("secure_chest", () -> IForgeContainerType.create((windowId, inv, data) -> new SecureChestContainer(windowId, inv)));
    public static final RegistryObject<ContainerType<SushiMakerContainer>> SUSHI_MAKER_CONTAINER = CONTAINERS.register("sushi_maker", () -> IForgeContainerType.create((windowId, inv, data) -> new SushiMakerContainer(windowId, inv)));
    public static final RegistryObject<ContainerType<TravelersBackpackContainer>> TRAVELERS_BACKPACK_CONTAINER = CONTAINERS.register("travelers_backpack", () -> IForgeContainerType.create(TravelersBackpackContainer::new));
    public static final RegistryObject<ContainerType<EntropyTableContainer>> ENTROPY_TABLE_CONTAINER = CONTAINERS.register("entropy_table", () -> IForgeContainerType.create((windowId, inv, data) -> new EntropyTableContainer(windowId, inv)));
}
