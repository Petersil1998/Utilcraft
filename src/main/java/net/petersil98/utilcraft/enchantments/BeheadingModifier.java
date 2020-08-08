package net.petersil98.utilcraft.enchantments;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;

public class BeheadingModifier extends LootModifier {
    public BeheadingModifier(ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        return generatedLoot;
    }

    private static class Serializer extends GlobalLootModifierSerializer<BeheadingModifier> {
        @Override
        public BeheadingModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn) {
            return new BeheadingModifier(conditionsIn);
        }
    }
}