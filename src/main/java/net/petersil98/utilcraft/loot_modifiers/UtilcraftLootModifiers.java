package net.petersil98.utilcraft.loot_modifiers;

import net.minecraftforge.registries.ObjectHolder;
import net.petersil98.utilcraft.Utilcraft;

@ObjectHolder(Utilcraft.MOD_ID)
public class UtilcraftLootModifiers {

    @ObjectHolder("beheading")
    public static BeheadingModifier.Serializer BEHEADING_SERIALIZER;
}
