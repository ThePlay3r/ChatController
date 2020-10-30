package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.config.CfgLang;
import me.pljr.chatcontroller.config.CfgSettings;
import me.pljr.pljrapi.utils.ChatUtil;
import me.pljr.pljrapi.utils.CommandUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AChatCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "chatcontroler.adminchat.use")) return false;

        if (args.length > 0){
            ChatUtil.broadcast(StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " "), "chatcontroller.adminchat.see", CfgSettings.bungee);
            return true;
        }

        if (checkPerm(sender, "achatcontroller.help")){
            sendHelp(sender, CfgLang.adminHelp);
        }
        return false;
    }
}