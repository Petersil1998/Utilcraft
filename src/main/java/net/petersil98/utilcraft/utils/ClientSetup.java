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
import net.minecraftforge.fml.common.Mod;
import net.petersil98.utilcraft.items.UtilcraftItems;
import net.petersil98.utilcraft.items.TNTFinder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ClientSetup {

    public static void registerItemProperties() {
        ItemModelsProperties.registerProperty(UtilcraftItems.TNT_FINDER, new ResourceLocation("angle"), new IItemPropertyGetter() {
            private final Angle field_239439_a_ = new Angle();
            private final Angle field_239440_b_ = new Angle();

            public float call(@Nonnull ItemStack item, @Nullable ClientWorld world, @Nullable LivingEntity livingEntity) {
                Entity entity = livingEntity != null ? livingEntity : item.getAttachedEntity();
                if (entity == null) {
                    return 0.0F;
                } else {
                    if (world == null && entity.world instanceof ClientWorld) {
                        world = (ClientWorld) entity.world;
                    }
                    long i = world.getGameTime();
                    boolean flag = livingEntity instanceof PlayerEntity && ((PlayerEntity) livingEntity).isUser();
                    double d1 = 0.0D;
                    if (flag) {
                        d1 = livingEntity.rotationYaw;
                    }
                    int radius = TNTFinder.getRadius();
                    BlockPos playerPos = entity.getPosition();
                    Stream<BlockPos> blocks = BlockPos.getAllInBox(playerPos.north(radius).east(radius).up(radius), playerPos.south(radius).west(radius).down(radius));
                    Stream<BlockPos> realBlocks = blocks.filter(blockPos -> playerPos.withinDistance(blockPos, radius));
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
                        List<TNTMinecartEntity> tntMinecarts = world.getEntitiesWithinAABB(TNTMinecartEntity.class, new AxisAlignedBB(playerPos).grow(radius));
                        if(tntMinecarts.size() > 0)
                            tnt = tntMinecarts.get(0).getPosition();
                    }
                    if(tnt != null) {
                        d1 = MathHelper.positiveModulo(d1 / 360.0D, 1.0D);
                        double d2 = this.func_239443_a_(Vector3d.copyCentered(tnt), entity) / (double) ((float) Math.PI * 2F);
                        double d3;
                        if (flag) {
                            if (this.field_239439_a_.func_239448_a_(i)) {
                                this.field_239439_a_.func_239449_a_(i, 0.5D - (d1 - 0.25D));
                            }

                            d3 = d2 + this.field_239439_a_.field_239445_a_;
                        } else {
                            d3 = 0.5D - (d1 - 0.25D - d2);
                        }

                        return MathHelper.positiveModulo((float) d3, 1.0F);
                    }
                    if (this.field_239440_b_.func_239448_a_(i)) {
                        this.field_239440_b_.func_239449_a_(i, Math.random());
                    }

                    double d0 = this.field_239440_b_.field_239445_a_ + (double) ((float) item.hashCode() / 2.14748365E9F);
                    return MathHelper.positiveModulo((float) d0, 1.0F);
                }
            }

            private double func_239443_a_(Vector3d p_239443_1_, Entity p_239443_2_) {
                return Math.atan2(p_239443_1_.getZ() - p_239443_2_.getPosZ(), p_239443_1_.getX() - p_239443_2_.getPosX());
            }
        });
    }

    public static class Angle {
        private double field_239445_a_;
        private double field_239446_b_;
        private long field_239447_c_;

        private Angle() {
        }

        private boolean func_239448_a_(long p_239448_1_) {
            return this.field_239447_c_ != p_239448_1_;
        }

        private void func_239449_a_(long p_239449_1_, double p_239449_3_) {
            this.field_239447_c_ = p_239449_1_;
            double d0 = p_239449_3_ - this.field_239445_a_;
            d0 = MathHelper.positiveModulo(d0 + 0.5D, 1.0D) - 0.5D;
            this.field_239446_b_ += d0 * 0.1D;
            this.field_239446_b_ *= 0.8D;
            this.field_239445_a_ = MathHelper.positiveModulo(this.field_239445_a_ + this.field_239446_b_, 1.0D);
        }
    }
}
