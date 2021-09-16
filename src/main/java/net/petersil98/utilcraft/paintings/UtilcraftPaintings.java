package net.petersil98.utilcraft.paintings;

import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.petersil98.utilcraft.Utilcraft;

public class UtilcraftPaintings {

    public static final DeferredRegister<PaintingType> PAINTING_TYPES = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, Utilcraft.MOD_ID);

    public static final RegistryObject<PaintingType> FROG = PAINTING_TYPES.register("frog", () -> new PaintingType(16, 16));
}
