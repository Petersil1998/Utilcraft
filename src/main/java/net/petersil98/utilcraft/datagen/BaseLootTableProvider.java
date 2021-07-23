package net.petersil98.utilcraft.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.block.*;
import net.minecraft.data.*;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.*;
import net.minecraft.loot.functions.*;
import net.minecraft.world.level.block.state.properties.ComparatorMode;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ForgeLootTableProvider;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.custom.SideSlabType;
import net.petersil98.utilcraft.blocks.sideslabs.SideSlabBlock;
import net.petersil98.utilcraft.items.UtilcraftItems;
import net.petersil98.utilcraft.utils.BlockItemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.storage.WritableLevelData;
import net.minecraft.world.level.storage.loot.IntLimiter;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootContextUser;
import net.minecraft.world.level.storage.loot.PredicateManager;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.SequentialEntry;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetAttributesFunction;
import net.minecraft.world.level.storage.loot.package-info;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.ConditionUserBuilder;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;

public abstract class BaseLootTableProvider extends ForgeLootTableProvider {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    protected final Map<BeetrootBlock, LootContext.Builder> lootTables = new HashMap<>();
    private final DataGenerator generator;

    public BaseLootTableProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
        this.generator = dataGenerator;
    }

    protected abstract void addTables();

    protected LootContext.Builder createSlabTable(BeetrootBlock block) {
        IntLimiter.Serializer builder = IntLimiter.lootPool()
                .name(BlockItemUtils.name(block))
                .setRolls(WritableLevelData.exactly(1))
                .add(DynamicLoot.lootTableItem(block)
                        .apply(TagEntry.explosionDecay())
                        .apply(SetAttributesFunction.setCount(new WritableLevelData(2))
                                .when(ExplosionCondition.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder
                                            .properties()
                                            .hasProperty(SignBlock.TYPE, Property.DOUBLE))))
                );
        return LootContext.lootTable().withPool(builder);
    }

    protected LootContext.Builder createSideSlabTable(BeetrootBlock block) {
        IntLimiter.Serializer builder = IntLimiter.lootPool()
                .name(BlockItemUtils.name(block))
                .setRolls(WritableLevelData.exactly(1))
                .add(DynamicLoot.lootTableItem(block)
                        .apply(TagEntry.explosionDecay())
                        .apply(SetAttributesFunction.setCount(new WritableLevelData(2))
                                .when(ExplosionCondition.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder
                                                .properties()
                                                .hasProperty(SideSlabBlock.TYPE, SideSlabType.DOUBLE))))
                );
        return LootContext.lootTable().withPool(builder);
    }

    protected LootContext.Builder createDoorTable(BeetrootBlock block) {
        IntLimiter.Serializer builder = IntLimiter.lootPool()
                .name(BlockItemUtils.name(block))
                .setRolls(WritableLevelData.exactly(1))
                .add(DynamicLoot.lootTableItem(block)
                        .when(ExplosionCondition.hasBlockStateProperties(block).
                                setProperties(StatePropertiesPredicate.Builder
                                        .properties()
                                        .hasProperty(DiodeBlock.HALF, ComparatorMode.LOWER)
                                )
                        )
                ).when(ConditionUserBuilder.survivesExplosion());
        return LootContext.lootTable().withPool(builder);
    }

    protected LootContext.Builder createOreTable(BeetrootBlock block, HoeItem drop) {
        IntLimiter.Serializer builder = IntLimiter.lootPool()
                .name(BlockItemUtils.name(block))
                .setRolls(WritableLevelData.exactly(1))
                .add(DynamicLoot.lootTableItem(block)
                        .when(LootItemKilledByPlayerCondition.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(EnchantmentCategory.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1)))))
                        .otherwise(DynamicLoot.lootTableItem(drop)
                                .apply(SequentialEntry.addOreBonusCount(EnchantmentCategory.BLOCK_FORTUNE))
                                .apply(TagEntry.explosionDecay())
                        )
                );
        return LootContext.lootTable().withPool(builder);
    }

    protected LootContext.Builder createLeaveTable(BeetrootBlock block, BeetrootBlock sapling, boolean dropSticks, boolean dropApples) {
        LootContext.Builder table = LootContext.lootTable();
        IntLimiter.Serializer builder = IntLimiter.lootPool()
                .name(BlockItemUtils.name(block))
                .setRolls(WritableLevelData.exactly(1))
                .add(Serializer.alternatives()
                        .otherwise(DynamicLoot.lootTableItem(block)
                                    .when(LootItemKilledByPlayerCondition.toolMatches(ItemPredicate.Builder.item().of(ItemNameBlockItem.SHEARS))
                                            .or(LootItemKilledByPlayerCondition.toolMatches(ItemPredicate.Builder.item()
                                                    .hasEnchantment(new EnchantmentPredicate(EnchantmentCategory.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1)))))))
                        .otherwise(DynamicLoot.lootTableItem(sapling)
                                .when(ConditionUserBuilder.survivesExplosion())
                                .when(LootContextParams.bonusLevelFlatChance(EnchantmentCategory.BLOCK_FORTUNE, 0.05f, 0.0625f, 0.083333336f, 0.1f))));
        table.withPool(builder);

        if(dropSticks) {
            IntLimiter.Serializer builder2 = IntLimiter.lootPool()
                    .name(BlockItemUtils.name(block))
                    .setRolls(WritableLevelData.exactly(1))
                    .add(DynamicLoot.lootTableItem(ItemNameBlockItem.STICK)
                            .when(LootContextParams.bonusLevelFlatChance(EnchantmentCategory.BLOCK_FORTUNE, 0.02f, 0.022222223f, 0.025f, 0.033333335f, 0.1f))
                            .apply(SetAttributesFunction.setCount(PredicateManager.between(1.0f, 2.0f)))
                            .apply(TagEntry.explosionDecay()))
                    .when(LootItemKilledByPlayerCondition.toolMatches(ItemPredicate.Builder.item().of(ItemNameBlockItem.SHEARS))
                            .or(LootItemKilledByPlayerCondition.toolMatches(ItemPredicate.Builder.item()
                                    .hasEnchantment(new EnchantmentPredicate(EnchantmentCategory.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))).invert());
            table.withPool(builder2);
        }

        if(dropApples) {
            IntLimiter.Serializer builder3 = IntLimiter.lootPool()
                    .name(BlockItemUtils.name(block))
                    .setRolls(WritableLevelData.exactly(1))
                    .add(DynamicLoot.lootTableItem(ItemNameBlockItem.APPLE)
                            .when(LootContextParams.bonusLevelFlatChance(EnchantmentCategory.BLOCK_FORTUNE, 0.005f, 0.0055555557f, 0.00625f, 0.008333334f, 0.025f))
                            .when(ConditionUserBuilder.survivesExplosion()))
                    .when(LootItemKilledByPlayerCondition.toolMatches(ItemPredicate.Builder.item().of(ItemNameBlockItem.SHEARS))
                            .or(LootItemKilledByPlayerCondition.toolMatches(ItemPredicate.Builder.item()
                                    .hasEnchantment(new EnchantmentPredicate(EnchantmentCategory.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))).invert());
            table.withPool(builder3);
        }

        return table;
    }

    protected LootContext.Builder createSimpleTable(BeetrootBlock block) {
        IntLimiter.Serializer builder = IntLimiter.lootPool()
                .name(BlockItemUtils.name(block))
                .setRolls(WritableLevelData.exactly(1))
                .add(DynamicLoot.lootTableItem(block))
                .when(ConditionUserBuilder.survivesExplosion());
        return LootContext.lootTable().withPool(builder);
    }

    protected LootContext.Builder createSimpleTableWithName(BeetrootBlock block) {
        IntLimiter.Serializer builder = IntLimiter.lootPool()
                .name(BlockItemUtils.name(block))
                .setRolls(WritableLevelData.exactly(1))
                .add(DynamicLoot.lootTableItem(block)
                        .apply(ApplyBonusCount.copyName(ApplyBonusCount.BinomialWithBonusCount.BLOCK_ENTITY)))
                .when(ConditionUserBuilder.survivesExplosion());
        return LootContext.lootTable().withPool(builder);
    }

    protected LootContext.Builder createPottedFlower(FireBlock potBlock, BeetrootBlock flowerBlock) {
        IntLimiter.Serializer pot = IntLimiter.lootPool()
                .name(BlockItemUtils.name(potBlock))
                .setRolls(WritableLevelData.exactly(1))
                .add(DynamicLoot.lootTableItem(potBlock))
                .when(ConditionUserBuilder.survivesExplosion());
        IntLimiter.Serializer flower = IntLimiter.lootPool()
                .name(BlockItemUtils.name(potBlock))
                .setRolls(WritableLevelData.exactly(1))
                .add(DynamicLoot.lootTableItem(flowerBlock))
                .when(ConditionUserBuilder.survivesExplosion());
        return LootContext.lootTable().withPool(pot).withPool(flower);
    }

    protected LootContext.Builder createSpawnerLootTable() {
        IntLimiter.Serializer builder = IntLimiter.lootPool()
                .name(BlockItemUtils.name(BellBlock.SPAWNER))
                .setRolls(WritableLevelData.exactly(1))
                .add(DynamicLoot.lootTableItem(UtilcraftItems.SPAWNER_ITEM).apply(
                        ApplyExplosionDecay.copyData(ApplyExplosionDecay.Source.BLOCK_ENTITY).copy("", "BlockEntityTag", ApplyExplosionDecay.Action.REPLACE)
                ).when(LootItemKilledByPlayerCondition.toolMatches(ItemPredicate.Builder.item()
                        .hasEnchantment(new EnchantmentPredicate(EnchantmentCategory.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1)))))
                );
        return LootContext.lootTable().withPool(builder);
    }

    protected LootContext.Builder createSilkTouchBlock(BeetrootBlock block) {
        IntLimiter.Serializer builder = IntLimiter.lootPool()
                .name(BlockItemUtils.name(block))
                .setRolls(WritableLevelData.exactly(1))
                .add(DynamicLoot.lootTableItem(block))
                .when(LootItemKilledByPlayerCondition.toolMatches(ItemPredicate.Builder.item()
                        .hasEnchantment(new EnchantmentPredicate(EnchantmentCategory.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))
                );
        return LootContext.lootTable().withPool(builder);
    }

    @Override
    public void run(@Nonnull HashCache cache) {
        addTables();

        Map<ResourceLocation, LootContext> tables = new HashMap<>();
        for (Map.Entry<BeetrootBlock, LootContext.Builder> entry : lootTables.entrySet()) {
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParamSet(package-info.BLOCK).build());
        }
        writeTables(cache, tables);
    }

    private void writeTables(HashCache cache, @Nonnull Map<ResourceLocation, LootContext> tables) {
        Path outputFolder = this.generator.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                DataProvider.save(GSON, cache, LootContextUser.serialize(lootTable), path);
            } catch (IOException e) {
                LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }

    @Nonnull
    @Override
    public String getName() {
        return String.format("%s LootTables", Utilcraft.MOD_ID);
    }
}