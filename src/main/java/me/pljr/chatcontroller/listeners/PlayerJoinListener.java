package me.pljr.chatcontroller.listeners;

import me.pljr.chatcontroller.config.CfgLang;
import me.pljr.chatcontroller.enums.Lang;
import me.pljr.pljrapi.utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String playerName = player.getName();

        if (!player.hasPlayedBefore()){
            event.setJoinMessage(null);
            ChatUtil.broadcast(CfgLang.lang.get(Lang.MESSAGE_JOIN_FIRST).replace("{player}", playerName), "", false);
        }else{
            event.setJoinMessage(null);
            ChatUtil.broadcast(CfgLang.lang.get(Lang.MESSAGE_JOIN).replace("{player}", playerName), "", false);
        }
    }
}
