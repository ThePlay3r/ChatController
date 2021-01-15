package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.ChatController;
import me.pljr.chatcontroller.config.Lang;
import me.pljr.chatcontroller.objects.CorePlayer;
import me.pljr.pljrapispigot.utils.CommandUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class MsgIgnoreCommand extends CommandUtil {

    public MsgIgnoreCommand(){
        super("msgignore", "chatcontroller.msgignore.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 0){
            // /msgignore
            if (!checkPerm(player, "chatcontroller.msgignore.use.fullignore")) return;
            CorePlayer corePlayer = ChatController.getPlayerManager().getCorePlayer(player.getUniqueId());
            if (corePlayer.isIgnoring()){
                sendMessage(player, Lang.MSGIGNORE_SUCCESS_FULLIGNORE_OFF.get());
                corePlayer.setIgnoring(false);
            }else{
                sendMessage(player, Lang.MSGIGNORE_SUCCESS_FULLIGNORE_ON.get());
                corePlayer.setIgnoring(true);
            }
            ChatController.getPlayerManager().setCorePlayer(player.getUniqueId(), corePlayer);
            return;
        }

        else if (args.length == 1){
            // /msgignore <player>
            if (!checkPerm(player, "chatcontroller.msgignore.use.player")) return;
            CorePlayer corePlayer = ChatController.getPlayerManager().getCorePlayer(player.getUniqueId());
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
            List<UUID> ignoreList = corePlayer.getIgnoreList();
            if (ignoreList.contains(offlinePlayer.getUniqueId())){
                sendMessage(player, Lang.MSGIGNORE_SUCCESS_UNIGNORE.get().replace("{player}", args[0]));
                ignoreList.remove(offlinePlayer.getUniqueId());
            }else{
                sendMessage(player, Lang.MSGIGNORE_SUCCESS_IGNORE.get().replace("{player}", args[0]));
                ignoreList.add(offlinePlayer.getUniqueId());
            }
            corePlayer.setIgnoreList(ignoreList);
            ChatController.getPlayerManager().setCorePlayer(player.getUniqueId(), corePlayer);
            return;
        }

        if (checkPerm(player, "chatcontroller.help")){
            sendMessage(player, Lang.HELP.get());
        }
    }
}
