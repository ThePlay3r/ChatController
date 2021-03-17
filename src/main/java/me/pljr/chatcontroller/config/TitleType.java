package me.pljr.chatcontroller.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.objects.PLJRActionBar;
import me.pljr.pljrapispigot.objects.PLJRTitle;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public enum TitleType {
    JOIN_TITLE(new PLJRTitle("&fHey &b{player}", "&fWelcome to the server!", 0, 40,20)),
    MENTION_TITLE(new PLJRTitle("&a&lMention!", "&e{player} &bmentioned you in chat!", 10, 40 ,10));

    private static HashMap<TitleType, PLJRTitle> titleTypes;
    private final PLJRTitle defaultValue;

    TitleType(PLJRTitle defaultValue){
        this.defaultValue = defaultValue;
    }

    public static void load(ConfigManager config){
        titleTypes = new HashMap<>();
        FileConfiguration fileConfig = config.getConfig();
        for (TitleType titleType : values()){
            if (!fileConfig.isSet(titleType.toString())){
                config.setPLJRTitle(titleType.toString(), titleType.defaultValue);
            }else{
                titleTypes.put(titleType, config.getPLJRTitle(titleType.toString()));
            }
        }
        config.save();
    }

    public PLJRTitle get(){
        return titleTypes.getOrDefault(this, defaultValue);
    }
}
