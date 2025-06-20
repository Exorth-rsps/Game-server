package org.alter.plugins.content.mechanics.random_events

import org.alter.game.model.timer.TimerKey
import org.alter.api.cfg.Npcs
import org.alter.plugins.content.combat.isAttacking
import org.alter.plugins.content.combat.isBeingAttacked
import org.alter.plugins.content.mechanics.random_events.IGNORE_EVENT_TIMER
import org.alter.plugins.content.mechanics.random_events.CALL_EVENT_TIMER
import org.alter.plugins.content.mechanics.random_events.CALL_INDEX_ATTR
import org.alter.plugins.content.mechanics.random_events.CALL_MESSAGES
import org.alter.plugins.content.mechanics.random_events.CALL_DELAY
import org.alter.plugins.content.mechanics.random_events.spawnRandomEvent

private val RANDOM_EVENT_TIMER = TimerKey()

private val MIN_DELAY = 3000
private val MAX_DELAY = 6000

on_login {
    player.timers[RANDOM_EVENT_TIMER] = world.random(MIN_DELAY..MAX_DELAY)
}

on_logout {
    player.timers.remove(RANDOM_EVENT_TIMER)
}

on_timer(RANDOM_EVENT_TIMER) {
    if (player.isAttacking() || player.isBeingAttacked()) {
        player.timers[RANDOM_EVENT_TIMER] = world.random(MIN_DELAY..MAX_DELAY)
        return@on_timer
    }
    spawnRandomEvent(player)
    player.timers[RANDOM_EVENT_TIMER] = world.random(MIN_DELAY..MAX_DELAY)
}

on_timer(CALL_EVENT_TIMER) {
    val owner = npc.owner
    if (npc.isSpawned() && owner != null && owner.isOnline) {
        val index = npc.attr[CALL_INDEX_ATTR] ?: 0
        if (index < CALL_MESSAGES.size) {
            val msg = CALL_MESSAGES[index](owner)
            npc.forceChat(msg)
            npc.attr[CALL_INDEX_ATTR] = index + 1
            if (index + 1 < CALL_MESSAGES.size) {
                npc.timers[CALL_EVENT_TIMER] = CALL_DELAY
            }
        }
    }
}

on_timer(IGNORE_EVENT_TIMER) {
    val owner = npc.owner
    if (npc.isSpawned() && owner != null && owner.isOnline) {
        owner.message("Well ${owner.username}, im done, i send you home!")
        owner.moveTo(world.gameContext.home)
    }
    world.remove(npc)
}

