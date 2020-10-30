package me.pljr.chatcontroller.listeners;

import me.pljr.chatcontroller.ChatController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class AsyncPlayerPreLoginListener implements Listener {

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event){
        ChatController.getQueryManager().loadPlayerSync(event.getUniqueId());
    }
}
