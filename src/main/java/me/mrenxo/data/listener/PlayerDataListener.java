package me.mrenxo.data.listener;

import me.mrenxo.RadioWave;
import me.mrenxo.data.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerDataListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!RadioWave.getInstance().playerData.hasData(player)) {
            RadioWave.getInstance().playerData.putData(player, new PlayerData(player));
        } else {
            RadioWave.getInstance().playerData.loadData(player);
        }

        PlayerData playerData = RadioWave.getInstance().playerData.getData(player);
        playerData.setPlayer(player);
    }

    @EventHandler
    public void onJoin(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!RadioWave.getInstance().playerData.hasCachedData(player)) return;
        RadioWave.getInstance().playerData.saveData(player);
        RadioWave.getInstance().playerData.removeCachedData(player);
    }
}
