package me.pljr.chatcontroller.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.objects.PLJRSound;
import me.pljr.pljrapispigot.xseries.XSound;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public enum SoundType {
    MENTION(new PLJRSound(XSound.ENTITY_PLAYER_LEVELUP.parseSound(), 1, 1)),
    PRIVATE_MESSAGE(new PLJRSound(XSound.ENTITY_PLAYER_LEVELUP.parseSound(), 1, 1));

    private static HashMap<SoundType, PLJRSound> sounds;
    private final PLJRSound defaultValue;

    SoundType(PLJRSound defaultValue){
        this.defaultValue = defaultValue;
    }

    public static void load(ConfigManager config){
        sounds = new HashMap<>();
        FileConfiguration fileConfig = config.getConfig();
        for (SoundType soundType : SoundType.values()){
            if (!fileConfig.isSet(soundType.toString())){
                config.setPLJRSound(soundType.toString(), soundType.defaultValue);
            }else{
                sounds.put(soundType, config.getPLJRSound(soundType.toString()));
            }
        }
        config.save();
    }

    public PLJRSound get(){
        return sounds.getOrDefault(this, defaultValue);
    }
}
