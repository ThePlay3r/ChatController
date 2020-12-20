package me.pljr.chatcontroller.listeners;

import me.pljr.chatcontroller.ChatController;
import me.pljr.chatcontroller.config.CfgLang;
import me.pljr.chatcontroller.enums.Lang;
import me.pljr.chatcontroller.managers.QueryManager;
import me.pljr.pljrapi.utils.ChatUtil;
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
        ChatUtil.broadcast(CfgLang.lang.get(Lang.MESSAGE_LEAVE).replace("{player}", playerName), "", false);
    }
}
