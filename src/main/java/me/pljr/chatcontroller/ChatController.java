package me.pljr.chatcontroller;

import me.pljr.chatcontroller.commands.*;
import me.pljr.chatcontroller.config.*;
import me.pljr.chatcontroller.listeners.*;
import me.pljr.chatcontroller.managers.BroadcastManager;
import me.pljr.chatcontroller.managers.PlayerManager;
import me.pljr.chatcontroller.managers.QueryManager;
import me.pljr.pljrapi.PLJRApi;
import me.pljr.pljrapi.database.DataSource;
import me.pljr.pljrapi.managers.ConfigManager;
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
        if (!setupPLJRApi()) return;
        instance = this;
        setupConfig();
        setupManagers();
        setupDatabase();
        setupListeners();
        setupCommands();
    }

    private boolean setupPLJRApi(){
        PLJRApi api = (PLJRApi) Bukkit.getServer().getPluginManager().getPlugin("PLJRApi");
        if (api == null){
            Bukkit.getConsoleSender().sendMessage("§cChatController: PLJRApi not found, disabling plugin!");
            getServer().getPluginManager().disablePlugin(this);
            return false;
        }else{
            Bukkit.getConsoleSender().sendMessage("§aChatController: Hooked into PLJRApi!");
            return true;
        }
    }

    private void setupConfig(){
        saveDefaultConfig();
        configManager = new ConfigManager(getConfig(), "§cCahtController:", "config.yml");
        CfgGroups.load(configManager);
        CfgLang.load(configManager);
        CfgMention.load(configManager);
        CfgSettings.load(configManager);
        CfgSounds.load(configManager);
        CfgBroadcasts.load(configManager);
        CfgDeathMessages.load(configManager);
    }

    private void setupManagers(){
        playerManager = new PlayerManager();
        broadcastManager = new BroadcastManager(this);
        broadcastManager.startTimer(CfgSettings.broadcast);
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
        getCommand("chatcontroller").setExecutor(new ChatControllerCommand());
        getCommand("msg").setExecutor(new MsgCommand());
        getCommand("msgignore").setExecutor(new MsgIgnoreCommand());
        getCommand("achatcontroller").setExecutor(new AChatControllerCommand());
        getCommand("achat").setExecutor(new AChatCommand());
        getCommand("amsg").setExecutor(new AMsgCommand());
        getCommand("bc").setExecutor(new BCCommand());
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
