package me.pljr.chatcontroller.config;

import lombok.Getter;
import me.pljr.chatcontroller.objects.Broadcast;
import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.objects.PLJRActionBar;
import me.pljr.pljrapispigot.objects.PLJRTitle;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class Broadcasts {
    private final static String PATH = "broadcasts";

    private final HashMap<String, Broadcast> broadcasts;
    private final List<String> broadcastsNames;

    public Broadcasts(ConfigManager config){
        broadcasts = new HashMap<>();
        broadcastsNames = new ArrayList<>();
        ConfigurationSection broadcasts = config.getConfigurationSection(PATH);
        for (String broadcast : broadcasts.getKeys(false)){
            String perm = config.getString(PATH+"."+broadcast+".perm");
            Sound sound = config.getSound(PATH+"."+broadcast+".sound");
            boolean messageEnabled = config.getBoolean(PATH+"."+broadcast+".message.enabled");
            List<String> message = config.getStringList(PATH+"."+broadcast+".message.text");
            boolean titleEnabled = config.getBoolean(PATH+"."+broadcast+".title.enabled");
            PLJRTitle title = config.getPLJRTitle(PATH+"."+broadcast+".title.title");
            boolean actionbarEnabled = config.getBoolean(PATH+"."+broadcast+".actionbar.enabled");
            PLJRActionBar actionbar = config.getPLJRActionBar(PATH+"."+broadcast+".actionbar.actionbar");
            this.broadcasts.put(broadcast, new Broadcast(perm, sound, messageEnabled, message, titleEnabled, title, actionbarEnabled, actionbar));
            broadcastsNames.add(broadcast);
        }
    }
}
