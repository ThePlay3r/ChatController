package me.pljr.chatcontroller.utils;

import me.pljr.chatcontroller.config.*;
import me.pljr.pljrapispigot.builders.ActionBarBuilder;
import me.pljr.pljrapispigot.builders.SoundBuilder;
import me.pljr.pljrapispigot.builders.TitleBuilder;
import me.pljr.pljrapispigot.utils.ChatUtil;
import org.bukkit.entity.Player;

public class MentionUtil {

    public static void mention(Player mention, String byWho){
        if (CfgSettings.MENTION_MESSAGE) ChatUtil.sendMessage(mention, Lang.MENTION_MESSAGE.get().replace("{player}", byWho));
        if (CfgSettings.MENTION_TITLE) new TitleBuilder(TitleType.MENTION_TITLE.get())
                .replaceTitle("{player}", byWho)
                .replaceSubtitle("{player}", byWho)
                .create().send(mention);
        if (CfgSettings.MENTION_ACTIONBAR) new ActionBarBuilder(ActionBarType.MENTION_ACTIONBAR.get())
                .replaceMessage("{player}", byWho)
                .create().send(mention);
        if (CfgSettings.SOUNDS) SoundType.PRIVATE_MESSAGE.get().play(mention);
    }
}
