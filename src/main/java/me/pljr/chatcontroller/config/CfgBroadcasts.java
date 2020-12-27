package me.pljr.chatcontroller.config;

import me.pljr.chatcontroller.objects.Broadcast;
import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.objects.PLJRActionBar;
import me.pljr.pljrapispigot.objects.PLJRTitle;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CfgBroadcasts {
    public static HashMap<String, Broadcast> BROADCASTS;
    public static List<String> BROADCASTS_NAMES;

    public static void load(ConfigManager config){
        CfgBroadcasts.BROADCASTS = new HashMap<>();
        CfgBroadcasts.BROADCASTS_NAMES = new ArrayList<>();
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
            CfgBroadcasts.BROADCASTS.put(broadcast, new Broadcast(perm, sound, messageEnabled, message, titleEnabled, title, actionbarEnabled, actionbar));
            CfgBroadcasts.BROADCASTS_NAMES.add(broadcast);
        }
    }
}
