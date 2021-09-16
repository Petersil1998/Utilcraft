package net.petersil98.utilcraft.loot_modifiers;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.petersil98.utilcraft.Utilcraft;

public class UtilcraftLootModifiers {

    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, Utilcraft.MOD_ID);

    public static final RegistryObject<GlobalLootModifierSerializer<BeheadingModifier>> BEHEADING_SERIALIZER = LOOT_MODIFIER_SERIALIZERS.register("beheading", BeheadingModifier.Serializer::new);
}
