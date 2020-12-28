package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.config.Lang;
import me.pljr.chatcontroller.utils.MsgUtils;
import me.pljr.pljrapispigot.utils.CommandUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
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
            sendMessage(player, Lang.ADMIN_HELP);
        }
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length >= 2){
            // /amsg <player> <message>
            if (!checkPlayer(sender, args[0])) return;
            Player receiver = Bukkit.getPlayer(args[0]);
            MsgUtils.sendMsg(sender, receiver, StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " "));
            return;
        }

        sendMessage(sender, Lang.ADMIN_HELP);
    }
}
