package org.alter.plugins.content.combat.specialattack.weapons.dragonhalberd

import org.alter.game.model.entity.AreaSound
import org.alter.api.cfg.Animation
import org.alter.api.cfg.Graphic
import org.alter.api.cfg.Items
import org.alter.api.cfg.Sound
import org.alter.plugins.content.combat.dealHit
import org.alter.plugins.content.combat.formula.MeleeCombatFormula
import org.alter.plugins.content.combat.specialattack.SpecialAttacks

val SPECIAL_REQUIREMENT = 30

fun register(item: Int) {
    SpecialAttacks.register(item, SPECIAL_REQUIREMENT) {
        player.animate(id = Animation.HALBERD_SPECIAL)
        player.graphic(id = Graphic.DRAGON_HALBERD_SPECIAL_NORTH)
        world.spawn(AreaSound(tile = player.tile, id = Sound.HALBERD_SWIPE, radius = 10, volume = 1))

        val maxHit = MeleeCombatFormula.getMaxHit(player, target, specialAttackMultiplier = 1.10)
        val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.10)
        val landHit = accuracy >= world.randomDouble()
        player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)
    }
}

register(Items.DRAGON_HALBERD)
register(Items.DRAGON_HALBERD_CR)
