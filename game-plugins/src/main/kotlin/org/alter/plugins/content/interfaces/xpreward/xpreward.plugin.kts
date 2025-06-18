package org.alter.plugins.content.interfaces.xpreward
import org.alter.game.model.attr.XP_REWARD_ITEM
import org.alter.game.model.attr.XP_REWARD_SKILL
import org.alter.game.model.attr.XP_REWARD_MIN_LEVEL
import org.alter.game.model.attr.XP_REWARD_EXPERIENCE
import org.alter.api.ext.message
import org.alter.api.ext.closeInterface
import org.alter.api.ext.playSound
import org.alter.api.ext.getSkills
import org.alter.api.ext.messageBox
import org.alter.api.cfg.Sound
import org.alter.api.Skills

XpReward.COMPONENT_TO_SKILL.forEach { (component, skill) ->
    on_button(interfaceId = XpReward.INTERFACE_ID, component = component) {
        player.attr[XP_REWARD_SKILL] = skill
        player.playSound(Sound.INTERFACE_SELECT1)
    }
}

on_button(interfaceId = XpReward.INTERFACE_ID, component = 1) {
    player.closeInterface(XpReward.INTERFACE_ID)
}

arrayOf(XpReward.CONFIRM_COMPONENT, XpReward.CONFIRM_BUTTON).forEach { comp ->
    on_button(interfaceId = XpReward.INTERFACE_ID, component = comp) {
        val item = player.attr[XP_REWARD_ITEM] ?: return@on_button
        val skill = player.attr[XP_REWARD_SKILL]
        if (skill == null || skill == -1) {
            player.message("You need to choose which skill you wish to be advanced.")
            return@on_button
        }
        val min = player.attr[XP_REWARD_MIN_LEVEL] ?: 1
        val xp = player.attr[XP_REWARD_EXPERIENCE] ?: 0.0
        if (player.getSkills().getBaseLevel(skill) < min) {
            val name = Skills.getSkillName(player.world, skill)
            player.message("You need a $name level of at least $min to use this.")
            return@on_button
        }
        player.inventory.remove(item)
        player.addXp(skill, xp)
        player.playSound(Sound.FOUND_GEM)
        player.queue {
            messageBox("<col=000080>Your wish has been granted!</col><br><br>You have been awarded ${xp.toInt()} ${Skills.getSkillName(world, skill)} experience!")
        }
        player.closeInterface(XpReward.INTERFACE_ID)
    }
}

on_interface_close(XpReward.INTERFACE_ID) {
    player.attr.remove(XP_REWARD_ITEM)
    player.attr.remove(XP_REWARD_SKILL)
    player.attr.remove(XP_REWARD_MIN_LEVEL)
    player.attr.remove(XP_REWARD_EXPERIENCE)
}
