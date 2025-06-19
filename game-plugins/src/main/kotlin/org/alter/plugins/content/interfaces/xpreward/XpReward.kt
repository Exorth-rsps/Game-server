package org.alter.plugins.content.interfaces.xpreward

import org.alter.game.message.impl.ResumePauseButtonMessage
import org.alter.game.model.entity.Player
import org.alter.api.InterfaceDestination
import org.alter.api.Skills
import org.alter.api.ext.*
import org.alter.game.model.queue.TaskPriority

object XpReward {
    private const val INTERFACE_ID = 240
    private const val VARP_ID = 261

    fun open(player: Player, item: Int? = null, xpMultiplier: Int = 10) {
        player.setInterfaceUnderlay(-1, -1)
        player.openInterface(INTERFACE_ID, InterfaceDestination.MAIN_SCREEN)
        player.setComponentText(INTERFACE_ID, 25, "Choose the stat you wish to be advanced!")
        player.setVarp(VARP_ID, 0)

        player.queue(TaskPriority.STRONG) {
            waitReturnValue()
            val msg = requestReturnValue as? ResumePauseButtonMessage ?: return@queue

            // Haal de “slot” uit het bericht (in plaats van onbestaande 'sub')
            val selectedSlot = msg.slot

            // Zoek de lamp op via values() i.p.v. entries
            val lamp = SkillLamp.values().find { it.slot == selectedSlot } ?: return@queue

            val baseLevel = player.getSkills().getBaseLevel(lamp.skill)
            val reward = xpMultiplier * baseLevel

            player.addXp(lamp.skill, reward.toDouble())
            if (item != null) {
                player.inventory.remove(item)
            }
            val skillName = Skills.getSkillName(player.world, lamp.skill)
            player.message("You have been rewarded $reward experience in $skillName.")
            player.closeInterface(INTERFACE_ID)
        }
    }

    private enum class SkillLamp(val slot: Int, val skill: Int) {
        ATTACK(0, Skills.ATTACK),
        STRENGTH(1, Skills.STRENGTH),
        RANGED(2, Skills.RANGED),
        MAGIC(3, Skills.MAGIC),
        DEFENCE(4, Skills.DEFENCE),
        HITPOINTS(5, Skills.HITPOINTS),
        PRAYER(6, Skills.PRAYER),
        AGILITY(7, Skills.AGILITY),
        HERBLORE(8, Skills.HERBLORE),
        THIEVING(9, Skills.THIEVING),
        CRAFTING(10, Skills.CRAFTING),
        RUNECRAFTING(11, Skills.RUNECRAFTING),
        MINING(12, Skills.MINING),
        SMITHING(13, Skills.SMITHING),
        FISHING(14, Skills.FISHING),
        COOKING(15, Skills.COOKING),
        FIREMAKING(16, Skills.FIREMAKING),
        WOODCUTTING(17, Skills.WOODCUTTING),
        FLETCHING(18, Skills.FLETCHING),
        SLAYER(19, Skills.SLAYER),
        FARMING(20, Skills.FARMING),
        CONSTRUCTION(21, Skills.CONSTRUCTION),
        HUNTER(22, Skills.HUNTER)
    }
}
