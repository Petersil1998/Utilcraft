package net.petersil98.utilcraft.utils;

import net.minecraft.block.BlockState;
import net.minecraft.block.TNTBlock;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.minecart.TNTMinecartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
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
        ItemModelsProperties.register(UtilcraftItems.TNT_FINDER, new ResourceLocation("angle"), new IItemPropertyGetter() {
            private final Angle wobble = new Angle();
            private final Angle wobbleRandom = new Angle();

            public float call(@Nonnull ItemStack item, @Nullable ClientWorld world, @Nullable LivingEntity livingEntity) {
                Entity entity = livingEntity != null ? livingEntity : item.getEntityRepresentation();
                if (entity == null) {
                    return 0.0F;
                } else {
                    if (world == null && entity.level instanceof ClientWorld) {
                        world = (ClientWorld) entity.level;
                    }
                    long gameTime = world.getGameTime();
                    boolean isClient = livingEntity instanceof PlayerEntity && ((PlayerEntity) livingEntity).isLocalPlayer();
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
                        BlockState block = world.getBlockState(current);
                        if(block.getBlock() instanceof TNTBlock) {
                            tnt = current;
                            break;
                        }
                    }
                    if(tnt == null) {
                        List<TNTMinecartEntity> tntMinecarts = world.getEntitiesOfClass(TNTMinecartEntity.class, new AxisAlignedBB(playerPos).inflate(radius));
                        if(tntMinecarts.size() > 0)
                            tnt = tntMinecarts.get(0).blockPosition();
                    }
                    if(tnt != null) {
                        rotation = MathHelper.positiveModulo(rotation / 360.0D, 1.0D);
                        double d2 = this.getAngleTo(Vector3d.atCenterOf(tnt), entity) / (double) ((float) Math.PI * 2F);
                        double d3;
                        if (isClient) {
                            if (this.wobble.shouldUpdate(gameTime)) {
                                this.wobble.update(gameTime, 0.5D - (rotation - 0.25D));
                            }

                            d3 = d2 + this.wobble.rotation;
                        } else {
                            d3 = 0.5D - (rotation - 0.25D - d2);
                        }

                        return MathHelper.positiveModulo((float) d3, 1.0F);
                    }
                    if (this.wobbleRandom.shouldUpdate(gameTime)) {
                        this.wobbleRandom.update(gameTime, Math.random());
                    }

                    double d0 = this.wobbleRandom.rotation + (double) ((float) item.hashCode() / 2.14748365E9F);
                    return MathHelper.positiveModulo((float) d0, 1.0F);
                }
            }

            private double getAngleTo(@Nonnull Vector3d p_239443_1_, @Nonnull Entity p_239443_2_) {
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
            realRotation = MathHelper.positiveModulo(realRotation + 0.5D, 1.0D) - 0.5D;
            this.deltaRotation += realRotation * 0.1D;
            this.deltaRotation *= 0.8D;
            this.rotation = MathHelper.positiveModulo(this.rotation + this.deltaRotation, 1.0D);
        }
    }

    public static void registerExtensionPoint() {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (minecraft, screen) -> new ConfigScreen(screen));
    }
}
