package org.alter.plugins.content.combat.autocast

import org.alter.api.cfg.Items
import org.alter.plugins.content.combat.strategy.magic.CombatSpell

/**
 * Provides quick lookups for autocast spell ids and any staff restrictions.
 */
object AutocastSpells {

    private val spellsById = CombatSpell.values.associateBy { it.autoCastId }

    private val restrictedSpells: Map<CombatSpell, IntArray> = buildMap {
        // Ancient magicks require a proper staff to autocast.
        val ancientStaves = intArrayOf(Items.ANCIENT_STAFF)
        CombatSpell.values
            .filter { it.autoCastId in 31..46 }
            .forEach { put(it, ancientStaves) }
        // TODO: Populate additional restrictions such as Iban blast or god spells when added.
    }

    fun forId(id: Int): CombatSpell? = spellsById[id]

    fun allowedStaves(spell: CombatSpell): IntArray? = restrictedSpells[spell]
}

