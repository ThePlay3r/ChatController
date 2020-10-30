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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "chatcontroller.msg.use")) return false;

        if (args.length >= 2){
            // /msg <player> <message>
            if (!checkPlayer(sender, args[0])) return false;
            Player receiver = Bukkit.getPlayer(args[0]);
            CorePlayer receiverCore = ChatController.getPlayerManager().getCorePlayer(receiver.getUniqueId());
            if (receiverCore.isIgnoring()){
                sendMessage(sender, CfgLang.lang.get(Lang.MSG_FAILURE_IGNORING).replace("%player", args[0]));
                return false;
            }
            if (sender instanceof Player){
                Player player = (Player) sender;
                if (receiverCore.getIgnoreList().contains(player.getUniqueId())){
                    sendMessage(player, CfgLang.lang.get(Lang.MSG_FAILURE_BLOCKED).replace("%player", args[0]));
                    return false;
                }
            }
            MsgUtils.sendMsg(sender, receiver, StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " "));
            return true;
        }

        if (checkPerm(sender, "chatcontroller.help")){
            sendHelp(sender, CfgLang.help);
        }
        return false;
    }
}
