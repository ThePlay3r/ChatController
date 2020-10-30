package me.pljr.chatcontroller.utils;

import me.pljr.chatcontroller.ChatController;
import me.pljr.chatcontroller.config.CfgLang;
import me.pljr.chatcontroller.config.CfgSettings;
import me.pljr.chatcontroller.config.CfgSounds;
import me.pljr.chatcontroller.enums.Lang;
import me.pljr.chatcontroller.enums.SoundType;
import me.pljr.chatcontroller.objects.CorePlayer;
import me.pljr.pljrapi.utils.ChatUtil;
import me.pljr.pljrapi.utils.MiniMessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MsgUtils {

    public static void sendMsg(CommandSender sender, Player receiver, String message){
        String senderName = sender.getName();
        String receiverName = receiver.getName();
        message = MiniMessageUtil.strip(message);
        if (CfgSettings.sounds) receiver.playSound(receiver.getLocation(), CfgSounds.sounds.get(SoundType.PRIVATE_MESSAGE), 1, 1);
        for (String line : CfgLang.privateMessageSender){
            ChatUtil.sendMessage(sender, line.replace("%sender", senderName).replace("%receiver", receiverName).replace("%message", message));
        }
        for (String line : CfgLang.privateMessageReceiver){
            ChatUtil.sendMessage(receiver, line.replace("%sender", senderName).replace("%receiver", receiverName).replace("%message", message));
        }
        for (Player player : Bukkit.getOnlinePlayers()){
            UUID playerId = player.getUniqueId();
            CorePlayer corePlayer = ChatController.getPlayerManager().getCorePlayer(playerId);
            if (corePlayer.isSpy()){
                ChatUtil.sendMessage(player, CfgLang.lang.get(Lang.ADMIN_SPY).replace("%sender", senderName).replace("%receiver", receiverName).replace("%message", message));
            }
        }
    }
}
