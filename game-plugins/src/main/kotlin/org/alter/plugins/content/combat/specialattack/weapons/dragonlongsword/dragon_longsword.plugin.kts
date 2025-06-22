package org.alter.plugins.content.combat.specialattack.weapons.dragonlongsword

import org.alter.game.model.entity.AreaSound
import org.alter.api.cfg.Animation
import org.alter.api.cfg.Graphic
import org.alter.api.cfg.Items
import org.alter.api.cfg.Sound
import org.alter.plugins.content.combat.dealHit
import org.alter.plugins.content.combat.formula.MeleeCombatFormula
import org.alter.plugins.content.combat.specialattack.SpecialAttacks

val SPECIAL_REQUIREMENT = 25

SpecialAttacks.register(Items.DRAGON_LONGSWORD, SPECIAL_REQUIREMENT) {
    player.animate(id = Animation.DRAGON_LONGSWORD_SPECIAL)
    player.graphic(id = Graphic.DRAGON_LONGSWORD_SPECIAL)
    world.spawn(AreaSound(tile = player.tile, id = Sound.STABSWORD_SLASH, radius = 10, volume = 1))

    val maxHit = MeleeCombatFormula.getMaxHit(player, target, specialAttackMultiplier = 1.25)
    val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.25)
    val landHit = accuracy >= world.randomDouble()
    player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)
}

SpecialAttacks.register(Items.DRAGON_LONGSWORD_BH, SPECIAL_REQUIREMENT) {
    player.animate(id = Animation.DRAGON_LONGSWORD_SPECIAL)
    player.graphic(id = Graphic.DRAGON_LONGSWORD_SPECIAL)
    world.spawn(AreaSound(tile = player.tile, id = Sound.STABSWORD_SLASH, radius = 10, volume = 1))

    val maxHit = MeleeCombatFormula.getMaxHit(player, target, specialAttackMultiplier = 1.25)
    val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.25)
    val landHit = accuracy >= world.randomDouble()
    player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)
}

SpecialAttacks.register(Items.DRAGON_LONGSWORD_CR, SPECIAL_REQUIREMENT) {
    player.animate(id = Animation.DRAGON_LONGSWORD_SPECIAL)
    player.graphic(id = Graphic.DRAGON_LONGSWORD_SPECIAL)
    world.spawn(AreaSound(tile = player.tile, id = Sound.STABSWORD_SLASH, radius = 10, volume = 1))

    val maxHit = MeleeCombatFormula.getMaxHit(player, target, specialAttackMultiplier = 1.25)
    val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.25)
    val landHit = accuracy >= world.randomDouble()
    player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)
}
