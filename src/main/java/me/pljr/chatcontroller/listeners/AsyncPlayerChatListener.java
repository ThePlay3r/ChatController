package me.pljr.chatcontroller.listeners;

import lombok.AllArgsConstructor;
import me.pljr.chatcontroller.config.*;
import me.pljr.pljrapispigot.builders.ActionBarBuilder;
import me.pljr.pljrapispigot.builders.TitleBuilder;
import me.pljr.pljrapispigot.utils.ChatUtil;
import me.pljr.pljrapispigot.utils.PapiUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@AllArgsConstructor
public class AsyncPlayerChatListener implements Listener {

    private final Groups groups;
    private final Settings settings;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        for (String group : groups.getGroupNames()){
            if (player.hasPermission("chatcontroller.groups."+group)){
                event.setCancelled(true);
                String message = event.getMessage();
                String playerName = player.getName();

                // AdminChat
                if (message.startsWith(settings.getAdminChat())){
                    if (player.hasPermission("chatcontroler.adminchat.use")){
                        Bukkit.dispatchCommand(player, "achat " + message.substring(1));
                        break;
                    }
                }

                // Chat
                ChatUtil.broadcast(PapiUtil.setPlaceholders(player,
                        groups.getGroups().get(group)
                                .replace("{player}", groups.getPlayerPlaceholder(player))
                                .replace("{message}", message)), "", settings.isBungee());

                // Mention
                for (Player bukkitPlayer : Bukkit.getOnlinePlayers()){
                    if (message.contains(bukkitPlayer.getName())){
                        if (settings.isMentionMessage()) ChatUtil.sendMessage(bukkitPlayer, Lang.MENTION_MESSAGE.get().replace("{player}", playerName));
                        if (settings.isMentionTitle()) new TitleBuilder(TitleType.MENTION_TITLE.get())
                                .replaceTitle("{player}", playerName)
                                .replaceSubtitle("{player}", playerName)
                                .create().send(bukkitPlayer);
                        if (settings.isMentionActionbar()) new ActionBarBuilder(ActionBarType.MENTION_ACTIONBAR.get())
                                .replaceMessage("{player}", playerName)
                                .create().send(bukkitPlayer);
                        if (settings.isSounds()) SoundType.PRIVATE_MESSAGE.get().play(bukkitPlayer);
                    }
                }
                break;
            }
        }
    }
}
