package me.pljr.chatcontroller.config;

import me.pljr.pljrapi.managers.ConfigManager;
import me.pljr.pljrapi.objects.PLJRActionBar;
import me.pljr.pljrapi.objects.PLJRTitle;

import java.util.List;

public class CfgMention {
    public static boolean messageEnabled;
    public static List<String> message;
    public static boolean titleEnabled;
    public static PLJRTitle title;
    public static boolean actionbarEnabled;
    public static PLJRActionBar actionBar;

    public static void load(ConfigManager config){
        messageEnabled = config.getBoolean("mention.message.enabled");
        message = config.getStringList("mention.message.text");
        titleEnabled = config.getBoolean("mention.title.enabled");
        title = config.getPLJRTitle("mention.title");
        actionbarEnabled = config.getBoolean("mention.actionbar.enabled");
        actionBar = config.getPLJRActionBar("mention.actionbar");
    }
}
