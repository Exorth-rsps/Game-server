package org.alter.plugins.content.combat.specialattack.weapons.dragonhasta

import org.alter.game.model.entity.AreaSound
import org.alter.api.cfg.Animation
import org.alter.api.cfg.Graphic
import org.alter.api.cfg.Items
import org.alter.api.cfg.Sound
import org.alter.plugins.content.combat.dealHit
import org.alter.plugins.content.combat.formula.MeleeCombatFormula
import org.alter.plugins.content.combat.specialattack.SpecialAttacks

val SPECIAL_REQUIREMENT = 30

SpecialAttacks.register(Items.DRAGON_HASTA, SPECIAL_REQUIREMENT) {
    player.animate(id = Animation.DRAGON_HASTA_SPECIAL)
    player.graphic(id = Graphic.DRAGON_HASTA_SPECIAL)
    world.spawn(AreaSound(tile = player.tile, id = Sound.PUNCTURE, radius = 10, volume = 1))

    val maxHit = MeleeCombatFormula.getMaxHit(player, target, specialAttackMultiplier = 1.05)
    val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.05)
    val landHit = accuracy >= world.randomDouble()
    player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)
}

SpecialAttacks.register(Items.DRAGON_HASTAP, SPECIAL_REQUIREMENT) {
    player.animate(id = Animation.DRAGON_HASTA_SPECIAL)
    player.graphic(id = Graphic.DRAGON_HASTA_SPECIAL)
    world.spawn(AreaSound(tile = player.tile, id = Sound.PUNCTURE, radius = 10, volume = 1))

    val maxHit = MeleeCombatFormula.getMaxHit(player, target, specialAttackMultiplier = 1.05)
    val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.05)
    val landHit = accuracy >= world.randomDouble()
    player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)
}

SpecialAttacks.register(Items.DRAGON_HASTAP_22737, SPECIAL_REQUIREMENT) {
    player.animate(id = Animation.DRAGON_HASTA_SPECIAL)
    player.graphic(id = Graphic.DRAGON_HASTA_SPECIAL)
    world.spawn(AreaSound(tile = player.tile, id = Sound.PUNCTURE, radius = 10, volume = 1))

    val maxHit = MeleeCombatFormula.getMaxHit(player, target, specialAttackMultiplier = 1.05)
    val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.05)
    val landHit = accuracy >= world.randomDouble()
    player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)
}

SpecialAttacks.register(Items.DRAGON_HASTAP_22740, SPECIAL_REQUIREMENT) {
    player.animate(id = Animation.DRAGON_HASTA_SPECIAL)
    player.graphic(id = Graphic.DRAGON_HASTA_SPECIAL)
    world.spawn(AreaSound(tile = player.tile, id = Sound.PUNCTURE, radius = 10, volume = 1))

    val maxHit = MeleeCombatFormula.getMaxHit(player, target, specialAttackMultiplier = 1.05)
    val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.05)
    val landHit = accuracy >= world.randomDouble()
    player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)
}

SpecialAttacks.register(Items.DRAGON_HASTAKP, SPECIAL_REQUIREMENT) {
    player.animate(id = Animation.DRAGON_HASTA_SPECIAL)
    player.graphic(id = Graphic.DRAGON_HASTA_SPECIAL)
    world.spawn(AreaSound(tile = player.tile, id = Sound.PUNCTURE, radius = 10, volume = 1))

    val maxHit = MeleeCombatFormula.getMaxHit(player, target, specialAttackMultiplier = 1.05)
    val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.05)
    val landHit = accuracy >= world.randomDouble()
    player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)
}
