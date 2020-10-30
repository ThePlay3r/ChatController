package me.pljr.chatcontroller.config;

import me.pljr.chatcontroller.enums.SoundType;
import me.pljr.pljrapi.managers.ConfigManager;
import org.bukkit.Sound;

import java.util.HashMap;

public class CfgSounds {
    public static HashMap<SoundType, Sound> sounds;

    public static void load(ConfigManager config){
        sounds = new HashMap<>();
        for (SoundType soundType : SoundType.values()){
            sounds.put(soundType, config.getSound("sounds."+soundType.toString()));
        }
    }
}
