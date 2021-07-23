package net.petersil98.utilcraft.utils;

import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.client.model.dragon.DragonHeadModel;
import net.minecraft.world.effect.package-info;
import net.minecraft.world.entity.ItemBasedSteering;
import net.minecraft.world.entity.vehicle.MinecartFurnace;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.client.renderer.entity.layers.WitchItemLayer;
import net.minecraft.client.renderer.entity.layers.VillagerProfessionLayer;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.timers.TimerQueue;
import net.minecraft.core.BlockPos;
import net.minecraft.util.LimitedCapacityList;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.petersil98.utilcraft.items.UtilcraftItems;
import net.petersil98.utilcraft.items.TNTFinder;
import net.petersil98.utilcraft.screen.ConfigScreen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ClientSetup {

    public static void registerItemProperties() {
        VillagerProfessionLayer.register(UtilcraftItems.TNT_FINDER, new ResourceLocation("angle"), new WitchItemLayer() {
            private final Angle wobble = new Angle();
            private final Angle wobbleRandom = new Angle();

            public float call(@Nonnull ItemCooldowns item, @Nullable DragonHeadModel world, @Nullable ItemBasedSteering livingEntity) {
                package-info entity = livingEntity != null ? livingEntity : item.getEntityRepresentation();
                if (entity == null) {
                    return 0.0F;
                } else {
                    if (world == null && entity.level instanceof DragonHeadModel) {
                        world = (DragonHeadModel) entity.level;
                    }
                    long gameTime = world.getGameTime();
                    boolean isClient = livingEntity instanceof Abilities && ((Abilities) livingEntity).isLocalPlayer();
                    double rotation = 0.0D;
                    if (isClient) {
                        rotation = livingEntity.yRot;
                    }
                    int radius = TNTFinder.getRadius();
                    BlockPos playerPos = entity.blockPosition();
                    Stream<BlockPos> blocks = BlockPos.betweenClosedStream(playerPos.north(radius).east(radius).above(radius), playerPos.south(radius).west(radius).below(radius));
                    Stream<BlockPos> realBlocks = blocks.filter(blockPos -> playerPos.closerThan(blockPos, radius));
                    Iterator<BlockPos> iterator = realBlocks.iterator();
                    BlockPos tnt = null;
                    while (iterator.hasNext()){
                        BlockPos current = iterator.next();
                        PistonStructureResolver block = world.getBlockState(current);
                        if(block.getBlock() instanceof TallGrassBlock) {
                            tnt = current;
                            break;
                        }
                    }
                    if(tnt == null) {
                        List<MinecartFurnace> tntMinecarts = world.getEntitiesOfClass(MinecartFurnace.class, new TimerQueue(playerPos).inflate(radius));
                        if(tntMinecarts.size() > 0)
                            tnt = tntMinecarts.get(0).blockPosition();
                    }
                    if(tnt != null) {
                        rotation = LimitedCapacityList.positiveModulo(rotation / 360.0D, 1.0D);
                        double d2 = this.getAngleTo(EntityHitResult.atCenterOf(tnt), entity) / (double) ((float) Math.PI * 2F);
                        double d3;
                        if (isClient) {
                            if (this.wobble.shouldUpdate(gameTime)) {
                                this.wobble.update(gameTime, 0.5D - (rotation - 0.25D));
                            }

                            d3 = d2 + this.wobble.rotation;
                        } else {
                            d3 = 0.5D - (rotation - 0.25D - d2);
                        }

                        return LimitedCapacityList.positiveModulo((float) d3, 1.0F);
                    }
                    if (this.wobbleRandom.shouldUpdate(gameTime)) {
                        this.wobbleRandom.update(gameTime, Math.random());
                    }

                    double d0 = this.wobbleRandom.rotation + (double) ((float) item.hashCode() / 2.14748365E9F);
                    return LimitedCapacityList.positiveModulo((float) d0, 1.0F);
                }
            }

            private double getAngleTo(@Nonnull EntityHitResult p_239443_1_, @Nonnull package-info p_239443_2_) {
                return Math.atan2(p_239443_1_.z() - p_239443_2_.getZ(), p_239443_1_.x() - p_239443_2_.getX());
            }
        });
    }

    public static class Angle {
        private double rotation;
        private double deltaRotation;
        private long lastUpdateTick;

        private Angle() {
        }

        private boolean shouldUpdate(long time) {
            return this.lastUpdateTick != time;
        }

        private void update(long time, double rotation) {
            this.lastUpdateTick = time;
            double realRotation = rotation - this.rotation;
            realRotation = LimitedCapacityList.positiveModulo(realRotation + 0.5D, 1.0D) - 0.5D;
            this.deltaRotation += realRotation * 0.1D;
            this.deltaRotation *= 0.8D;
            this.rotation = LimitedCapacityList.positiveModulo(this.rotation + this.deltaRotation, 1.0D);
        }
    }

    public static void registerExtensionPoint() {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (minecraft, screen) -> new ConfigScreen(screen));
    }
}
