package me.pljr.chatcontroller.listeners;

import me.pljr.chatcontroller.config.CfgGroups;
import me.pljr.chatcontroller.config.CfgLang;
import me.pljr.chatcontroller.config.CfgSettings;
import me.pljr.chatcontroller.enums.Lang;
import me.pljr.chatcontroller.utils.MentionUtil;
import me.pljr.pljrapi.utils.ChatUtil;
import me.pljr.pljrapi.utils.MiniMessageUtil;
import me.pljr.pljrapi.utils.PapiUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        for (String group : CfgGroups.groupNames){
            if (player.hasPermission("chatcontroller.groups."+group)){
                event.setCancelled(true);
                String message = event.getMessage();
                String playerName = player.getName();

                // AdminChat
                if (message.startsWith(CfgSettings.adminChat)){
                    if (player.hasPermission("chatcontroler.adminchat.use")){
                        ChatUtil.broadcast(PapiUtil.setPlaceholders(player, CfgLang.lang.get(Lang.ADMIN_CHAT_FORMAT).replace("%player", playerName).replace("%message", MiniMessageUtil.strip(message.substring(1)))),
                                "chatcontroller.adminchat.see", CfgSettings.bungee);
                        break;
                    }
                }

                // Chat
                ChatUtil.broadcast(PapiUtil.setPlaceholders(player, CfgGroups.groups.get(group).replace("%message", MiniMessageUtil.strip(message))), "", CfgSettings.bungee);

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
