package me.pljr.chatcontroller.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.Sound;

import java.util.HashMap;

public enum SoundType {
    MENTION,
    PRIVATE_MESSAGE;

    public static HashMap<SoundType, Sound> sounds;

    public static void load(ConfigManager config){
        sounds = new HashMap<>();
        for (SoundType soundType : SoundType.values()){
            sounds.put(soundType, config.getSound("sounds."+soundType.toString()));
        }
    }

    public Sound get(){
        return sounds.get(this);
    }
}
