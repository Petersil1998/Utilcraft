package net.petersil98.utilcraft.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;
import net.petersil98.utilcraft.entities.EmptyEntity;
import net.petersil98.utilcraft.entities.UtilcraftEntities;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Supplier;

public class PingRequest {

    public PingRequest() {}

    public PingRequest(@Nonnull FriendlyByteBuf packetBuffer) {}

    public void encode(@Nonnull FriendlyByteBuf buf) {}

    public boolean handle(@Nonnull Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if(player != null) {
                ServerLevel world = player.getLevel();
                Vec3 from = player.getEyePosition();
                BlockHitResult result = world.clip(new ClipContext(from, from.add(player.getLookAngle().scale(100)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
                if(result.getType() == HitResult.Type.BLOCK) {
                    List<EmptyEntity> entities = world.getEntities(UtilcraftEntities.EMPTY_ENTITY.get(), AABB.of(new BoundingBox(result.getBlockPos())), (emptyEntity) -> true);
                    if(!entities.isEmpty()) {
                        entities.forEach(emptyEntity -> emptyEntity.setAge(0));
                    } else {
                        EmptyEntity emptyEntity = new EmptyEntity(world, result.getBlockPos().getX() + 0.5, result.getBlockPos().getY(), result.getBlockPos().getZ() + 0.5);
                        world.addFreshEntity(emptyEntity);
                    }
                }
            }
        });
        return true;
    }
}
