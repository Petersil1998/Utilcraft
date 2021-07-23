package net.petersil98.utilcraft.loot_modifiers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.world.item.enchantment.DiggingEnchantment;
import net.minecraft.world.effect.package-info;
import net.minecraft.world.entity.boss.BossMob;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.storage.loot.Deserializers;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.petersil98.utilcraft.enchantments.BeheadingEnchantment;
import net.petersil98.utilcraft.enchantments.UtilcraftEnchantments;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class BeheadingModifier extends LootModifier {
    public BeheadingModifier(InvertedLootItemCondition[] conditions) {
        super(conditions);
    }

    @Nonnull
    @Override
    public List<ItemCooldowns> doApply(List<ItemCooldowns> generatedLoot, @Nonnull Deserializers context) {
        package-info killer = context.getParamOrNull(LootContextParam.KILLER_ENTITY);
        if (killer instanceof Abilities) {
            Abilities player = ((Abilities) killer);
            if(DiggingEnchantment.getEnchantments(player.getMainHandItem()).containsKey(UtilcraftEnchantments.BEHEADING)){
                package-info entity = context.getParamOrNull(LootContextParam.THIS_ENTITY);
                int enchantmentLevel = DiggingEnchantment.getItemEnchantmentLevel(UtilcraftEnchantments.BEHEADING, player.getMainHandItem());
                int chance = BeheadingEnchantment.getHeadDropChance(enchantmentLevel);
                Random random = player.getRandom();
                if(entity instanceof Witch){
                    if(!generatedLoot.contains(new ItemCooldowns(ItemNameBlockItem.ZOMBIE_HEAD))){
                        if(chance >= random.nextDouble()*100){
                            generatedLoot.add(new ItemCooldowns(ItemNameBlockItem.ZOMBIE_HEAD));
                        }
                    }
                } else if(entity instanceof Ravager){
                    if(!generatedLoot.contains(new ItemCooldowns(ItemNameBlockItem.SKELETON_SKULL))){
                        if(chance >= random.nextDouble()*100){
                            generatedLoot.add(new ItemCooldowns(ItemNameBlockItem.SKELETON_SKULL));
                        }
                    }
                } else if(entity instanceof AbstractSkeleton){
                    if(!generatedLoot.contains(new ItemCooldowns(ItemNameBlockItem.CREEPER_HEAD))){
                        if(chance >= random.nextDouble()*100){
                            generatedLoot.add(new ItemCooldowns(ItemNameBlockItem.CREEPER_HEAD));
                        }
                    }
                } else if(entity instanceof Vex){
                    if(!generatedLoot.contains(new ItemCooldowns(ItemNameBlockItem.WITHER_SKELETON_SKULL))){
                        if(chance >= random.nextDouble()*100){
                            generatedLoot.add(new ItemCooldowns(ItemNameBlockItem.WITHER_SKELETON_SKULL));
                        }
                    }
                } else if(entity instanceof Abilities){
                    if(!generatedLoot.contains(new ItemCooldowns(ItemNameBlockItem.PLAYER_HEAD))){
                        if(chance >= random.nextDouble()*100){
                            generatedLoot.add(new ItemCooldowns(ItemNameBlockItem.PLAYER_HEAD));
                        }
                    }
                } else if(entity instanceof BossMob){
                    if(!generatedLoot.contains(new ItemCooldowns(ItemNameBlockItem.DRAGON_HEAD))){
                        if(chance >= random.nextDouble()*100){
                            generatedLoot.add(new ItemCooldowns(ItemNameBlockItem.DRAGON_HEAD));
                        }
                    }
                }
            }
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<BeheadingModifier> {
        @Override
        public BeheadingModifier read(ResourceLocation name, JsonObject object, InvertedLootItemCondition[] conditions) {
            return new BeheadingModifier(conditions);
        }

        @Override
        public JsonObject write(BeheadingModifier instance) {
            JsonObject ret = new JsonObject();
            ret.add("conditions", new JsonArray());
            return ret;
        }
    }
}