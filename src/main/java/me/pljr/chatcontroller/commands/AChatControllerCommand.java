package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.ChatController;
import me.pljr.chatcontroller.config.Lang;
import me.pljr.chatcontroller.objects.CorePlayer;
import me.pljr.pljrapispigot.utils.CommandUtil;
import org.bukkit.entity.Player;

public class AChatControllerCommand extends CommandUtil {

    public AChatControllerCommand(){
        super("achatcontroller", "achatcontroller.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 1){
            // /achatcontroller help
            if (args[0].equalsIgnoreCase("help")){
                if (!checkPerm(player, "achatcontroller.help")) return;
                sendMessage(player, Lang.ADMIN_HELP);
                return;
            }

            // /achatcontroller spy
            if (args[0].equalsIgnoreCase("spy")){
                if (!checkPerm(player, "achatcontroller.spy")) return;
                CorePlayer corePlayer = ChatController.getPlayerManager().getCorePlayer(player.getUniqueId());
                if (corePlayer.isSpy()){
                    sendMessage(player, Lang.ACHATCONTROLLER_SPY_OFF.get());
                    corePlayer.setSpy(false);
                }else{
                    sendMessage(player, Lang.ACHATCONTROLLER_SPY_ON.get());
                    corePlayer.setSpy(true);
                }
                ChatController.getPlayerManager().setCorePlayer(player.getUniqueId(), corePlayer);
                return;
            }
        }

        if (checkPerm(player, "achatcontroller.help")){
            sendMessage(player, Lang.ADMIN_HELP);
        }
        return;
    }
}
