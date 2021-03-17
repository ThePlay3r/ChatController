package me.pljr.chatcontroller;

import me.pljr.chatcontroller.commands.*;
import me.pljr.chatcontroller.config.*;
import me.pljr.chatcontroller.listeners.*;
import me.pljr.chatcontroller.managers.BroadcastManager;
import me.pljr.chatcontroller.managers.PlayerManager;
import me.pljr.chatcontroller.managers.QueryManager;
import me.pljr.pljrapispigot.PLJRApiSpigot;
import me.pljr.pljrapispigot.database.DataSource;
import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class ChatController extends JavaPlugin {

    public static Logger log;

    private PLJRApiSpigot pljrApiSpigot;

    private ConfigManager configManager;
    private Groups groups;
    private Settings settings;
    private Broadcasts broadcasts;
    private DeathMessages deathMessages;

    private PlayerManager playerManager;
    private QueryManager queryManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!setupPLJRApi()) return;
        setupConfig();
        setupManagers();
        setupDatabase();
        setupListeners();
        setupCommands();
    }

    public boolean setupPLJRApi(){
        if (PLJRApiSpigot.get() == null){
            getLogger().warning("PLJRApi-Spigot is not enabled!");
            return false;
        }
        pljrApiSpigot = PLJRApiSpigot.get();
        return true;
    }

    public void setupConfig(){
        saveDefaultConfig();
        reloadConfig();
        configManager = new ConfigManager(this, "config.yml");
        groups = new Groups(configManager);
        settings = new Settings(configManager);
        broadcasts = new Broadcasts(configManager);
        deathMessages = new DeathMessages(configManager);
        Lang.load(new ConfigManager(this, "lang.yml"));
        ActionBarType.load(new ConfigManager(this, "actionbars.yml"));
        SoundType.load(new ConfigManager(this, "sounds.yml"));
        TitleType.load(new ConfigManager(this, "titles.yml"));
    }

    private void setupManagers(){
        playerManager = new PlayerManager(this, queryManager, settings.isCachePlayers());
        BroadcastManager broadcastManager = new BroadcastManager(this, broadcasts, settings);
        broadcastManager.startTimer(settings.getBroadcast());
    }

    private void setupDatabase(){
        DataSource dataSource = pljrApiSpigot.getDataSource(configManager);
        queryManager = new QueryManager(dataSource);
        queryManager.setupTables();
        for (Player player : Bukkit.getOnlinePlayers()){
            queryManager.loadPlayer(player.getUniqueId());
        }
    }

    private void setupListeners(){
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new AsyncPlayerChatListener(groups, settings), this);
        pluginManager.registerEvents(new AsyncPlayerPreLoginListener(playerManager), this);
        pluginManager.registerEvents(new PlayerQuitListener(playerManager), this);
        pluginManager.registerEvents(new PlayerJoinListener(settings), this);
        pluginManager.registerEvents(new PlayerDeathListener(deathMessages, settings), this);
    }

    private void setupCommands(){
        new ChatControllerCommand(this).registerCommand(this);
        new MsgCommand(playerManager).registerCommand(this);
        new MsgIgnoreCommand(playerManager).registerCommand(this);
        new AChatControllerCommand(playerManager).registerCommand(this);
        new AChatCommand(settings).registerCommand(this);
        new AMsgCommand(playerManager).registerCommand(this);
        new BCCommand(settings).registerCommand(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for (Player player : Bukkit.getOnlinePlayers()){
            playerManager.getPlayer(player.getUniqueId(), queryManager::savePlayer);
        }
    }
}
