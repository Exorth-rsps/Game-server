package org.alter.plugins.content.interfaces.autocast

import org.alter.api.InterfaceDestination
import org.alter.plugins.content.combat.Combat
import org.alter.plugins.content.combat.autocast.Autocast
import org.alter.plugins.content.combat.autocast.AutocastSpells
import org.alter.plugins.content.interfaces.attack.AttackTab.ATTACK_TAB_INTERFACE_ID

const val AUTOCAST_INTERFACE_ID = 201
const val SPELLBOOK_COMPONENT = 1
const val MAGIC_INTERFACE_ID = 218
const val DEFENSIVE_COMPONENT = 3
const val CANCEL_COMPONENT = 4
const val CLOSE_COMPONENT = 5

on_login {
    player.setVarbit(MagicVarbits.SPELLBOOK_FILTERING, 1)
    player.setVarbit(MagicVarbits.SPELLBOOK_SHOW_COMBAT_SPELLS, 1)
}

on_button(interfaceId = ATTACK_TAB_INTERFACE_ID, component = 26) {
    // Ensure combat spells are visible by disabling spell filters
    player.setVarbit(MagicVarbits.SPELLBOOK_FILTERING, 1)
    player.setVarbit(MagicVarbits.SPELLBOOK_SHOW_COMBAT_SPELLS, 1)
    // Open the autocast interface and embed the magic spellbook inside component 1
    player.openInterface(interfaceId = AUTOCAST_INTERFACE_ID, dest = InterfaceDestination.TAB_AREA)
    player.openInterface(parent = AUTOCAST_INTERFACE_ID, child = SPELLBOOK_COMPONENT, interfaceId = MAGIC_INTERFACE_ID)
    player.setInterfaceEvents(interfaceId = AUTOCAST_INTERFACE_ID, component = SPELLBOOK_COMPONENT, range = 0..51, setting = 2)
}

on_button(interfaceId = ATTACK_TAB_INTERFACE_ID, component = 21) {
    player.toggleVarbit(Combat.DEFENSIVE_MAGIC_CAST_VARBIT)
}

on_button(interfaceId = AUTOCAST_INTERFACE_ID, component = DEFENSIVE_COMPONENT) {
    player.toggleVarbit(Combat.DEFENSIVE_MAGIC_CAST_VARBIT)
}

on_button(interfaceId = AUTOCAST_INTERFACE_ID, component = CLOSE_COMPONENT) {
    player.closeInterface(AUTOCAST_INTERFACE_ID)
}

on_button(interfaceId = AUTOCAST_INTERFACE_ID, component = CANCEL_COMPONENT) {
    Autocast.reset(player)
    player.closeInterface(AUTOCAST_INTERFACE_ID)
}

on_button(interfaceId = AUTOCAST_INTERFACE_ID, component = SPELLBOOK_COMPONENT) {
    val slot = player.getInteractingSlot()
    val spell = AutocastSpells.forId(slot) ?: return@on_button
    if (Autocast.canAutocast(player, spell)) {
        player.attr[Combat.CASTING_SPELL] = spell
        player.setVarbit(Combat.SELECTED_AUTOCAST_VARBIT, spell.autoCastId)
        player.closeInterface(AUTOCAST_INTERFACE_ID)
    } else {
        player.message("You can't autocast that spell with this staff.")
        Autocast.reset(player)
    }
}

