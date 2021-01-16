package net.petersil98.utilcraft.loot_modifiers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.block.ShulkerBoxBlock;
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
    public BeheadingModifier(ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        Entity killer = context.get(LootParameters.KILLER_ENTITY);
        if (killer instanceof PlayerEntity) {
            PlayerEntity player = ((PlayerEntity) killer);
            if(EnchantmentHelper.getEnchantments(player.getHeldItemMainhand()).containsKey(UtilcraftEnchantments.BEHEADING)){
                Entity entity = context.get(LootParameters.THIS_ENTITY);
                int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(UtilcraftEnchantments.BEHEADING, player.getHeldItemMainhand());
                int chance = BeheadingEnchantment.getHeadDropChance(enchantmentLevel);
                Random random = player.getRNG();
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
        public BeheadingModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn) {
            return new BeheadingModifier(conditionsIn);
        }

        @Override
        public JsonObject write(BeheadingModifier instance) {
            JsonObject ret = new JsonObject();
            ret.add("conditions", new JsonArray());
            return ret;
        }
    }
}