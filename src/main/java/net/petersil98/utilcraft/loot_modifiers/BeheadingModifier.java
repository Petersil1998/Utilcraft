package net.petersil98.utilcraft.loot_modifiers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.petersil98.utilcraft.enchantments.BeheadingEnchantment;
import net.petersil98.utilcraft.enchantments.UtilcraftEnchantments;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class BeheadingModifier extends LootModifier {
    public BeheadingModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, @Nonnull LootContext context) {
        Entity killer = context.getParamOrNull(LootContextParams.KILLER_ENTITY);
        if (killer instanceof Player player) {
            if(EnchantmentHelper.getEnchantments(player.getMainHandItem()).containsKey(UtilcraftEnchantments.BEHEADING.get())){
                Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
                int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(UtilcraftEnchantments.BEHEADING.get(), player.getMainHandItem());
                int chance = BeheadingEnchantment.getHeadDropChance(enchantmentLevel);
                Random random = player.getRandom();
                if(entity instanceof Zombie){
                    if(!generatedLoot.contains(new ItemStack(Items.ZOMBIE_HEAD))){
                        if(chance >= random.nextDouble()*100){
                            generatedLoot.add(new ItemStack(Items.ZOMBIE_HEAD));
                        }
                    }
                } else if(entity instanceof Skeleton){
                    if(!generatedLoot.contains(new ItemStack(Items.SKELETON_SKULL))){
                        if(chance >= random.nextDouble()*100){
                            generatedLoot.add(new ItemStack(Items.SKELETON_SKULL));
                        }
                    }
                } else if(entity instanceof Creeper){
                    if(!generatedLoot.contains(new ItemStack(Items.CREEPER_HEAD))){
                        if(chance >= random.nextDouble()*100){
                            generatedLoot.add(new ItemStack(Items.CREEPER_HEAD));
                        }
                    }
                } else if(entity instanceof WitherSkeleton){
                    if(!generatedLoot.contains(new ItemStack(Items.WITHER_SKELETON_SKULL))){
                        if(chance >= random.nextDouble()*100){
                            generatedLoot.add(new ItemStack(Items.WITHER_SKELETON_SKULL));
                        }
                    }
                } else if(entity instanceof Player){
                    if(!generatedLoot.contains(new ItemStack(Items.PLAYER_HEAD))){
                        if(chance >= random.nextDouble()*100){
                            generatedLoot.add(new ItemStack(Items.PLAYER_HEAD));
                        }
                    }
                } else if(entity instanceof EnderDragon){
                    if(!generatedLoot.contains(new ItemStack(Items.DRAGON_HEAD))){
                        if(chance >= random.nextDouble()*100){
                            generatedLoot.add(new ItemStack(Items.DRAGON_HEAD));
                        }
                    }
                }
            }
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<BeheadingModifier> {
        @Override
        public BeheadingModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditions) {
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