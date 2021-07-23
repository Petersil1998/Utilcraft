package net.petersil98.utilcraft.network;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.petersil98.utilcraft.utils.PlayerUtils;

import javax.annotation.Nonnull;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PlayerDeathStats {

    public PlayerDeathStats() {}

    public PlayerDeathStats(@Nonnull PacketBuffer packetBuffer) {
        CompoundNBT tag = packetBuffer.readNbt();
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>(tag.getInt("size"));
        ListNBT list = (ListNBT) tag.get("playerDeaths");
        for (INBT inbt : list) {
            CompoundNBT nbt = (CompoundNBT) inbt;
            map.put(nbt.getString("username"), nbt.getInt("deaths"));
        }
        PlayerUtils.setPlayerDeaths(map);
    }

    public void encode(@Nonnull PacketBuffer buf) {
        Map<String, Integer> playerDeaths = PlayerUtils.getPlayerDeaths();
        CompoundNBT tag = new CompoundNBT();
        ListNBT list = new ListNBT();
        int i = 0;
        for (Map.Entry<String, Integer> entry: playerDeaths.entrySet()) {
            CompoundNBT nbt = new CompoundNBT();
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
