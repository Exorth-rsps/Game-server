package org.alter.plugins.content.combat.specialattack.weapons.brinesabre

import org.alter.game.model.entity.AreaSound
import org.alter.api.cfg.Items
import org.alter.plugins.content.combat.dealHit
import org.alter.plugins.content.combat.formula.MeleeCombatFormula
import org.alter.plugins.content.combat.specialattack.SpecialAttacks

val SPECIAL_REQUIREMENT = 60

SpecialAttacks.register(Items.BRINE_SABRE, SPECIAL_REQUIREMENT) {
    player.animate(id = 6118)
    player.graphic(id = 1048)
    world.spawn(AreaSound(tile = player.tile, id = 3473, radius = 10, volume = 1))

    val maxHit = MeleeCombatFormula.getMaxHit(player, target, specialAttackMultiplier = 1.25)
    val landHit = true
    player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)
}
