package me.pljr.chatcontroller.commands;

import me.pljr.chatcontroller.config.Settings;
import me.pljr.chatcontroller.config.Lang;
import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.pljrapispigot.utils.ChatUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AChatCommand extends BukkitCommand {
    private static final String PERM = "chatcontroller.adminchat.see";

    private final Settings settings;

    public AChatCommand(Settings settings){
        super("achat", "chatcontroller.adminchat.use");
        this.settings = settings;
    }

    private void execute(String name, String[] args){
        String message = StringUtils.join(ArrayUtils.subarray(args, 0, args.length), " ");
        ChatUtil.broadcast(Lang.ADMIN_CHAT_FORMAT.get().replace("{player}", name).replace("{message}", message), PERM, settings.isBungeeAChat());
    }


    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length > 0){
            execute(player.getName(), args);
            return;
        }

        if (checkPerm(player, "achatcontroller.help")){
            sendMessage(player, Lang.ADMIN_HELP.get());
        }
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length > 0){
            execute("Console", args);
            return;
        }

        sendMessage(sender, Lang.ADMIN_HELP.get());
    }
}
