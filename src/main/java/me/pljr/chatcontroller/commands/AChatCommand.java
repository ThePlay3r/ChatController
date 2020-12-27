package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.config.CfgSettings;
import me.pljr.chatcontroller.config.Lang;
import me.pljr.pljrapispigot.utils.ChatUtil;
import me.pljr.pljrapispigot.utils.CommandUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AChatCommand extends CommandUtil {

    public AChatCommand(){
        super("achat", "chatcontroller.adminchat.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length > 0){
            ChatUtil.broadcast(StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " "), "chatcontroller.adminchat.see", CfgSettings.BUNGEE);
            return;
        }

        if (checkPerm(player, "achatcontroller.help")){
            sendMessage(player, Lang.ADMIN_HELP);
        }
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length > 0){
            ChatUtil.broadcast(StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " "), "chatcontroller.adminchat.see", CfgSettings.BUNGEE);
            return;
        }

        sendMessage(sender, Lang.ADMIN_HELP);
    }
}
