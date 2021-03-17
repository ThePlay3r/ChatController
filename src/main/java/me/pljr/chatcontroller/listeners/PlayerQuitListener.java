package me.pljr.chatcontroller.listeners;

import lombok.AllArgsConstructor;
import me.pljr.chatcontroller.ChatController;
import me.pljr.chatcontroller.config.Lang;
import me.pljr.chatcontroller.managers.PlayerManager;
import me.pljr.chatcontroller.managers.QueryManager;
import me.pljr.pljrapispigot.utils.ChatUtil;
import me.pljr.pljrapispigot.utils.PapiUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

@AllArgsConstructor
public class PlayerQuitListener implements Listener {

    private final PlayerManager playerManager;

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();
        String playerName = player.getName();

        playerManager.savePlayer(playerId);

        event.setQuitMessage(null);
        ChatUtil.broadcast(PapiUtil.setPlaceholders(player, Lang.QUIT_MESSAGE.get().replace("{player}", playerName)), "", false);
    }
}
