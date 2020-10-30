package me.pljr.chatcontroller.utils;

import me.pljr.chatcontroller.config.CfgMention;
import me.pljr.chatcontroller.config.CfgSettings;
import me.pljr.chatcontroller.config.CfgSounds;
import me.pljr.chatcontroller.enums.SoundType;
import me.pljr.pljrapi.managers.ActionBarManager;
import me.pljr.pljrapi.managers.TitleManager;
import me.pljr.pljrapi.objects.PLJRActionBar;
import me.pljr.pljrapi.objects.PLJRTitle;
import me.pljr.pljrapi.utils.ChatUtil;
import org.bukkit.entity.Player;

public class MentionUtil {

    public static void mention(Player mention, String byWho){
        if (CfgMention.messageEnabled){
            for (String line : CfgMention.message){
                ChatUtil.sendMessage(mention, line.replace("%player", byWho));
            }
        }
        if (CfgMention.titleEnabled) TitleManager.send(mention, new PLJRTitle(
                CfgMention.title.getTitle().replace("%player", byWho),
                CfgMention.title.getSubtitle().replace("%player", byWho),
                CfgMention.title.getIn(),
                CfgMention.title.getStay(),
                CfgMention.title.getOut()));
        if (CfgMention.actionbarEnabled) ActionBarManager.send(mention, new PLJRActionBar(CfgMention.actionBar.getMessage().replace("%player", byWho), CfgMention.actionBar.getDuration()));
        if (CfgSettings.sounds) mention.playSound(mention.getLocation(), CfgSounds.sounds.get(SoundType.PRIVATE_MESSAGE), 1, 1);
    }
}
