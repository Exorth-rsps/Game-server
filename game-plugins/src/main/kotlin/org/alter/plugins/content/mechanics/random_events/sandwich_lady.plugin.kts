package org.alter.plugins.content.mechanics.random_events

import org.alter.api.cfg.Items
import org.alter.api.cfg.Npcs
import org.alter.game.model.entity.Npc
import org.alter.game.model.queue.QueueTask
import org.alter.plugins.content.interfaces.sandwich.SandwichTray
import org.alter.game.fs.def.ItemDef
import org.alter.plugins.content.mechanics.random_events.CALL_EVENT_TIMER

private val SANDWICH_REWARDS = intArrayOf(
    Items.SANDWICH_LADY_HAT,
    Items.SANDWICH_LADY_TOP,
    Items.SANDWICH_LADY_BOTTOM,
    Items.STALE_BAGUETTE
)

on_npc_option(npc = Npcs.SANDWICH_LADY, option = "talk-to") {
    val eventNpc = player.getInteractingNpc()
    if (eventNpc.owner != player) {
        player.message("The Sandwich Lady is busy with someone else.")
        return@on_npc_option
    }
    eventNpc.timers.remove(IGNORE_EVENT_TIMER)
    eventNpc.timers.remove(CALL_EVENT_TIMER)
    player.queue { sandwichDialog(eventNpc) }
}

suspend fun QueueTask.sandwichDialog(eventNpc: Npc) {
    val request = SandwichTray.items.random()
    val name = world.definitions.get(ItemDef::class.java, request).name.lowercase()
    chatNpc("Please choose the $name from my tray.", npc = eventNpc.id)
    val correct = SandwichTray.chooseItem(this, request)
    if (correct) {
        val reward = SANDWICH_REWARDS.random()
        player.inventory.add(reward)
        chatNpc("Enjoy your treat.", npc = eventNpc.id)
    } else {
        chatNpc("That's not what I asked for!", npc = eventNpc.id)
    }
    world.remove(eventNpc)
}
