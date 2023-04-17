package me.mrenxo;

import me.mrenxo.display.ScoreboardManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RadioWave extends JavaPlugin {

    private static RadioWave instance;
    public ScoreboardManager scoreboardManager;

    public static RadioWave getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        scoreboardManager = new ScoreboardManager();

        registerEvents();
    }

    @Override
    public void onDisable() {

    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(scoreboardManager, this);
    }
}