package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.config.CfgLang;
import me.pljr.chatcontroller.utils.MsgUtils;
import me.pljr.pljrapi.utils.CommandUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AMsgCommand extends CommandUtil {

    public AMsgCommand(){
        super("amsg", "chatcontroller.amsg.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length >= 2){
            // /amsg <player> <message>
            if (!checkPlayer(player, args[0])) return;
            Player receiver = Bukkit.getPlayer(args[0]);
            MsgUtils.sendMsg(player, receiver, StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " "));
            return;
        }

        if (checkPerm(player, "achatcontroller.help")){
            sendHelp(player, CfgLang.adminHelp);
        }
    }

    @Override
    public void onConsoleCommand(CommandSender sender, String[] args){
        if (args.length >= 2){
            // /amsg <player> <message>
            if (!checkPlayer(sender, args[0])) return;
            Player receiver = Bukkit.getPlayer(args[0]);
            MsgUtils.sendMsg(sender, receiver, StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " "));
            return;
        }

        sendHelp(sender, CfgLang.adminHelp);
    }
}
