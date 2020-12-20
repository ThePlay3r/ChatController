package me.pljr.chatcontroller.config;

import me.pljr.pljrapi.managers.ConfigManager;
import me.pljr.pljrapi.utils.PapiUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CfgGroups {
    private static String playerPlaceholder;
    public static HashMap<String, String> groups;
    public static List<String> groupNames;

    public static void load(ConfigManager config){
        playerPlaceholder = config.getString("player");
        groups = new HashMap<>();
        groupNames = new ArrayList<>();
        ConfigurationSection cs = config.getConfigurationSection("groups");
        for (String group : cs.getKeys(false)){
            groups.put(group, config.getString("groups."+group));
            groupNames.add(group);
        }
    }

    public static String getPlayerPlaceholder(Player player){
        return PapiUtil.setPlaceholders(player, playerPlaceholder);
    }
}
