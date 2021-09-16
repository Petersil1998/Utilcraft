package net.petersil98.utilcraft.loot_modifiers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.petersil98.utilcraft.enchantments.BeheadingEnchantment;
import net.petersil98.utilcraft.enchantments.UtilcraftEnchantments;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class BeheadingModifier extends LootModifier {
    public BeheadingModifier(ILootCondition[] conditions) {
        super(conditions);
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, @Nonnull LootContext context) {
        Entity killer = context.getParamOrNull(LootParameters.KILLER_ENTITY);
        if (killer instanceof PlayerEntity) {
            PlayerEntity player = ((PlayerEntity) killer);
            if(EnchantmentHelper.getEnchantments(player.getMainHandItem()).containsKey(UtilcraftEnchantments.BEHEADING.get())){
                Entity entity = context.getParamOrNull(LootParameters.THIS_ENTITY);
                int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(UtilcraftEnchantments.BEHEADING.get(), player.getMainHandItem());
                int chance = BeheadingEnchantment.getHeadDropChance(enchantmentLevel);
                Random random = player.getRandom();
                if(entity instanceof ZombieEntity){
                    if(!generatedLoot.contains(new ItemStack(Items.ZOMBIE_HEAD))){
                        if(chance >= random.nextDouble()*100){
                            generatedLoot.add(new ItemStack(Items.ZOMBIE_HEAD));
                        }
                    }
                } else if(entity instanceof SkeletonEntity){
                    if(!generatedLoot.contains(new ItemStack(Items.SKELETON_SKULL))){
                        if(chance >= random.nextDouble()*100){
                            generatedLoot.add(new ItemStack(Items.SKELETON_SKULL));
                        }
                    }
                } else if(entity instanceof CreeperEntity){
                    if(!generatedLoot.contains(new ItemStack(Items.CREEPER_HEAD))){
                        if(chance >= random.nextDouble()*100){
                            generatedLoot.add(new ItemStack(Items.CREEPER_HEAD));
                        }
                    }
                } else if(entity instanceof WitherSkeletonEntity){
                    if(!generatedLoot.contains(new ItemStack(Items.WITHER_SKELETON_SKULL))){
                        if(chance >= random.nextDouble()*100){
                            generatedLoot.add(new ItemStack(Items.WITHER_SKELETON_SKULL));
                        }
                    }
                } else if(entity instanceof PlayerEntity){
                    if(!generatedLoot.contains(new ItemStack(Items.PLAYER_HEAD))){
                        if(chance >= random.nextDouble()*100){
                            generatedLoot.add(new ItemStack(Items.PLAYER_HEAD));
                        }
                    }
                } else if(entity instanceof EnderDragonEntity){
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
        public BeheadingModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditions) {
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