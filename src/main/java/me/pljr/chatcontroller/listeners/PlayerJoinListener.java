package me.pljr.chatcontroller.listeners;

import lombok.AllArgsConstructor;
import me.pljr.chatcontroller.config.ActionBarType;
import me.pljr.chatcontroller.config.Settings;
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

@AllArgsConstructor
public class PlayerJoinListener implements Listener {

    private final Settings settings;

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

        if (settings.isJoinMessage()){
            ChatUtil.sendMessage(player, Lang.JOIN_MESSAGE_PLAYER.get().replace("{player}", playerName));
        }

        if (settings.isJoinTitle()){
            new TitleBuilder(TitleType.JOIN_TITLE.get())
                    .replaceTitle("{player}", playerName)
                    .replaceSubtitle("{player}", playerName)
                    .create().send(player);
        }

        if (settings.isJoinActionbar()){
            new ActionBarBuilder(ActionBarType.JOIN_ACTIONBAR.get())
                    .replaceMessage("{player}", playerName)
                    .create().send(player);
        }
    }
}
