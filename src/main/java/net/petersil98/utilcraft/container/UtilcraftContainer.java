package net.petersil98.utilcraft.container;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;
import net.petersil98.utilcraft.Utilcraft;

@ObjectHolder(Utilcraft.MOD_ID)
public class UtilcraftContainer {

    @ObjectHolder("disenchantment_table")
    public static ContainerType<DisenchantmentTableContainer> DISENCHANTMENT_BLOCK_CONTAINER;

    @ObjectHolder("secure_chest")
    public static ContainerType<SecureChestContainer> SECURE_CHEST_CONTAINER;

    @ObjectHolder("travelers_backpack")
    public static ContainerType<TravelersBackpackContainer> TRAVELERS_BACKPACK_CONTAINER;

    @ObjectHolder("sushi_maker")
    public static ContainerType<SushiMakerContainer> SUSHI_MAKER_CONTAINER;

    @ObjectHolder("entropy_table")
    public static ContainerType<EntropyTableContainer> ENTROPY_TABLE_CONTAINER;
}
