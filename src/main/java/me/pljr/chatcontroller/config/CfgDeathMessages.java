package me.pljr.chatcontroller.config;

import me.pljr.pljrapi.managers.ConfigManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;
import java.util.List;

public class CfgDeathMessages {
    public static List<String> player;
    public static HashMap<EntityDamageEvent.DamageCause, List<String>> messages;

    public static void load(ConfigManager config){
        player = config.getStringList("death-messages.player");
        messages = new HashMap<>();
        ConfigurationSection cs = config.getConfigurationSection("death-messages.causes");
        if (cs != null){
            for (String cause : cs.getKeys(false)){
                messages.put(config.getDamageCause("death-messages.causes."+cause+".type"), config.getStringList("death-messages.causes."+cause+".messages"));
            }
        }
    }
}
