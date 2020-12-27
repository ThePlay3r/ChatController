package me.pljr.chatcontroller.listeners;

import me.pljr.chatcontroller.config.CfgGroups;
import me.pljr.chatcontroller.config.CfgSettings;
import me.pljr.chatcontroller.config.Lang;
import me.pljr.chatcontroller.utils.MentionUtil;
import me.pljr.pljrapispigot.utils.ChatUtil;
import me.pljr.pljrapispigot.utils.MiniMessageUtil;
import me.pljr.pljrapispigot.utils.PapiUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        for (String group : CfgGroups.GROUP_NAMES){
            if (player.hasPermission("chatcontroller.groups."+group)){
                event.setCancelled(true);
                String message = event.getMessage();
                String playerName = player.getName();

                // AdminChat
                if (message.startsWith(CfgSettings.ADMIN_CHAT)){
                    if (player.hasPermission("chatcontroler.adminchat.use")){
                        ChatUtil.broadcast(PapiUtil.setPlaceholders(player, Lang.ADMIN_CHAT_FORMAT.get().replace("{player}", playerName).replace("{message}", MiniMessageUtil.strip(message.substring(1)))),
                                "chatcontroller.adminchat.see", CfgSettings.BUNGEE);
                        break;
                    }
                }

                // Chat
                ChatUtil.broadcast(PapiUtil.setPlaceholders(player,
                        CfgGroups.GROUPS.get(group)
                                .replace("{player}", CfgGroups.getPlayerPlaceholder(player))
                                .replace("{message}", MiniMessageUtil.strip(message))), "", CfgSettings.BUNGEE);

                // Mention
                for (Player bukkitPlayer : Bukkit.getOnlinePlayers()){
                    if (message.contains(bukkitPlayer.getName())){
                        MentionUtil.mention(bukkitPlayer, playerName);
                    }
                }
                break;
            }
        }
    }
}
