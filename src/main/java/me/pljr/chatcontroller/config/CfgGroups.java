package me.pljr.chatcontroller.config;

import me.pljr.pljrapi.managers.ConfigManager;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CfgGroups {
    public static HashMap<String, String> groups;
    public static List<String> groupNames;

    public static void load(ConfigManager config){
        groups = new HashMap<>();
        groupNames = new ArrayList<>();
        ConfigurationSection cs = config.getConfigurationSection("groups");
        for (String group : cs.getKeys(false)){
            groups.put(group, config.getString("groups."+group));
            groupNames.add(group);
        }
    }
}
