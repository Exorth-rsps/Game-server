package org.alter.plugins.content.combat.autocast

import org.alter.api.EquipmentType
import org.alter.api.ext.getVarbit
import org.alter.api.ext.hasEquipped
import org.alter.api.ext.setVarbit
import org.alter.game.model.entity.Player
import org.alter.plugins.content.combat.Combat
import org.alter.plugins.content.combat.strategy.magic.CombatSpell

/**
 * Utility for validating and managing autocast spell selections.
 *
 * The [restrictedSpells] map should contain any spells that can only be
 * autocast with specific staves. The array associated with each spell
 * represents the item ids of the staves that can autocast it.
 */
object Autocast {

    private val restrictedSpells: Map<CombatSpell, IntArray> = mapOf(
        // TODO: Populate when restricted spells such as Iban blast or god spells are added.
    )

    /** Returns true if the player's equipped weapon can autocast [spell]. */
    fun canAutocast(player: Player, spell: CombatSpell): Boolean {
        val allowed = restrictedSpells[spell] ?: return true
        return player.hasEquipped(EquipmentType.WEAPON, *allowed)
    }

    /** Clears any saved autocast state for [player]. */
    fun reset(player: Player) {
        player.attr.remove(Combat.CASTING_SPELL)
        player.setVarbit(Combat.SELECTED_AUTOCAST_VARBIT, 0)
        player.setVarbit(Combat.DEFENSIVE_MAGIC_CAST_VARBIT, 0)
    }

    /** Ensures the currently selected autocast spell is valid for the equipped weapon. */
    fun validate(player: Player) {
        val id = player.getVarbit(Combat.SELECTED_AUTOCAST_VARBIT)
        if (id == 0) return
        val spell = CombatSpell.values.firstOrNull { it.autoCastId == id }
        if (spell == null || !canAutocast(player, spell)) {
            reset(player)
        }
    }
}

