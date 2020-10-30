package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.ChatController;
import me.pljr.chatcontroller.config.CfgLang;
import me.pljr.chatcontroller.enums.Lang;
import me.pljr.chatcontroller.objects.CorePlayer;
import me.pljr.pljrapi.utils.CommandUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AChatControllerCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "achatcontroller.use")) return false;

        if (args.length == 1){
            // /achatcontroller help
            if (args[0].equalsIgnoreCase("help")){
                if (!checkPerm(player, "achatcontroller.help")) return false;
                sendHelp(player, CfgLang.adminHelp);
                return true;
            }

            // /achatcontroller spy
            if (args[0].equalsIgnoreCase("spy")){
                if (!checkPerm(player, "achatcontroller.spy")) return false;
                CorePlayer corePlayer = ChatController.getPlayerManager().getCorePlayer(player.getUniqueId());
                if (corePlayer.isSpy()){
                    sendMessage(player, CfgLang.lang.get(Lang.ACHATCONTROLLER_SPY_OFF));
                    corePlayer.setSpy(false);
                }else{
                    sendMessage(player, CfgLang.lang.get(Lang.ACHATCONTROLLER_SPY_ON));
                    corePlayer.setSpy(true);
                }
                ChatController.getPlayerManager().setCorePlayer(player.getUniqueId(), corePlayer);
                return true;
            }
        }

        if (checkPerm(player, "achatcontroller.help")){
            sendHelp(player, CfgLang.adminHelp);
        }
        return false;
    }
}
