package net.petersil98.utilcraft.container;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.ObjectHolder;
import net.petersil98.utilcraft.Utilcraft;

@ObjectHolder(Utilcraft.MOD_ID)
public class UtilcraftContainer {

    @ObjectHolder("disenchantment_table")
    public static MenuType<DisenchantmentTableContainer> DISENCHANTMENT_BLOCK_CONTAINER;

    @ObjectHolder("secure_chest")
    public static MenuType<SecureChestContainer> SECURE_CHEST_CONTAINER;

    @ObjectHolder("travelers_backpack")
    public static MenuType<TravelersBackpackContainer> TRAVELERS_BACKPACK_CONTAINER;

    @ObjectHolder("sushi_maker")
    public static MenuType<SushiMakerContainer> SUSHI_MAKER_CONTAINER;

    @ObjectHolder("entropy_table")
    public static MenuType<EntropyTableContainer> ENTROPY_TABLE_CONTAINER;
}
