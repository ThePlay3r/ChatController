package me.pljr.chatcontroller;

import me.pljr.chatcontroller.commands.*;
import me.pljr.chatcontroller.config.*;
import me.pljr.chatcontroller.listeners.*;
import me.pljr.chatcontroller.managers.BroadcastManager;
import me.pljr.chatcontroller.managers.PlayerManager;
import me.pljr.chatcontroller.managers.QueryManager;
import me.pljr.pljrapispigot.database.DataSource;
import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatController extends JavaPlugin {
    private static ChatController instance;
    private static ConfigManager configManager;
    private static PlayerManager playerManager;
    private static QueryManager queryManager;
    private static BroadcastManager broadcastManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        setupConfig();
        setupManagers();
        setupDatabase();
        setupListeners();
        setupCommands();
    }

    public void setupConfig(){
        saveDefaultConfig();
        reloadConfig();
        configManager = new ConfigManager(this, "config.yml");
        CfgGroups.load(configManager);
        Lang.load(configManager);
        CfgMention.load(configManager);
        CfgSettings.load(configManager);
        SoundType.load(configManager);
        CfgBroadcasts.load(configManager);
        CfgDeathMessages.load(configManager);
    }

    private void setupManagers(){
        playerManager = new PlayerManager();
        broadcastManager = new BroadcastManager(this);
        broadcastManager.startTimer(CfgSettings.BROADCAST);
    }

    private void setupDatabase(){
        DataSource dataSource = DataSource.getFromConfig(configManager);
        queryManager = new QueryManager(dataSource, this);
        queryManager.setupTables();
        for (Player player : Bukkit.getOnlinePlayers()){
            queryManager.loadPlayer(player.getUniqueId());
        }
    }

    private void setupListeners(){
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new AsyncPlayerChatListener(), this);
        pluginManager.registerEvents(new AsyncPlayerPreLoginListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerDeathListener(), this);
    }

    private void setupCommands(){
        new ChatControllerCommand().registerCommand(this);
        new MsgCommand().registerCommand(this);
        new MsgIgnoreCommand().registerCommand(this);
        new AChatControllerCommand().registerCommand(this);
        new AChatCommand().registerCommand(this);
        new AMsgCommand().registerCommand(this);
        new BCCommand().registerCommand(this);
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }
    public static PlayerManager getPlayerManager() {
        return playerManager;
    }
    public static QueryManager getQueryManager() {
        return queryManager;
    }
    public static ChatController getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for (Player player : Bukkit.getOnlinePlayers()){
            queryManager.savePlayerSync(player.getUniqueId());
        }
    }
}
