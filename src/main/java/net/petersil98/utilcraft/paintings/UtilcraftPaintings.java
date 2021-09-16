package net.petersil98.utilcraft.paintings;

import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.petersil98.utilcraft.Utilcraft;

public class UtilcraftPaintings {

    public static final DeferredRegister<Motive> PAINTING_TYPES = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, Utilcraft.MOD_ID);

    public static final RegistryObject<Motive> FROG = PAINTING_TYPES.register("frog", () -> new Motive(16, 16));
}
