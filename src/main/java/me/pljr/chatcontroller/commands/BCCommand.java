package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.config.CfgSettings;
import me.pljr.chatcontroller.config.Lang;
import me.pljr.pljrapispigot.utils.ChatUtil;
import me.pljr.pljrapispigot.utils.CommandUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BCCommand extends CommandUtil {

    public BCCommand(){
        super("bc", "chatcontroller.bc.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length > 0){
            ChatUtil.broadcast(StringUtils.join(ArrayUtils.subarray(args, 0, args.length), " "), "", CfgSettings.BUNGEE);
            return;
        }

        if (checkPerm(player, "achatcontroller.help")){
            sendMessage(player, Lang.ADMIN_HELP.get());
        }
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length > 0){
            ChatUtil.broadcast(StringUtils.join(ArrayUtils.subarray(args, 0, args.length), " "), "", CfgSettings.BUNGEE);
            return;
        }

        sendMessage(sender, Lang.ADMIN_HELP.get());
    }
}
