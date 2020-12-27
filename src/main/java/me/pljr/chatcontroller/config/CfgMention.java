package me.pljr.chatcontroller.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.objects.PLJRActionBar;
import me.pljr.pljrapispigot.objects.PLJRTitle;

import java.util.List;

public class CfgMention {
    public static boolean MESSAGE_ENABLED;
    public static List<String> MESSAGE;
    public static boolean TITLE_ENABLED;
    public static PLJRTitle TITLE;
    public static boolean ACTIONBAR_ENABLED;
    public static PLJRActionBar ACTIONBAR;

    public static void load(ConfigManager config){
        MESSAGE_ENABLED = config.getBoolean("mention.message.enabled");
        MESSAGE = config.getStringList("mention.message.text");
        TITLE_ENABLED = config.getBoolean("mention.title.enabled");
        TITLE = config.getPLJRTitle("mention.title");
        ACTIONBAR_ENABLED = config.getBoolean("mention.actionbar.enabled");
        ACTIONBAR = config.getPLJRActionBar("mention.actionbar");
    }
}
