package org.alter.plugins.content.combat.specialattack.weapons.dragonscimitar

import org.alter.game.model.entity.AreaSound
import org.alter.game.model.entity.Player
import org.alter.api.cfg.Animation
import org.alter.api.cfg.Graphic
import org.alter.api.cfg.Items
import org.alter.api.cfg.Sound
import org.alter.plugins.content.combat.dealHit
import org.alter.plugins.content.combat.formula.MeleeCombatFormula
import org.alter.plugins.content.combat.specialattack.SpecialAttacks
import org.alter.plugins.content.mechanics.prayer.Prayers

val SPECIAL_REQUIREMENT = 55

SpecialAttacks.register(Items.DRAGON_SCIMITAR, SPECIAL_REQUIREMENT) {
    player.animate(id = Animation.DRAGON_SCIMITAR_SPECIAL)
    player.graphic(id = Graphic.DRAGON_SCIMITAR_SPECIAL)
    world.spawn(AreaSound(tile = player.tile, id = Sound.SEVER, radius = 10, volume = 1))

    val maxHit = MeleeCombatFormula.getMaxHit(player, target)
    val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.25)
    val landHit = accuracy >= world.randomDouble()
    val hit = player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)

    if (hit.landed && target.entityType.isPlayer) {
        Prayers.disableOverheads(target as Player, 5)
    }
}

SpecialAttacks.register(Items.DRAGON_SCIMITAR_OR, SPECIAL_REQUIREMENT) {
    player.animate(id = Animation.DRAGON_SCIMITAR_SPECIAL)
    player.graphic(id = Graphic.DRAGON_SCIMITAR_SPECIAL)
    world.spawn(AreaSound(tile = player.tile, id = Sound.SEVER, radius = 10, volume = 1))

    val maxHit = MeleeCombatFormula.getMaxHit(player, target)
    val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.25)
    val landHit = accuracy >= world.randomDouble()
    val hit = player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)

    if (hit.landed && target.entityType.isPlayer) {
        Prayers.disableOverheads(target as Player, 5)
    }
}

SpecialAttacks.register(Items.DRAGON_SCIMITAR_20406, SPECIAL_REQUIREMENT) {
    player.animate(id = Animation.DRAGON_SCIMITAR_SPECIAL)
    player.graphic(id = Graphic.DRAGON_SCIMITAR_SPECIAL)
    world.spawn(AreaSound(tile = player.tile, id = Sound.SEVER, radius = 10, volume = 1))

    val maxHit = MeleeCombatFormula.getMaxHit(player, target)
    val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.25)
    val landHit = accuracy >= world.randomDouble()
    val hit = player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)

    if (hit.landed && target.entityType.isPlayer) {
        Prayers.disableOverheads(target as Player, 5)
    }
}

SpecialAttacks.register(Items.DRAGON_SCIMITAR_CR, SPECIAL_REQUIREMENT) {
    player.animate(id = Animation.DRAGON_SCIMITAR_SPECIAL)
    player.graphic(id = Graphic.DRAGON_SCIMITAR_SPECIAL)
    world.spawn(AreaSound(tile = player.tile, id = Sound.SEVER, radius = 10, volume = 1))

    val maxHit = MeleeCombatFormula.getMaxHit(player, target)
    val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.25)
    val landHit = accuracy >= world.randomDouble()
    val hit = player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)

    if (hit.landed && target.entityType.isPlayer) {
        Prayers.disableOverheads(target as Player, 5)
    }
}

