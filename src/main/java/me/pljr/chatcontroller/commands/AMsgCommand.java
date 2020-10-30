package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.config.CfgLang;
import me.pljr.chatcontroller.utils.MsgUtils;
import me.pljr.pljrapi.utils.CommandUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AMsgCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "chatcontroller.amsg.use")) return false;

        if (args.length >= 2){
            // /amsg <player> <message>
            if (!checkPlayer(sender, args[0])) return false;
            Player receiver = Bukkit.getPlayer(args[0]);
            MsgUtils.sendMsg(sender, receiver, StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " "));
            return true;
        }

        if (checkPerm(sender, "achatcontroller.help")){
            sendHelp(sender, CfgLang.adminHelp);
        }
        return false;
    }
}
