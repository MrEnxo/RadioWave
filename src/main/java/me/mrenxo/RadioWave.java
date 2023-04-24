package me.mrenxo;

import com.google.gson.reflect.TypeToken;
import me.mrenxo.commands.NBTCommand;
import me.mrenxo.data.PlayerData;
import me.mrenxo.data.serialize.GsonSerializer;
import me.mrenxo.data.serialize.PlayerSerializer;
import me.mrenxo.data.storage.FileStorageProvider;
import me.mrenxo.data.storage.StorageManager;
import me.mrenxo.display.ScoreboardManager;
import me.mrenxo.item.commands.RadioItemCommand;
import me.mrenxo.item.listener.AbilityRightClickListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class RadioWave extends JavaPlugin {

    private static RadioWave instance;
    public ScoreboardManager scoreboardManager;

    public StorageManager<Player, PlayerData> playerData;

    public static RadioWave getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        scoreboardManager = new ScoreboardManager();

        playerData = new StorageManager<>(
                new FileStorageProvider<>(
                        new PlayerSerializer(),
                        new GsonSerializer<>(new TypeToken<PlayerData>(){}),
                        new File(getDataFolder().getPath() + "/playerData")
                )
        );

        registerEvents();
        registerCommand();
    }

    @Override
    public void onDisable() {

    }

    public void registerCommand() {
        this.getCommand("items").setExecutor(new RadioItemCommand());
        this.getCommand("nbt").setExecutor(new NBTCommand());
    }
    public void registerEvents() {

        getServer().getPluginManager().registerEvents(scoreboardManager, this);
        getServer().getPluginManager().registerEvents(new AbilityRightClickListener(), this);
    }
}