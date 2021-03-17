package me.pljr.chatcontroller.utils;

import me.pljr.chatcontroller.config.Lang;
import me.pljr.chatcontroller.config.SoundType;
import me.pljr.chatcontroller.objects.ChatPlayer;
import me.pljr.pljrapispigot.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class MsgUtils {

    public static void sendMsg(CommandSender sender, ChatPlayer receiver, String message){
        String senderName = sender.getName();
        Player receiverPlayer = Bukkit.getPlayer(receiver.getUniqueId());
        String receiverName = receiverPlayer.getName();
        SoundType.PRIVATE_MESSAGE.get().play(receiverPlayer);
        ChatUtil.sendMessage(sender, Lang.PRIVATE_MESSAGE_SENDER.get().replace("{sender}", senderName).replace("{receiver}", receiverName).replace("{message}", message));
        ChatUtil.sendMessage(receiverPlayer, Lang.PRIVATE_MESSAGE_RECEIVER.get().replace("{sender}", senderName).replace("{receiver}", receiverName).replace("{message}", message));
        for (Player player : Bukkit.getOnlinePlayers()){
            if (receiver.isSpy()){
                ChatUtil.sendMessage(player, Lang.ADMIN_SPY.get().replace("{sender}", senderName).replace("{receiver}", receiverName).replace("{message}", message));
            }
        }
    }
}
