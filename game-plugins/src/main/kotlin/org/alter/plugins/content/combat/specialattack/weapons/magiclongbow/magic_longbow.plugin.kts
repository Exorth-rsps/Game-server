package org.alter.plugins.content.combat.specialattack.weapons.magiclongbow

import org.alter.game.model.entity.AreaSound
import org.alter.api.cfg.Animation
import org.alter.api.cfg.Items
import org.alter.api.cfg.Sound
import org.alter.plugins.content.combat.dealHit
import org.alter.plugins.content.combat.formula.RangedCombatFormula
import org.alter.plugins.content.combat.specialattack.SpecialAttacks

val SPECIAL_REQUIREMENT = 55

SpecialAttacks.register(Items.MAGIC_LONGBOW, SPECIAL_REQUIREMENT) {
    player.animate(id = Animation.HUMAN_BOW_ATTACK)
    world.spawn(AreaSound(tile = player.tile, id = Sound.LONGBOW, radius = 10, volume = 1))

    val maxHit = RangedCombatFormula.getMaxHit(player, target, specialAttackMultiplier = 1.5)
    val accuracy = RangedCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.5)
    val landHit = accuracy >= world.randomDouble()
    player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)
}
