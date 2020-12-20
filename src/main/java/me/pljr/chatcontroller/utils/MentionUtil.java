package me.pljr.chatcontroller.utils;

import me.pljr.chatcontroller.config.CfgMention;
import me.pljr.chatcontroller.config.CfgSettings;
import me.pljr.chatcontroller.config.CfgSounds;
import me.pljr.chatcontroller.enums.SoundType;
import me.pljr.pljrapi.builders.ActionBarBuilder;
import me.pljr.pljrapi.builders.SoundBuilder;
import me.pljr.pljrapi.builders.TitleBuilder;
import me.pljr.pljrapi.utils.ChatUtil;
import org.bukkit.entity.Player;

public class MentionUtil {

    public static void mention(Player mention, String byWho){
        if (CfgMention.messageEnabled){
            for (String line : CfgMention.message){
                ChatUtil.sendMessage(mention, line.replace("{player}", byWho));
            }
        }
        if (CfgMention.titleEnabled) new TitleBuilder(CfgMention.title)
                .replaceTitle("{player}", byWho)
                .replaceSubtitle("{player}", byWho)
                .create().send(mention);
        if (CfgMention.actionbarEnabled) new ActionBarBuilder(CfgMention.actionBar)
                .replaceMessage("{player}", byWho)
                .create().send(mention);
        if (CfgSettings.sounds) new SoundBuilder(CfgSounds.sounds.get(SoundType.PRIVATE_MESSAGE))
                .create().play(mention);
    }
}
