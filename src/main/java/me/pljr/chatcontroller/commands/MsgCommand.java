package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.ChatController;
import me.pljr.chatcontroller.config.Lang;
import me.pljr.chatcontroller.managers.PlayerManager;
import me.pljr.chatcontroller.objects.ChatPlayer;
import me.pljr.chatcontroller.utils.MsgUtils;
import me.pljr.pljrapispigot.commands.BukkitCommand;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class MsgCommand extends BukkitCommand {

    private final PlayerManager playerManager;

    public MsgCommand(PlayerManager playerManager){
        super("msg", "chatcontroller.msg.use");
        this.playerManager = playerManager;
    }

    public void onPlayerCommand(Player player, String[] args){
        if (args.length >= 2){
            // /msg <player> <message>
            if (!checkPlayer(player, args[0])) return;
            Player receiver = Bukkit.getPlayer(args[0]);
            playerManager.getPlayer(receiver.getUniqueId(), chatPlayer -> {
                if (chatPlayer.isIgnoring()){
                    sendMessage(player, Lang.MSG_FAILURE_IGNORING.get().replace("{player}", args[0]));
                    return;
                }
                if (chatPlayer.getIgnoreList().contains(player.getUniqueId())){
                    sendMessage(player, Lang.MSG_FAILURE_BLOCKED.get().replace("{player}", args[0]));
                    return;
                }
                MsgUtils.sendMsg(player, chatPlayer, StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " "));
            });
            return;
        }

        if (checkPerm(player, "chatcontroller.help")){
            sendMessage(player, Lang.HELP.get());
        }
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length >= 2){
            // /msg <player> <message>
            if (!checkPlayer(sender, args[0])) return;
            Player receiver = Bukkit.getPlayer(args[0]);
            playerManager.getPlayer(receiver.getUniqueId(), chatPlayer -> {
                if (chatPlayer.isIgnoring()){
                    sendMessage(sender, Lang.MSG_FAILURE_IGNORING.get().replace("{player}", args[0]));
                    return;
                }
                MsgUtils.sendMsg(sender, chatPlayer, StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " "));
            });
            return;
        }

        sendMessage(sender, Lang.HELP.get());
    }
}
