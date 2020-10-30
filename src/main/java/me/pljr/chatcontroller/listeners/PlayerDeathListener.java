package me.pljr.chatcontroller.listeners;

import me.pljr.chatcontroller.config.CfgDeathMessages;
import me.pljr.chatcontroller.config.CfgSettings;
import me.pljr.pljrapi.utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;
import java.util.Random;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity() == null) return;

        Player player = event.getEntity();
        String playerName = player.getName();

        EntityDamageEvent.DamageCause cause = player.getLastDamageCause().getCause();

        String message = null;

        if (player.getKiller() != null){
            Player killer = player.getKiller();
            String killerName = killer.getName();

            List<String> messages = CfgDeathMessages.player;
            message = messages.get(new Random().nextInt(messages.size())).replace("%attacker", killerName).replace("%victim", playerName);
        }else if (CfgDeathMessages.messages.containsKey(cause)){
            List<String> messages = CfgDeathMessages.messages.get(cause);
            message = messages.get(new Random().nextInt(messages.size())).replace("%victim", playerName);
        }

        if (message != null){
            event.setDeathMessage(null);
            ChatUtil.broadcast(message, "", CfgSettings.bungee);
        }
    }
}
