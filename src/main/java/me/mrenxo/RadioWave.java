package me.mrenxo;

import me.mrenxo.display.ScoreboardManager;
import me.mrenxo.item.commands.RadioItemCommand;
import me.mrenxo.item.listener.AbilityRightClickListener;
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
        registerCommand();
    }

    @Override
    public void onDisable() {

    }

    public void registerCommand() {
        this.getCommand("items").setExecutor(new RadioItemCommand());
    }
    public void registerEvents() {

        getServer().getPluginManager().registerEvents(scoreboardManager, this);
        getServer().getPluginManager().registerEvents(new AbilityRightClickListener(), this);
    }
}