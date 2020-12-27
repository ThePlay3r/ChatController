package me.pljr.chatcontroller.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.objects.PLJRActionBar;
import me.pljr.pljrapispigot.objects.PLJRTitle;

import java.util.HashMap;
import java.util.List;

public enum Lang {
    ADMIN_CHAT_FORMAT,
    MSG_FAILURE_IGNORING,
    MSG_FAILURE_BLOCKED,
    MSGIGNORE_SUCCESS_FULLIGNORE_ON,
    MSGIGNORE_SUCCESS_FULLIGNORE_OFF,
    MSGIGNORE_SUCCESS_IGNORE,
    MSGIGNORE_SUCCESS_UNIGNORE,
    ACHATCONTROLLER_SPY_ON,
    ACHATCONTROLLER_SPY_OFF,
    ADMIN_SPY,
    MESSAGE_JOIN_FIRST,
    MESSAGE_JOIN,
    MESSAGE_LEAVE;
    public static List<String> PRIVATE_MESSAGE_SENDER;
    public static List<String> PRIVATE_MESSAGE_RECEIVER;
    public static List<String> HELP;
    public static List<String> ADMIN_HELP;
    public static List<String> JOIN_MESSAGE;
    public static PLJRTitle JOIN_TITLE;
    public static PLJRActionBar JOIN_ACTIONBAR;

    public static HashMap<Lang, String> lang;

    public static void load(ConfigManager config){
        PRIVATE_MESSAGE_SENDER = config.getStringList("private-message-sender");
        PRIVATE_MESSAGE_RECEIVER = config.getStringList("private-message-receiver");
        HELP = config.getStringList("help");
        ADMIN_HELP = config.getStringList("admin-help");
        JOIN_MESSAGE = config.getStringList("join-message");
        JOIN_TITLE = config.getPLJRTitle("join-title");
        JOIN_ACTIONBAR = config.getPLJRActionBar("join-actionbar");
        lang = new HashMap<>();
        for (Lang lang : values()){
            Lang.lang.put(lang, config.getString("lang."+lang));
        }
    }

    public String get(){
        return lang.get(this);
    }
}
