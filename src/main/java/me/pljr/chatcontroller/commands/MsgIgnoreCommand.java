package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.ChatController;
import me.pljr.chatcontroller.config.Lang;
import me.pljr.chatcontroller.managers.PlayerManager;
import me.pljr.chatcontroller.objects.ChatPlayer;
import me.pljr.pljrapispigot.commands.BukkitCommand;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class MsgIgnoreCommand extends BukkitCommand {

    private final PlayerManager playerManager;

    public MsgIgnoreCommand(PlayerManager playerManager){
        super("msgignore", "chatcontroller.msgignore.use");
        this.playerManager = playerManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 0){
            // /msgignore
            if (!checkPerm(player, "chatcontroller.msgignore.use.fullignore")) return;
            playerManager.getPlayer(player.getUniqueId(), chatPlayer -> {
                if (chatPlayer.isIgnoring()){
                    sendMessage(player, Lang.MSGIGNORE_SUCCESS_FULLIGNORE_OFF.get());
                    chatPlayer.setIgnoring(false);
                }else{
                    sendMessage(player, Lang.MSGIGNORE_SUCCESS_FULLIGNORE_ON.get());
                    chatPlayer.setIgnoring(true);
                }
                playerManager.setPlayer(player.getUniqueId(), chatPlayer);
            });
            return;
        }

        else if (args.length == 1){
            // /msgignore <player>
            if (!checkPerm(player, "chatcontroller.msgignore.use.player")) return;
            playerManager.getPlayer(player.getUniqueId(), chatPlayer -> {
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
                List<UUID> ignoreList = chatPlayer.getIgnoreList();
                if (ignoreList.contains(offlinePlayer.getUniqueId())){
                    sendMessage(player, Lang.MSGIGNORE_SUCCESS_UNIGNORE.get().replace("{player}", args[0]));
                    ignoreList.remove(offlinePlayer.getUniqueId());
                }else{
                    sendMessage(player, Lang.MSGIGNORE_SUCCESS_IGNORE.get().replace("{player}", args[0]));
                    ignoreList.add(offlinePlayer.getUniqueId());
                }
                chatPlayer.setIgnoreList(ignoreList);
                playerManager.setPlayer(player.getUniqueId(), chatPlayer);
            });
            return;
        }

        if (checkPerm(player, "chatcontroller.help")){
            sendMessage(player, Lang.HELP.get());
        }
    }
}
