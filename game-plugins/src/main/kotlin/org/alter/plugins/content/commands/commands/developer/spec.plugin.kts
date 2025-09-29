package org.alter.plugins.content.commands.commands.developer

import org.alter.game.model.priv.Privilege
import org.alter.plugins.content.interfaces.attack.AttackTab

on_command("spec", Privilege.DEV_POWER, description = "Restore special attack") {
    AttackTab.setEnergy(player, 100)
    player.message("Your special attack has been restored.")
}
