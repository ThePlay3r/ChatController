package me.pljr.chatcontroller.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.objects.PLJRActionBar;
import me.pljr.pljrapispigot.objects.PLJRTitle;
import me.pljr.pljrapispigot.utils.FormatUtil;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;

public enum Lang {
    PRIVATE_MESSAGE_SENDER("" +
            "\n&7✉ &b{sender} &8&l> &b{receiver}" +
            "\n" +
            "\n&8&l■ &f{message}" +
            "\n"),

    PRIVATE_MESSAGE_RECEIVER("" +
            "\n&7✉ &b{sender} &8&l> &b{receiver}" +
            "\n" +
            "\n&8&l■ &f{message}" +
            "\n"),

    HELP("" +
            "\n&a&lChatController Help" +
            "\n" +
            "\n&e/chatcontroller help &8» &fDisplays this message." +
            "\n" +
            "\n" +
            "\n" +
            "\n"),

    ADMIN_HELP("" +
            "\n&a&lChatController Admin-Help" +
            "\n" +
            "\n&e/achatcontroller help &8» &fDisplays this message." +
            "\n&e/achatcontroller spy &8» &fToggles spying on private messages." +
            "\n&e/achat <message> &8» &fSends message to admin chat." +
            "\n&e/amsg <player> <message> &8» &fSends private message to player, ignoring his ignore list." +
            "\n&e/bc <message> &8» &fBroadcasts message." +
            "\n"),

    ADMIN_CHAT_FORMAT("&4&lAT &c{player} &4> &c<pre>{message}</pre>"),
    ADMIN_SPY("&cMSG: &8{sender} : {receiver} > &7{message}"),
    MSG_FAILURE_IGNORING("&aChatController &8» &b{player} &fis not receiving any private messages."),
    MSG_FAILURE_BLOCKED("&aChatController &8» &b{player} &fhas blocked you."),
    MSGIGNORE_SUCCESS_FULLIGNORE_ON("&aChatController &8» &fYou are now ignoring everyone."),
    MSGIGNORE_SUCCESS_FULLIGNORE_OFF("&aChatController &8» &fYou are no longed ignoring everyone."),
    MSGIGNORE_SUCCESS_IGNORE("&aChatController &8» &fYou are now ignoring messages from &b{player}&f."),
    MSGIGNORE_SUCCESS_UNIGNORE("&aChatController &8» &fYou are no longer ignoring messages from &b{player}&f."),
    ACHATCONTROLLER_SPY_ON("&aChatController &8» &fYou can now see private messages."),
    ACHATCONTROLLER_SPY_OFF("&aChatController &8» &fYou no longer can see private messages."),
    FIRST_JOIN_MESSAGE("&a{player} &fhas joined for the &bfirst time&f!"),
    JOIN_MESSAGE_PLAYER("&8&l> &fHey &b{player}&f, welcome to server!"),
    JOIN_MESSAGE("&a&l● &a&o{player}"),
    QUIT_MESSAGE( "&c&l● &c&o{player}"),
    MENTION_MESSAGE("&e{player} &amentioned you!");

    private static HashMap<Lang, String> lang;
    private final String defaultValue;

    Lang(String defaultValue){
        this.defaultValue = defaultValue;
    }

    public static void load(ConfigManager config){
        lang = new HashMap<>();
        FileConfiguration fileConfig = config.getConfig();
        for (Lang lang : values()){
            if (!fileConfig.isSet(lang.toString())){
                fileConfig.set(lang.toString(), lang.defaultValue);
            }else{
                Lang.lang.put(lang, config.getString(lang.toString()));
            }
        }
        config.save();
    }

    public String get(){
        return lang.getOrDefault(this, FormatUtil.colorString(defaultValue));
    }
}
