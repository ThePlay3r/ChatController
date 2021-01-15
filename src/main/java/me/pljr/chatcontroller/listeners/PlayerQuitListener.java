package me.pljr.chatcontroller.listeners;

import me.pljr.chatcontroller.ChatController;
import me.pljr.chatcontroller.config.Lang;
import me.pljr.chatcontroller.managers.QueryManager;
import me.pljr.pljrapispigot.utils.ChatUtil;
import me.pljr.pljrapispigot.utils.PapiUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();
        String playerName = player.getName();

        QueryManager queryManager = ChatController.getQueryManager();
        queryManager.savePlayer(playerId);

        event.setQuitMessage(null);
        ChatUtil.broadcast(PapiUtil.setPlaceholders(player, Lang.QUIT_MESSAGE.get().replace("{player}", playerName)), "", false);
    }
}
