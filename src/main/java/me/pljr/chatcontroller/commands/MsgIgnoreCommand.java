package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.ChatController;
import me.pljr.chatcontroller.config.CfgLang;
import me.pljr.chatcontroller.enums.Lang;
import me.pljr.chatcontroller.objects.CorePlayer;
import me.pljr.pljrapi.utils.CommandUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class MsgIgnoreCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "chatcontroller.msgignore.use")) return false;

        if (args.length == 0){
            // /msgignore
            if (!checkPerm(player, "chatcontroller.msgignore.use.fullignore")) return false;
            CorePlayer corePlayer = ChatController.getPlayerManager().getCorePlayer(player.getUniqueId());
            if (corePlayer.isIgnoring()){
                sendMessage(player, CfgLang.lang.get(Lang.MSGIGNORE_SUCCESS_FULLIGNORE_OFF));
                corePlayer.setIgnoring(false);
            }else{
                sendMessage(player, CfgLang.lang.get(Lang.MSGIGNORE_SUCCESS_FULLIGNORE_ON));
                corePlayer.setIgnoring(true);
            }
            ChatController.getPlayerManager().setCorePlayer(player.getUniqueId(), corePlayer);
            return true;
        }

        else if (args.length == 1){
            // /msgignore <player>
            if (!checkPerm(player, "chatcontroller.msgignore.use.player")) return false;
            CorePlayer corePlayer = ChatController.getPlayerManager().getCorePlayer(player.getUniqueId());
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
            List<UUID> ignoreList = corePlayer.getIgnoreList();
            if (ignoreList.contains(offlinePlayer.getUniqueId())){
                sendMessage(player, CfgLang.lang.get(Lang.MSGIGNORE_SUCCESS_UNIGNORE).replace("%player", args[0]));
                ignoreList.remove(offlinePlayer.getUniqueId());
            }else{
                sendMessage(player, CfgLang.lang.get(Lang.MSGIGNORE_SUCCESS_IGNORE).replace("%player", args[0]));
                ignoreList.add(offlinePlayer.getUniqueId());
            }
            corePlayer.setIgnoreList(ignoreList);
            ChatController.getPlayerManager().setCorePlayer(player.getUniqueId(), corePlayer);
            return true;
        }

        if (checkPerm(player, "chatcontroller.help")){
            sendHelp(sender, CfgLang.help);
        }
        return false;
    }
}
