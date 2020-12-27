package me.pljr.chatcontroller.config;

import me.pljr.pljrapispigot.managers.ConfigManager;

public class CfgSettings {
    public static boolean BUNGEE;
    public static String ADMIN_CHAT;
    public static boolean SOUNDS;
    public static int BROADCAST;
    public static boolean JOIN_MESSAGE;
    public static boolean JOIN_TITLE;
    public static boolean JOIN_ACTIONBAR;

    public static void load(ConfigManager config){
        BUNGEE = config.getBoolean("settings.bungee");
        ADMIN_CHAT = config.getString("settings.admin-chat");
        SOUNDS = config.getBoolean("settings.sounds");
        BROADCAST = config.getInt("settings.broadcast");
        JOIN_MESSAGE = config.getBoolean("settings.join-message");
        JOIN_TITLE = config.getBoolean("settings.join-title");
        JOIN_ACTIONBAR = config.getBoolean("settings.join-actionbar");
    }
}
