package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.ChatController;
import me.pljr.chatcontroller.config.Lang;
import me.pljr.chatcontroller.managers.PlayerManager;
import me.pljr.chatcontroller.objects.ChatPlayer;
import me.pljr.pljrapispigot.commands.BukkitCommand;
import org.bukkit.entity.Player;

public class AChatControllerCommand extends BukkitCommand {

    private final PlayerManager playerManager;

    public AChatControllerCommand(PlayerManager playerManager){
        super("achatcontroller", "achatcontroller.use");
        this.playerManager = playerManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 1){
            // /achatcontroller help
            if (args[0].equalsIgnoreCase("help")){
                if (!checkPerm(player, "achatcontroller.help")) return;
                sendMessage(player, Lang.ADMIN_HELP.get());
                return;
            }

            // /achatcontroller spy
            if (args[0].equalsIgnoreCase("spy")){
                if (!checkPerm(player, "achatcontroller.spy")) return;
                playerManager.getPlayer(player.getUniqueId(), chatPlayer -> {
                    if (chatPlayer.isSpy()){
                        sendMessage(player, Lang.ACHATCONTROLLER_SPY_OFF.get());
                        chatPlayer.setSpy(false);
                    }else{
                        sendMessage(player, Lang.ACHATCONTROLLER_SPY_ON.get());
                        chatPlayer.setSpy(true);
                    }
                    playerManager.setPlayer(player.getUniqueId(), chatPlayer);
                });
                return;
            }
        }

        if (checkPerm(player, "achatcontroller.help")){
            sendMessage(player, Lang.ADMIN_HELP.get());
        }
    }
}
