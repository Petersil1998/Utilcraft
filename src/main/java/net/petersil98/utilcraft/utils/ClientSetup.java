package net.petersil98.utilcraft.utils;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.MinecartTNT;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmlclient.ConfigGuiHandler;
import net.petersil98.utilcraft.items.TNTFinder;
import net.petersil98.utilcraft.items.UtilcraftItems;
import net.petersil98.utilcraft.screen.ConfigScreen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ClientSetup {

    public static void registerItemProperties() {
        ItemProperties.register(UtilcraftItems.TNT_FINDER.get(), new ResourceLocation("angle"), new ItemPropertyFunction() {
            private final Angle wobble = new Angle();
            private final Angle wobbleRandom = new Angle();

            public float call(@Nonnull ItemStack item, @Nullable ClientLevel world, @Nullable LivingEntity livingEntity, int p_174679_) {
                Entity entity = livingEntity != null ? livingEntity : item.getEntityRepresentation();
                if (entity == null) {
                    return 0.0F;
                } else {
                    if (world == null && entity.level instanceof ClientLevel) {
                        world = (ClientLevel) entity.level;
                    }
                    long gameTime = world.getGameTime();
                    boolean isClient = livingEntity instanceof Player && ((Player) livingEntity).isLocalPlayer();
                    double rotation = 0.0D;
                    if (isClient) {
                        rotation = livingEntity.yBodyRot;
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
                        if(block.getBlock() instanceof TntBlock) {
                            tnt = current;
                            break;
                        }
                    }
                    if(tnt == null) {
                        List<MinecartTNT> tntMinecarts = world.getEntitiesOfClass(MinecartTNT.class, new AABB(playerPos).inflate(radius));
                        if(tntMinecarts.size() > 0)
                            tnt = tntMinecarts.get(0).blockPosition();
                    }
                    if(tnt != null) {
                        rotation = Mth.positiveModulo(rotation / 360.0D, 1.0D);
                        double d2 = this.getAngleTo(Vec3.atCenterOf(tnt), entity) / (double) ((float) Math.PI * 2F);
                        double d3;
                        if (isClient) {
                            if (this.wobble.shouldUpdate(gameTime)) {
                                this.wobble.update(gameTime, 0.5D - (rotation - 0.25D));
                            }

                            d3 = d2 + this.wobble.rotation;
                        } else {
                            d3 = 0.5D - (rotation - 0.25D - d2);
                        }

                        return Mth.positiveModulo((float) d3, 1.0F);
                    }
                    if (this.wobbleRandom.shouldUpdate(gameTime)) {
                        this.wobbleRandom.update(gameTime, Math.random());
                    }

                    double d0 = this.wobbleRandom.rotation + (double) ((float) item.hashCode() / 2.14748365E9F);
                    return Mth.positiveModulo((float) d0, 1.0F);
                }
            }

            private double getAngleTo(@Nonnull Vec3 p_239443_1_, @Nonnull Entity p_239443_2_) {
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
            realRotation = Mth.positiveModulo(realRotation + 0.5D, 1.0D) - 0.5D;
            this.deltaRotation += realRotation * 0.1D;
            this.deltaRotation *= 0.8D;
            this.rotation = Mth.positiveModulo(this.rotation + this.deltaRotation, 1.0D);
        }
    }

    public static void registerExtensionPoint() {
        ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class,
                () -> new ConfigGuiHandler.ConfigGuiFactory((minecraft, screen) -> new ConfigScreen(screen)));
    }
}
