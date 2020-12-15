package net.petersil98.utilcraft.container;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.container.DisenchantmentTableContainer;

@ObjectHolder(Main.MOD_ID)
public class ModContainer {

    @ObjectHolder("disenchantment_table")
    public static ContainerType<DisenchantmentTableContainer> DISENCHANTMENT_BLOCK_CONTAINER;

    @ObjectHolder("secure_chest")
    public static ContainerType<SecureChestContainer> SECURE_CHEST_CONTAINER;

    @ObjectHolder("travelers_backpack")
    public static ContainerType<TravelersBackpackContainer> TRAVELERS_BACKPACK_CONTAINER;
}
