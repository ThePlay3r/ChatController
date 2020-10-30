package me.pljr.chatcontroller.config;

import me.pljr.pljrapi.managers.ConfigManager;

public class CfgSettings {
    public static boolean bungee;
    public static String adminChat;
    public static boolean sounds;
    public static int broadcast;

    public static void load(ConfigManager config){
        bungee = config.getBoolean("settings.bungee");
        adminChat = config.getString("settings.admin-chat");
        sounds = config.getBoolean("settings.sounds");
        broadcast = config.getInt("settings.broadcast");
    }
}
