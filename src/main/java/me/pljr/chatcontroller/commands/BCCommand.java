package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.config.Settings;
import me.pljr.chatcontroller.config.Lang;
import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.pljrapispigot.utils.ChatUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BCCommand extends BukkitCommand {

    private final Settings settings;

    public BCCommand(Settings settings){
        super("bc", "chatcontroller.bc.use");
        this.settings = settings;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length > 0){
            ChatUtil.broadcast(StringUtils.join(ArrayUtils.subarray(args, 0, args.length), " "), "", settings.isBungee());
            return;
        }

        if (checkPerm(player, "achatcontroller.help")){
            sendMessage(player, Lang.ADMIN_HELP.get());
        }
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length > 0){
            ChatUtil.broadcast(StringUtils.join(ArrayUtils.subarray(args, 0, args.length), " "), "", settings.isBungee());
            return;
        }

        sendMessage(sender, Lang.ADMIN_HELP.get());
    }
}
