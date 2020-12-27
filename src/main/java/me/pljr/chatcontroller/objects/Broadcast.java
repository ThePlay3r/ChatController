package me.pljr.chatcontroller.objects;

import me.pljr.pljrapispigot.objects.PLJRActionBar;
import me.pljr.pljrapispigot.objects.PLJRTitle;
import org.bukkit.Sound;

import java.util.List;

public class Broadcast {
    private final String perm;
    private final Sound sound;
    private final boolean messageEnabled;
    private final List<String> message;
    private final boolean titleEnabled;
    private final PLJRTitle tile;
    private final boolean actionbarEnabled;
    private final PLJRActionBar actionBar;

    public Broadcast(String perm, Sound sound, boolean messageEnabled, List<String> message, boolean titleEnabled, PLJRTitle title, boolean actionbarEnabled, PLJRActionBar actionBar){
        this.perm = perm;
        this.sound = sound;
        this.messageEnabled = messageEnabled;
        this.message = message;
        this.titleEnabled = titleEnabled;
        this.tile = title;
        this.actionbarEnabled = actionbarEnabled;
        this.actionBar =actionBar;
    }

    public String getPerm() {
        return perm;
    }

    public Sound getSound() {
        return sound;
    }

    public boolean isMessageEnabled() {
        return messageEnabled;
    }

    public List<String> getMessage() {
        return message;
    }

    public boolean isTitleEnabled() {
        return titleEnabled;
    }

    public PLJRTitle getTile() {
        return tile;
    }

    public boolean isActionbarEnabled() {
        return actionbarEnabled;
    }

    public PLJRActionBar getActionBar() {
        return actionBar;
    }
}
