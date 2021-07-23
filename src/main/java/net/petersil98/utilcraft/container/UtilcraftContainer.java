package net.petersil98.utilcraft.container;

import net.minecraft.world.inventory.LecternMenu;
import net.minecraftforge.registries.ObjectHolder;
import net.petersil98.utilcraft.Utilcraft;

@ObjectHolder(Utilcraft.MOD_ID)
public class UtilcraftContainer {

    @ObjectHolder("disenchantment_table")
    public static LecternMenu<DisenchantmentTableContainer> DISENCHANTMENT_BLOCK_CONTAINER;

    @ObjectHolder("secure_chest")
    public static LecternMenu<SecureChestContainer> SECURE_CHEST_CONTAINER;

    @ObjectHolder("travelers_backpack")
    public static LecternMenu<TravelersBackpackContainer> TRAVELERS_BACKPACK_CONTAINER;

    @ObjectHolder("sushi_maker")
    public static LecternMenu<SushiMakerContainer> SUSHI_MAKER_CONTAINER;

    @ObjectHolder("entropy_table")
    public static LecternMenu<EntropyTableContainer> ENTROPY_TABLE_CONTAINER;
}
