package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.ChatController;
import me.pljr.chatcontroller.config.CfgLang;
import me.pljr.chatcontroller.enums.Lang;
import me.pljr.chatcontroller.objects.CorePlayer;
import me.pljr.pljrapi.utils.CommandUtil;
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
                sendHelp(player, CfgLang.adminHelp);
                return;
            }

            // /achatcontroller spy
            if (args[0].equalsIgnoreCase("spy")){
                if (!checkPerm(player, "achatcontroller.spy")) return;
                CorePlayer corePlayer = ChatController.getPlayerManager().getCorePlayer(player.getUniqueId());
                if (corePlayer.isSpy()){
                    sendMessage(player, CfgLang.lang.get(Lang.ACHATCONTROLLER_SPY_OFF));
                    corePlayer.setSpy(false);
                }else{
                    sendMessage(player, CfgLang.lang.get(Lang.ACHATCONTROLLER_SPY_ON));
                    corePlayer.setSpy(true);
                }
                ChatController.getPlayerManager().setCorePlayer(player.getUniqueId(), corePlayer);
                return;
            }
        }

        if (checkPerm(player, "achatcontroller.help")){
            sendHelp(player, CfgLang.adminHelp);
        }
        return;
    }
}
