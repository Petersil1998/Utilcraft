package net.petersil98.utilcraft.container;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;
import net.petersil98.utilcraft.container.DisenchantmentTableContainer;

public class ModContainer {

    @ObjectHolder("utilcraft:disenchantment_table")
    public static ContainerType<DisenchantmentTableContainer> DISENCHANTMENT_BLOCK_CONTAINER;

    @ObjectHolder("utilcraft:secure_chest")
    public static ContainerType<SecureChestContainer> SECURE_CHEST_CONTAINER;

    @ObjectHolder("utilcraft:travelers_backpack")
    public static ContainerType<TravelersBackpackContainer> TRAVELERS_BACKPACK_CONTAINER;
}
