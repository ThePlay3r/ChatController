package me.pljr.chatcontroller.config;

import me.pljr.chatcontroller.objects.Broadcast;
import me.pljr.pljrapi.managers.ConfigManager;
import me.pljr.pljrapi.objects.PLJRActionBar;
import me.pljr.pljrapi.objects.PLJRTitle;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CfgBroadcasts {
    public static HashMap<String, Broadcast> broadcasts;
    public static List<String> broadcastNames;

    public static void load(ConfigManager config){
        CfgBroadcasts.broadcasts = new HashMap<>();
        CfgBroadcasts.broadcastNames = new ArrayList<>();
        ConfigurationSection broadcasts = config.getConfigurationSection("broadcasts");
        for (String broadcast : broadcasts.getKeys(false)){
            String perm = config.getString("broadcasts."+broadcast+".perm");
            Sound sound = config.getSound("broadcasts."+broadcast+".sound");
            boolean messageEnabled = config.getBoolean("broadcasts."+broadcast+".message.enabled");
            List<String> message = config.getStringList("broadcasts."+broadcast+".message.text");
            boolean titleEnabled = config.getBoolean("broadcasts."+broadcast+".title.enabled");
            PLJRTitle title = config.getPLJRTitle("broadcasts."+broadcast+".title.title");
            boolean actionbarEnabled = config.getBoolean("broadcasts."+broadcast+".actionbar.enabled");
            PLJRActionBar actionbar = config.getPLJRActionBar("broadcasts."+broadcast+".actionbar.actionbar");
            CfgBroadcasts.broadcasts.put(broadcast, new Broadcast(perm, sound, messageEnabled, message, titleEnabled, title, actionbarEnabled, actionbar));
            CfgBroadcasts.broadcastNames.add(broadcast);
        }
    }
}
