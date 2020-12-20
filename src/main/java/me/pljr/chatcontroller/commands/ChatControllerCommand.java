package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.ChatController;
import me.pljr.chatcontroller.config.CfgLang;
import me.pljr.pljrapi.utils.CommandUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatControllerCommand extends CommandUtil {

    public ChatControllerCommand(){
        super("chatcontroller", "chatcontroller.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 1){
            // /chatcontroller help
            if (args[0].equalsIgnoreCase("help")){
                if (!checkPerm(player, "chatcontroller.help"))
                    sendHelp(player, CfgLang.help);
                return;
            }

            // /chatcontroller reload
            if (args[0].equalsIgnoreCase("reload")){
                if (!checkPerm(player, "chatcontroller.reload")) return;
                ChatController.getInstance().setupConfig();
                player.sendMessage("§a§l✔");
                return;
            }
        }
    }

    @Override
    public void onConsoleCommand(CommandSender sender, String[] args){
        if (args.length == 1){
            // /chatcontroller help
            if (args[0].equalsIgnoreCase("help")){
                sendHelp(sender, CfgLang.help);
                return;
            }

            // /chatcontroller reload
            if (args[0].equalsIgnoreCase("reload")){
                ChatController.getInstance().setupConfig();
                sender.sendMessage("§a§l✔");
                return;
            }
        }
    }
}
