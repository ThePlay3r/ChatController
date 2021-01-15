package me.pljr.chatcontroller.listeners;

import me.pljr.chatcontroller.config.ActionBarType;
import me.pljr.chatcontroller.config.CfgSettings;
import me.pljr.chatcontroller.config.Lang;
import me.pljr.chatcontroller.config.TitleType;
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
            ChatUtil.broadcast(PapiUtil.setPlaceholders(player, Lang.FIRST_JOIN_MESSAGE.get().replace("{player}", playerName)), "", false);
        }else{
            event.setJoinMessage(null);
            ChatUtil.broadcast(PapiUtil.setPlaceholders(player, Lang.JOIN_MESSAGE.get().replace("{player}", playerName)), "", false);
        }

        if (CfgSettings.JOIN_MESSAGE){
            ChatUtil.sendMessage(player, Lang.JOIN_MESSAGE_PLAYER.get().replace("{player}", playerName));
        }

        if (CfgSettings.JOIN_TITLE){
            new TitleBuilder(TitleType.JOIN_TITLE.get())
                    .replaceTitle("{player}", playerName)
                    .replaceSubtitle("{player}", playerName)
                    .create().send(player);
        }

        if (CfgSettings.JOIN_ACTIONBAR){
            new ActionBarBuilder(ActionBarType.JOIN_ACTIONBAR.get())
                    .replaceMessage("{player}", playerName)
                    .create().send(player);
        }
    }
}
