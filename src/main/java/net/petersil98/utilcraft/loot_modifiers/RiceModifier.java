package net.petersil98.utilcraft.loot_modifiers;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;

public class RiceModifier extends LootModifier {
    public RiceModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, @Nonnull LootContext context) {
        generatedLoot.add(new ItemStack(Items.ENDER_PEARL));
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<RiceModifier> {
        @Override
        public RiceModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditions) {
            return new RiceModifier(conditions);
        }

        @Override
        public JsonObject write(RiceModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}
