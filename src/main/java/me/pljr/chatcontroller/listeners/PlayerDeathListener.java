package me.pljr.chatcontroller.listeners;

import lombok.AllArgsConstructor;
import me.pljr.chatcontroller.config.DeathMessages;
import me.pljr.chatcontroller.config.Settings;
import me.pljr.pljrapispigot.utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;
import java.util.Random;

@AllArgsConstructor
public class PlayerDeathListener implements Listener {

    private final DeathMessages deathMessages;
    private final Settings settings;

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

            List<String> messages = deathMessages.getPlayer();
            message = messages.get(new Random().nextInt(messages.size())).replace("{attacker}", killerName).replace("{victim}", playerName);
        }else if (deathMessages.getMessages().containsKey(cause)){
            List<String> messages = deathMessages.getMessages().get(cause);
            message = messages.get(new Random().nextInt(messages.size())).replace("{victim}", playerName);
        }

        if (message != null){
            event.setDeathMessage(null);
            ChatUtil.broadcast(message, "", settings.isBungee());
        }
    }
}
