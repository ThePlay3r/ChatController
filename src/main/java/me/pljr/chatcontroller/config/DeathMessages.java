package me.pljr.chatcontroller.config;

import lombok.Getter;
import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;
import java.util.List;

@Getter
public class DeathMessages {
    private final static String PATH = "death-messages";

    private final List<String> player;
    private final HashMap<EntityDamageEvent.DamageCause, List<String>> messages;

    public DeathMessages(ConfigManager config){
        player = config.getStringList(PATH+".player");
        messages = new HashMap<>();
        ConfigurationSection cs = config.getConfigurationSection(PATH+".causes");
        if (cs != null){
            for (String cause : cs.getKeys(false)){
                messages.put(config.getDamageCause(PATH+".causes."+cause+".type"), config.getStringList(PATH+".causes."+cause+".messages"));
            }
        }
    }
}
