package me.pljr.chatcontroller.managers;

import lombok.AllArgsConstructor;
import me.pljr.chatcontroller.config.Broadcasts;
import me.pljr.chatcontroller.config.Settings;
import me.pljr.chatcontroller.objects.Broadcast;
import me.pljr.pljrapispigot.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Random;

@AllArgsConstructor
public class BroadcastManager {
    private final Plugin plugin;
    private final Broadcasts broadcasts;
    private final Settings settings;

    public void startTimer(int seconds){
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, ()->{
            Broadcast broadcast = broadcasts.getBroadcasts().get(broadcasts.getBroadcastsNames().get(new Random().nextInt(broadcasts.getBroadcastsNames().size())));
            broadcast(broadcast);
        }, 0, 20L * seconds);
    }

    public void broadcast(Broadcast broadcast){
        for (Player player : Bukkit.getOnlinePlayers()){
            player.playSound(player.getLocation(), broadcast.getSound(), 1, 1);
        }
        if (broadcast.isMessageEnabled()){
            ChatUtil.broadcast(broadcast.getMessage(), broadcast.getPerm(), settings.isBungee());
        }
        if (broadcast.isTitleEnabled()){
            broadcast.getTile().broadcast();
        }
        if (broadcast.isActionbarEnabled()){
            broadcast.getActionBar().broadcast();
        }
    }
}
