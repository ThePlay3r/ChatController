package me.pljr.chatcontroller.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;
import java.util.List;

public class CfgDeathMessages {
    public static List<String> PLAYER;
    public static HashMap<EntityDamageEvent.DamageCause, List<String>> MESSAGES;

    public static void load(ConfigManager config){
        PLAYER = config.getStringList("death-messages.player");
        MESSAGES = new HashMap<>();
        ConfigurationSection cs = config.getConfigurationSection("death-messages.causes");
        if (cs != null){
            for (String cause : cs.getKeys(false)){
                MESSAGES.put(config.getDamageCause("death-messages.causes."+cause+".type"), config.getStringList("death-messages.causes."+cause+".messages"));
            }
        }
    }
}
