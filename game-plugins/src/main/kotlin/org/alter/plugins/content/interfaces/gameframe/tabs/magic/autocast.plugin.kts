package org.alter.plugins.content.interfaces.autocast

import org.alter.api.InterfaceDestination
import org.alter.plugins.content.combat.Combat
import org.alter.plugins.content.combat.autocast.Autocast
import org.alter.plugins.content.combat.autocast.AutocastSpells
import org.alter.plugins.content.combat.strategy.magic.CombatSpell
import org.alter.plugins.content.interfaces.attack.AttackTab.ATTACK_TAB_INTERFACE_ID
import org.alter.game.model.item.Item

const val AUTOCAST_INTERFACE_ID = 201
const val SPELL_COMPONENT = 1
const val DEFENSIVE_COMPONENT = 3
const val CLOSE_COMPONENT = 5

on_button(interfaceId = ATTACK_TAB_INTERFACE_ID, component = 26) {
    // Ensure combat spells are visible by disabling spell filters
    player.setVarbit(MagicVarbits.SPELLBOOK_FILTERING, 1)
    player.setVarbit(MagicVarbits.SPELLBOOK_SHOW_COMBAT_SPELLS, 1)
    // Populate the interface with available combat spell icons
    val spells = CombatSpell.values.sortedBy { it.autoCastId }
    val items = Array<Item?>(50) { index ->
        val spell = spells.getOrNull(index)
        spell?.let { Item(it.id) }
    }
    player.sendItemContainer(interfaceId = AUTOCAST_INTERFACE_ID, component = SPELL_COMPONENT, items = items)
    player.openInterface(interfaceId = AUTOCAST_INTERFACE_ID, dest = InterfaceDestination.TAB_AREA)
    player.setInterfaceEvents(interfaceId = AUTOCAST_INTERFACE_ID, component = SPELL_COMPONENT, range = 0..50, setting = 2)
}

on_button(interfaceId = ATTACK_TAB_INTERFACE_ID, component = 21) {
    player.toggleVarbit(Combat.DEFENSIVE_MAGIC_CAST_VARBIT)
}

on_button(interfaceId = AUTOCAST_INTERFACE_ID, component = SPELL_COMPONENT) {
    val slot = player.getInteractingSlot()
    val spell = AutocastSpells.forId(slot + 1) ?: return@on_button
    if (Autocast.canAutocast(player, spell)) {
        player.attr[Combat.CASTING_SPELL] = spell
        player.setVarbit(Combat.SELECTED_AUTOCAST_VARBIT, spell.autoCastId)
        player.closeInterface(AUTOCAST_INTERFACE_ID)
    } else {
        player.message("You can't autocast that spell with this staff.")
        Autocast.reset(player)
    }
}

on_button(interfaceId = AUTOCAST_INTERFACE_ID, component = DEFENSIVE_COMPONENT) {
    player.toggleVarbit(Combat.DEFENSIVE_MAGIC_CAST_VARBIT)
}

on_button(interfaceId = AUTOCAST_INTERFACE_ID, component = CLOSE_COMPONENT) {
    player.closeInterface(AUTOCAST_INTERFACE_ID)
}

