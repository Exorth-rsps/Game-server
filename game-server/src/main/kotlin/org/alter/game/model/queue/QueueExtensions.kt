package org.alter.game.model.queue

import org.alter.game.model.entity.Player

/**
 * Public helper to submit a return value to a [Player]'s queue.
 */
fun Player.submitReturnValue(value: Any) {
    this.queues.submitReturnValue(value)
}

