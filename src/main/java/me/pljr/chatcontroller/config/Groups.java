package me.pljr.chatcontroller.config;

import lombok.Getter;
import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.utils.PapiUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class Groups {
    private final static String PATH = "groups";

    private final String playerPlaceholder;
    private final HashMap<String, String> groups;
    private final List<String> groupNames;

    public Groups(ConfigManager config){
        playerPlaceholder = config.getString("player");
        groups = new HashMap<>();
        groupNames = new ArrayList<>();
        ConfigurationSection cs = config.getConfigurationSection(PATH);
        for (String group : cs.getKeys(false)){
            groups.put(group, config.getString(PATH+"."+group));
            groupNames.add(group);
        }
    }

    public String getPlayerPlaceholder(Player player){
        return PapiUtil.setPlaceholders(player, playerPlaceholder);
    }
}
