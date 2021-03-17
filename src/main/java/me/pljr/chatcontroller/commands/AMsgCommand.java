package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.config.Lang;
import me.pljr.chatcontroller.managers.PlayerManager;
import me.pljr.chatcontroller.utils.MsgUtils;
import me.pljr.pljrapispigot.commands.BukkitCommand;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AMsgCommand extends BukkitCommand {

    private final PlayerManager playerManager;

    public AMsgCommand(PlayerManager playerManager){
        super("amsg", "chatcontroller.amsg.use");
        this.playerManager = playerManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length >= 2){
            // /amsg <player> <message>
            if (!checkPlayer(player, args[0])) return;
            Player receiver = Bukkit.getPlayer(args[0]);
            playerManager.getPlayer(receiver.getUniqueId(), chatPlayer -> MsgUtils.sendMsg(player, chatPlayer, StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ")));
            return;
        }

        if (checkPerm(player, "achatcontroller.help")){
            sendMessage(player, Lang.ADMIN_HELP.get());
        }
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length >= 2){
            // /amsg <player> <message>
            if (!checkPlayer(sender, args[0])) return;
            Player receiver = Bukkit.getPlayer(args[0]);
            playerManager.getPlayer(receiver.getUniqueId(), chatPlayer -> MsgUtils.sendMsg(sender, chatPlayer, StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ")));
            return;
        }

        sendMessage(sender, Lang.ADMIN_HELP.get());
    }
}
