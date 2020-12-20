package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.ChatController;
import me.pljr.chatcontroller.config.CfgLang;
import me.pljr.chatcontroller.enums.Lang;
import me.pljr.chatcontroller.objects.CorePlayer;
import me.pljr.chatcontroller.utils.MsgUtils;
import me.pljr.pljrapi.utils.CommandUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgCommand extends CommandUtil {

    public MsgCommand(){
        super("msg", "chatcontroller.msg.use");
    }

    public void onPlayerCommand(Player player, String[] args){
        if (args.length >= 2){
            // /msg <player> <message>
            if (!checkPlayer(player, args[0])) return;
            Player receiver = Bukkit.getPlayer(args[0]);
            CorePlayer receiverCore = ChatController.getPlayerManager().getCorePlayer(receiver.getUniqueId());
            if (receiverCore.isIgnoring()){
                sendMessage(player, CfgLang.lang.get(Lang.MSG_FAILURE_IGNORING).replace("{player}", args[0]));
                return;
            }
            if (receiverCore.getIgnoreList().contains(player.getUniqueId())){
                sendMessage(player, CfgLang.lang.get(Lang.MSG_FAILURE_BLOCKED).replace("{player}", args[0]));
                return;
            }
            MsgUtils.sendMsg(player, receiver, StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " "));
            return;
        }

        if (checkPerm(player, "chatcontroller.help")){
            sendHelp(player, CfgLang.help);
        }
    }

    @Override
    public void onConsoleCommand(CommandSender sender, String[] args){
        if (args.length >= 2){
            // /msg <player> <message>
            if (!checkPlayer(sender, args[0])) return;
            Player receiver = Bukkit.getPlayer(args[0]);
            CorePlayer receiverCore = ChatController.getPlayerManager().getCorePlayer(receiver.getUniqueId());
            if (receiverCore.isIgnoring()){
                sendMessage(sender, CfgLang.lang.get(Lang.MSG_FAILURE_IGNORING).replace("{player}", args[0]));
                return;
            }
            MsgUtils.sendMsg(sender, receiver, StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " "));
            return;
        }

        sendHelp(sender, CfgLang.help);
    }
}
