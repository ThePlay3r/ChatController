package me.pljr.chatcontroller.config;

import lombok.Getter;
import me.pljr.pljrapispigot.managers.ConfigManager;

@Getter
public class Settings {
    private final static String PATH = "settings";

    private final boolean bungee;
    private final boolean bungeeAChat;
    private final String adminChat;
    private final boolean sounds;
    private final int broadcast;
    private final boolean joinMessage;
    private final boolean joinTitle;
    private final boolean joinActionbar;
    private final boolean mentionMessage;
    private final boolean mentionTitle;
    private final boolean mentionActionbar;
    private final boolean cachePlayers;

    public Settings(ConfigManager config){
        bungee = config.getBoolean(PATH+".bungee");
        bungeeAChat = config.getBoolean(PATH+".bungee-admin-chat");
        adminChat = config.getString(PATH+".admin-chat");
        sounds = config.getBoolean(PATH+".sounds");
        broadcast = config.getInt(PATH+".broadcast");
        joinMessage = config.getBoolean(PATH+".join-message");
        joinTitle = config.getBoolean(PATH+".join-title");
        joinActionbar = config.getBoolean(PATH+".join-actionbar");
        mentionMessage = config.getBoolean(PATH+".mention-message");
        mentionTitle = config.getBoolean(PATH+".mention-title");
        mentionActionbar = config.getBoolean(PATH+".mention-actionbar");
        cachePlayers = config.getBoolean(PATH+".cache-players");
    }
}
