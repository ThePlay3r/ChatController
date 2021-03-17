package me.pljr.chatcontroller.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.objects.PLJRActionBar;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public enum ActionBarType {
    JOIN_ACTIONBAR(new PLJRActionBar("&fHey &b{player}&f, welcome to server!", 40)),
    MENTION_ACTIONBAR(new PLJRActionBar("&e{player} &amentioned you!", 40));

    private static HashMap<ActionBarType, PLJRActionBar> actionbars;
    private final PLJRActionBar defaultValue;

    ActionBarType(PLJRActionBar defaultValue){
        this.defaultValue = defaultValue;
    }

    public static void load(ConfigManager config){
        actionbars = new HashMap<>();
        FileConfiguration fileConfig = config.getConfig();
        for (ActionBarType actionBarType : values()){
            if (!fileConfig.isSet(actionBarType.toString())){
                config.setPLJRActionBar(actionBarType.toString(), actionBarType.defaultValue);
            }else{
                actionbars.put(actionBarType, config.getPLJRActionBar(actionBarType.toString()));
            }
        }
        config.save();
    }

    public PLJRActionBar get(){
        return actionbars.getOrDefault(this, defaultValue);
    }
}
