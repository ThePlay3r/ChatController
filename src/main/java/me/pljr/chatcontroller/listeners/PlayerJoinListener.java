package me.pljr.chatcontroller.listeners;

import me.pljr.chatcontroller.config.CfgSettings;
import me.pljr.chatcontroller.config.Lang;
import me.pljr.pljrapispigot.builders.ActionBarBuilder;
import me.pljr.pljrapispigot.builders.TitleBuilder;
import me.pljr.pljrapispigot.utils.ChatUtil;
import me.pljr.pljrapispigot.utils.PapiUtil;
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
            ChatUtil.broadcast(PapiUtil.setPlaceholders(player, Lang.MESSAGE_JOIN_FIRST.get().replace("{player}", playerName)), "", false);
        }else{
            event.setJoinMessage(null);
            ChatUtil.broadcast(PapiUtil.setPlaceholders(player, Lang.MESSAGE_JOIN.get().replace("{player}", playerName)), "", false);
        }

        if (CfgSettings.JOIN_MESSAGE){
            for (String line : Lang.JOIN_MESSAGE){
                ChatUtil.sendMessage(player, line.replace("{player}", playerName));
            }
        }

        if (CfgSettings.JOIN_TITLE){
            new TitleBuilder(Lang.JOIN_TITLE)
                    .replaceTitle("{player}", playerName)
                    .replaceSubtitle("{player}", playerName)
                    .create().send(player);
        }

        if (CfgSettings.JOIN_ACTIONBAR){
            new ActionBarBuilder(Lang.JOIN_ACTIONBAR)
                    .replaceMessage("{player}", playerName)
                    .create().send(player);
        }
    }
}
