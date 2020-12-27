package me.pljr.chatcontroller.utils;

import me.pljr.chatcontroller.config.CfgMention;
import me.pljr.chatcontroller.config.CfgSettings;
import me.pljr.chatcontroller.config.CfgSounds;
import me.pljr.chatcontroller.config.SoundType;
import me.pljr.pljrapispigot.builders.ActionBarBuilder;
import me.pljr.pljrapispigot.builders.SoundBuilder;
import me.pljr.pljrapispigot.builders.TitleBuilder;
import me.pljr.pljrapispigot.utils.ChatUtil;
import org.bukkit.entity.Player;

public class MentionUtil {

    public static void mention(Player mention, String byWho){
        if (CfgMention.MESSAGE_ENABLED){
            for (String line : CfgMention.MESSAGE){
                ChatUtil.sendMessage(mention, line.replace("{player}", byWho));
            }
        }
        if (CfgMention.TITLE_ENABLED) new TitleBuilder(CfgMention.TITLE)
                .replaceTitle("{player}", byWho)
                .replaceSubtitle("{player}", byWho)
                .create().send(mention);
        if (CfgMention.ACTIONBAR_ENABLED) new ActionBarBuilder(CfgMention.ACTIONBAR)
                .replaceMessage("{player}", byWho)
                .create().send(mention);
        if (CfgSettings.SOUNDS) new SoundBuilder(SoundType.PRIVATE_MESSAGE.get())
                .create().play(mention);
    }
}
