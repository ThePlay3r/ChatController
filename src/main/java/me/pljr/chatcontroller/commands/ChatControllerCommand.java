package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.ChatController;
import me.pljr.chatcontroller.config.Lang;
import me.pljr.pljrapispigot.utils.CommandUtil;
import org.bukkit.command.ConsoleCommandSender;
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
                    sendMessage(player, Lang.HELP);
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
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length == 1){
            // /chatcontroller help
            if (args[0].equalsIgnoreCase("help")){
                sendMessage(sender, Lang.HELP);
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
