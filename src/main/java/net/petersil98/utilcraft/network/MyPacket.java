package net.petersil98.utilcraft.network;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class MyPacket {

    private final BlockPos pos;

    public MyPacket(PacketBuffer buf) {
        pos = buf.readBlockPos();
    }

    public MyPacket(BlockPos pos) {
        this.pos = pos;
    }

    public void encode(PacketBuffer buf) {
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientWorld world = Minecraft.getInstance().world;
            //world.setBlockState(pos, Blocks.AIR.getDefaultState());
        });
        return true;
    }
}
