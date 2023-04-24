package me.mrenxo.data;


import org.bukkit.entity.Player;

public class PlayerData {

    private transient Player player;

    private transient


    public PlayerData() {

    }
    public PlayerData(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) { if (this.player == null) this.player = player; }



}
