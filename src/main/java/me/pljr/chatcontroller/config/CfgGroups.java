package me.pljr.chatcontroller.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.utils.PapiUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CfgGroups {
    private static String PLAYER_PLACEHOLDER;
    public static HashMap<String, String> GROUPS;
    public static List<String> GROUP_NAMES;

    public static void load(ConfigManager config){
        PLAYER_PLACEHOLDER = config.getString("player");
        GROUPS = new HashMap<>();
        GROUP_NAMES = new ArrayList<>();
        ConfigurationSection cs = config.getConfigurationSection("groups");
        for (String group : cs.getKeys(false)){
            GROUPS.put(group, config.getString("groups."+group));
            GROUP_NAMES.add(group);
        }
    }

    public static String getPlayerPlaceholder(Player player){
        return PapiUtil.setPlaceholders(player, PLAYER_PLACEHOLDER);
    }
}
