package net.petersil98.utilcraft.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.petersil98.utilcraft.Utilcraft;

public class UtilcraftEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Utilcraft.MOD_ID);

    public static final RegistryObject<EntityType<EmptyEntity>> EMPTY_ENTITY = ENTITIES.register("empty_entity", () -> EntityType.Builder.<EmptyEntity>of(EmptyEntity::new, MobCategory.MISC).sized(1F, 1F).updateInterval(20).build("empty_entity"));

}
