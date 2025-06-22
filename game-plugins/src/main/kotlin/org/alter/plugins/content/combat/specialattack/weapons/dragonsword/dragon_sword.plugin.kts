package org.alter.plugins.content.combat.specialattack.weapons.dragonsword

import org.alter.game.model.entity.AreaSound
import org.alter.api.cfg.Items
import org.alter.api.cfg.Sound
import org.alter.plugins.content.combat.dealHit
import org.alter.plugins.content.combat.formula.MeleeCombatFormula
import org.alter.plugins.content.combat.specialattack.SpecialAttacks

val SPECIAL_REQUIREMENT = 25

SpecialAttacks.register(Items.DRAGON_SWORD, SPECIAL_REQUIREMENT) {
    player.animate(id = 7515)
    player.graphic(id = 1293)
    world.spawn(AreaSound(tile = player.tile, id = Sound.STABSWORD_STAB, radius = 10, volume = 1))

    val maxHit = MeleeCombatFormula.getMaxHit(player, target, specialAttackMultiplier = 1.25)
    val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.25)
    val landHit = accuracy >= world.randomDouble()
    player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)
}
