package org.alter.plugins.content.mechanics.random_events

import org.alter.api.cfg.Npcs
import org.alter.api.ext.message
import org.alter.game.fs.def.NpcDef
import org.alter.game.model.attr.AttributeKey
import org.alter.game.model.entity.Npc
import org.alter.game.model.entity.Player
import org.alter.game.model.timer.TimerKey

/**
 * Utilities for random events.
 */
val IGNORE_EVENT_TIMER = TimerKey()
val CALL_EVENT_TIMER = TimerKey()
val CALL_INDEX_ATTR = AttributeKey<Int>()
val FOLLOW_EVENT_TIMER = TimerKey()

const val FOLLOW_DELAY = 1

const val CALL_DELAY = 20

val CALL_MESSAGES = arrayOf<(Player) -> String>(
    { p -> "Hello ${p.username}, can we speak?" },
    { p -> "Hello ${p.username}, are you there?" },
    { p -> "Hello ${p.username}!" },
    { p -> "Hello ${p.username}, talk to me now!" },
    { p -> "${p.username}, Last chance!" }

)

private const val IGNORE_DELAY = 100

private val EVENTS = intArrayOf(
    Npcs.GENIE,
    Npcs.SANDWICH_LADY
)

fun spawnRandomEvent(player: Player, npcId: Int = EVENTS.random()) {
    val world = player.world
    val tile = player.tile.transform(1, 0)
    val npc = Npc(npcId, tile, world)
    npc.owner = player
    npc.respawns = false
    npc.timers[IGNORE_EVENT_TIMER] = IGNORE_DELAY
    npc.attr[CALL_INDEX_ATTR] = 1
    world.spawn(npc)
    npc.forceChat(CALL_MESSAGES[0](player))
    npc.timers[CALL_EVENT_TIMER] = CALL_DELAY
    npc.timers[FOLLOW_EVENT_TIMER] = FOLLOW_DELAY
    val name = world.definitions.get(NpcDef::class.java, npcId).name
    player.message("A random event has appeared: $name. Talk to them or you'll be sent home!")
}
