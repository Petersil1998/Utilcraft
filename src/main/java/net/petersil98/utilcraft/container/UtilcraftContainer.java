package net.petersil98.utilcraft.container;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.petersil98.utilcraft.Utilcraft;

public class UtilcraftContainer {

    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.Keys.CONTAINER_TYPES, Utilcraft.MOD_ID);

    public static final RegistryObject<MenuType<DisenchantmentTableContainer>> DISENCHANTMENT_BLOCK_CONTAINER = CONTAINER_TYPES.register("disenchantment_table", () -> IForgeMenuType.create((windowId, inv, data) -> new DisenchantmentTableContainer(windowId, inv)));
    public static final RegistryObject<MenuType<SecureChestContainer>> SECURE_CHEST_CONTAINER = CONTAINER_TYPES.register("secure_chest", () -> IForgeMenuType.create((windowId, inv, data) -> new SecureChestContainer(windowId, inv)));
    public static final RegistryObject<MenuType<SushiMakerContainer>> SUSHI_MAKER_CONTAINER = CONTAINER_TYPES.register("sushi_maker", () -> IForgeMenuType.create((windowId, inv, data) -> new SushiMakerContainer(windowId, inv)));
    public static final RegistryObject<MenuType<TravelersBackpackContainer>> TRAVELERS_BACKPACK_CONTAINER = CONTAINER_TYPES.register("travelers_backpack", () -> IForgeMenuType.create(TravelersBackpackContainer::new));
    public static final RegistryObject<MenuType<EntropyTableContainer>> ENTROPY_TABLE_CONTAINER = CONTAINER_TYPES.register("entropy_table", () -> IForgeMenuType.create((windowId, inv, data) -> new EntropyTableContainer(windowId, inv)));
}
