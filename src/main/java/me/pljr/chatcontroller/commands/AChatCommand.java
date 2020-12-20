package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.config.CfgLang;
import me.pljr.chatcontroller.config.CfgSettings;
import me.pljr.pljrapi.utils.ChatUtil;
import me.pljr.pljrapi.utils.CommandUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AChatCommand extends CommandUtil {

    public AChatCommand(){
        super("achat", "chatcontroller.adminchat.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length > 0){
            ChatUtil.broadcast(StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " "), "chatcontroller.adminchat.see", CfgSettings.bungee);
            return;
        }

        if (checkPerm(player, "achatcontroller.help")){
            sendHelp(player, CfgLang.adminHelp);
        }
    }

    @Override
    public void onConsoleCommand(CommandSender sender, String[] args){
        if (args.length > 0){
            ChatUtil.broadcast(StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " "), "chatcontroller.adminchat.see", CfgSettings.bungee);
            return;
        }

        sendHelp(sender, CfgLang.adminHelp);
    }
}
