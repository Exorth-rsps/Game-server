package org.alter.plugins.content.area.legacy.falador.chat

import org.alter.api.cfg.Npcs
import org.alter.api.ext.selectAppearance
import org.alter.game.model.queue.QueueTask

private val MAKEOVER_MAGE_NPCS = arrayOf(
    Npcs.MAKEOVER_MAGE,
    Npcs.MAKEOVER_MAGE_1307,
    Npcs.MAKEOVER_MAGE_8487
)

MAKEOVER_MAGE_NPCS.forEach { npc ->
    on_npc_option(npc = npc, option = "talk-to", lineOfSightDistance = 1) {
        player.queue { makeoverDialogue() }
    }
}

suspend fun QueueTask.makeoverDialogue() {
    chatNpc("Hello there. I'm the Makeover Mage!", "Would you like to change your appearance?")
    when (options("Yes, please.", "No, thanks.")) {
        FIRST_OPTION -> startMakeover()
        SECOND_OPTION -> chatPlayer("No, thanks.", animation = 567)
    }
}

suspend fun QueueTask.startMakeover() {
    chatPlayer("Yes, please.", animation = 567)
    chatNpc("Splendid! Step this way and we'll see what we can do.")
    selectAppearance()
}
