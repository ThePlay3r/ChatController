package me.pljr.chatcontroller.utils;

import me.pljr.chatcontroller.ChatController;
import me.pljr.chatcontroller.config.CfgSettings;
import me.pljr.chatcontroller.config.Lang;
import me.pljr.chatcontroller.config.SoundType;
import me.pljr.chatcontroller.objects.CorePlayer;
import me.pljr.pljrapispigot.utils.ChatUtil;
import me.pljr.pljrapispigot.utils.MiniMessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MsgUtils {

    public static void sendMsg(CommandSender sender, Player receiver, String message){
        String senderName = sender.getName();
        String receiverName = receiver.getName();
        message = MiniMessageUtil.strip(message);
        if (CfgSettings.SOUNDS) receiver.playSound(receiver.getLocation(), SoundType.PRIVATE_MESSAGE.get(), 1, 1);
        for (String line : Lang.PRIVATE_MESSAGE_SENDER){
            ChatUtil.sendMessage(sender, line.replace("{sender}", senderName).replace("{receiver}", receiverName).replace("{message}", message));
        }
        for (String line : Lang.PRIVATE_MESSAGE_RECEIVER){
            ChatUtil.sendMessage(receiver, line.replace("{sender}", senderName).replace("{receiver}", receiverName).replace("{message}", message));
        }
        for (Player player : Bukkit.getOnlinePlayers()){
            UUID playerId = player.getUniqueId();
            CorePlayer corePlayer = ChatController.getPlayerManager().getCorePlayer(playerId);
            if (corePlayer.isSpy()){
                ChatUtil.sendMessage(player, Lang.ADMIN_SPY.get().replace("{sender}", senderName).replace("{receiver}", receiverName).replace("{message}", message));
            }
        }
    }
}
