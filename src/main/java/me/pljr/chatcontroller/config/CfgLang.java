package me.pljr.chatcontroller.config;

import me.pljr.chatcontroller.enums.Lang;
import me.pljr.pljrapi.managers.ConfigManager;

import java.util.HashMap;
import java.util.List;

public class CfgLang {
    public static List<String> privateMessageSender;
    public static List<String> privateMessageReceiver;
    public static List<String> help;
    public static List<String> adminHelp;
    public static HashMap<Lang, String> lang;

    public static void load(ConfigManager config){
        privateMessageSender = config.getStringList("private-message-sender");
        privateMessageReceiver = config.getStringList("private-message-receiver");
        help = config.getStringList("help");
        adminHelp = config.getStringList("admin-help");
        lang = new HashMap<>();
        for (Lang lang : Lang.values()){
            CfgLang.lang.put(lang, config.getString("lang."+lang.toString()));
        }
    }
}
