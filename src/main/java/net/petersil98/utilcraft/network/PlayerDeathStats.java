package net.petersil98.utilcraft.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.network.NetworkEvent;
import net.petersil98.utilcraft.utils.PlayerUtils;

import javax.annotation.Nonnull;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PlayerDeathStats {

    public PlayerDeathStats() {}

    public PlayerDeathStats(@Nonnull FriendlyByteBuf packetBuffer) {
        CompoundTag tag = packetBuffer.readNbt();
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>(tag.getInt("size"));
        ListTag list = (ListTag) tag.get("playerDeaths");
        for (Tag inbt : list) {
            CompoundTag nbt = (CompoundTag) inbt;
            map.put(nbt.getString("username"), nbt.getInt("deaths"));
        }
        PlayerUtils.setPlayerDeaths(map);
    }

    public void encode(@Nonnull FriendlyByteBuf buf) {
        Map<String, Integer> playerDeaths = PlayerUtils.getPlayerDeaths();
        CompoundTag tag = new CompoundTag();
        ListTag list = new ListTag();
        int i = 0;
        for (Map.Entry<String, Integer> entry: playerDeaths.entrySet()) {
            CompoundTag nbt = new CompoundTag();
            nbt.putString("username", entry.getKey());
            nbt.putInt("deaths", entry.getValue());
            list.add(i, nbt);
            i++;
        }
        tag.put("playerDeaths", list);
        tag.putInt("size", playerDeaths.size());
        buf.writeNbt(tag);
    }

    public boolean handle(@Nonnull Supplier<NetworkEvent.Context> ctx) {
        return true;
    }
}
