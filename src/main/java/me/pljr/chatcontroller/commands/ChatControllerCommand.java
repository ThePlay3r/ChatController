package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.config.CfgLang;
import me.pljr.pljrapi.utils.CommandUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatControllerCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "chatcontroller.help")) return false;
        sendHelp(sender, CfgLang.help);
        return true;
    }
}
