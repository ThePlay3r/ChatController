package me.pljr.chatcontroller.managers;

import me.pljr.chatcontroller.config.CfgBroadcasts;
import me.pljr.chatcontroller.config.CfgSettings;
import me.pljr.chatcontroller.objects.Broadcast;
import me.pljr.pljrapi.managers.ActionBarManager;
import me.pljr.pljrapi.managers.TitleManager;
import me.pljr.pljrapi.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Random;

public class BroadcastManager {
    private final Plugin plugin;

    public BroadcastManager(Plugin plugin){
        this.plugin = plugin;
    }

    public void startTimer(int seconds){
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, ()->{
            Broadcast broadcast = CfgBroadcasts.broadcasts.get(CfgBroadcasts.broadcastNames.get(new Random().nextInt(CfgBroadcasts.broadcastNames.size())));
            broadcast(broadcast);
        }, 0, 20*seconds);
    }

    public void broadcast(Broadcast broadcast){
        for (Player player : Bukkit.getOnlinePlayers()){
            player.playSound(player.getLocation(), broadcast.getSound(), 1, 1);
        }
        if (broadcast.isMessageEnabled()){
            ChatUtil.broadcast(broadcast.getMessage(), broadcast.getPerm(), CfgSettings.bungee);
        }
        if (broadcast.isTitleEnabled()){
            TitleManager.broadcast(broadcast.getTile());
        }
        if (broadcast.isActionbarEnabled()){
            ActionBarManager.broadcast(broadcast.getActionBar());
        }
    }
}
