package org.alter.plugins.content.mechanics.run

import org.alter.game.model.priv.Privilege

on_login {
    player.timers[RunEnergy.RUN_DRAIN] = 1
}

on_timer(RunEnergy.RUN_DRAIN) {
    player.timers[RunEnergy.RUN_DRAIN] = 1
    //RunEnergy.drain(player)
}

/**
 * Button by minimap.
 */
on_button(interfaceId = 160, component = 27) {
    RunEnergy.toggle(player)
}

/**
 * Settings button.
 */
on_button(interfaceId = 116, component = 71) {
    RunEnergy.toggle(player)
}
