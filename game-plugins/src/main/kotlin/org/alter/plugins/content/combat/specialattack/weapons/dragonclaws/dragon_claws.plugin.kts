package org.alter.plugins.content.combat.specialattack.weapons.dragonclaws

import org.alter.game.model.entity.AreaSound
import org.alter.api.cfg.Animation
import org.alter.api.cfg.Graphic
import org.alter.api.cfg.Items
import org.alter.api.cfg.Sound
import org.alter.plugins.content.combat.dealHit
import org.alter.plugins.content.combat.formula.MeleeCombatFormula
import org.alter.plugins.content.combat.specialattack.SpecialAttacks

val SPECIAL_REQUIREMENT = 50

SpecialAttacks.register(Items.DRAGON_CLAWS, SPECIAL_REQUIREMENT) {
    player.animate(id = Animation.DRAGON_CLAWS_SPECIAL)
    player.graphic(id = Graphic.DRAGON_CLAWS_SPECIAL)
    world.spawn(AreaSound(tile = player.tile, id = Sound.PUNCTURE, radius = 10, volume = 1))

    val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.25)
    val landHit = accuracy >= world.randomDouble()

    for (i in 0 until 4) {
        val maxHit = MeleeCombatFormula.getMaxHit(player, target)
        val delay = if (target.entityType.isNpc) i + 1 else 1
        player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = delay)
    }
}

SpecialAttacks.register(Items.DRAGON_CLAWS_20784, SPECIAL_REQUIREMENT) {
    player.animate(id = Animation.DRAGON_CLAWS_SPECIAL)
    player.graphic(id = Graphic.DRAGON_CLAWS_SPECIAL)
    world.spawn(AreaSound(tile = player.tile, id = Sound.PUNCTURE, radius = 10, volume = 1))

    val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.25)
    val landHit = accuracy >= world.randomDouble()

    for (i in 0 until 4) {
        val maxHit = MeleeCombatFormula.getMaxHit(player, target)
        val delay = if (target.entityType.isNpc) i + 1 else 1
        player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = delay)
    }
}

SpecialAttacks.register(Items.DRAGON_CLAWS_OR, SPECIAL_REQUIREMENT) {
    player.animate(id = Animation.DRAGON_CLAWS_SPECIAL)
    player.graphic(id = Graphic.DRAGON_CLAWS_SPECIAL)
    world.spawn(AreaSound(tile = player.tile, id = Sound.PUNCTURE, radius = 10, volume = 1))

    val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.25)
    val landHit = accuracy >= world.randomDouble()

    for (i in 0 until 4) {
        val maxHit = MeleeCombatFormula.getMaxHit(player, target)
        val delay = if (target.entityType.isNpc) i + 1 else 1
        player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = delay)
    }
}

SpecialAttacks.register(Items.DRAGON_CLAWS_CR, SPECIAL_REQUIREMENT) {
    player.animate(id = Animation.DRAGON_CLAWS_SPECIAL)
    player.graphic(id = Graphic.DRAGON_CLAWS_SPECIAL)
    world.spawn(AreaSound(tile = player.tile, id = Sound.PUNCTURE, radius = 10, volume = 1))

    val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.25)
    val landHit = accuracy >= world.randomDouble()

    for (i in 0 until 4) {
        val maxHit = MeleeCombatFormula.getMaxHit(player, target)
        val delay = if (target.entityType.isNpc) i + 1 else 1
        player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = delay)
    }
}
