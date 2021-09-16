package net.petersil98.utilcraft.enchantments;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.petersil98.utilcraft.Utilcraft;

public class UtilcraftEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Utilcraft.MOD_ID);

    public static final RegistryObject<Enchantment> BEHEADING = ENCHANTMENTS.register("beheading_enchantment", BeheadingEnchantment::new);
}
