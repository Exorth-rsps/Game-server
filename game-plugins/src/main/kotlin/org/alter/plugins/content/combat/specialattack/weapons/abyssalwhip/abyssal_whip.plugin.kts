package org.alter.plugins.content.combat.specialattack.weapons.abyssalwhip

import org.alter.game.model.entity.AreaSound
import org.alter.game.model.entity.Player
import org.alter.api.cfg.Animation
import org.alter.api.cfg.Graphic
import org.alter.api.cfg.Items
import org.alter.api.cfg.Sound
import org.alter.plugins.content.combat.dealHit
import org.alter.plugins.content.combat.formula.MeleeCombatFormula
import org.alter.plugins.content.combat.specialattack.SpecialAttacks

val SPECIAL_REQUIREMENT = 50

fun register(item: Int) {
    SpecialAttacks.register(item, SPECIAL_REQUIREMENT) {
        player.animate(id = Animation.HUMAN_WHIP_SWING)
        player.graphic(id = Graphic.WHIP_SPECIAL)
        world.spawn(AreaSound(tile = player.tile, id = Sound.WHIP, radius = 10, volume = 1))

        val maxHit = MeleeCombatFormula.getMaxHit(player, target)
        val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.25)
        val landHit = accuracy >= world.randomDouble()
        player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1) { hit ->
            if (hit.landed && target.entityType.isPlayer) {
                val damage = hit.hit.hitmarks.sumOf { it.damage }
                val drain = damage * 100
                val victim = target as Player
                victim.runEnergy = (victim.runEnergy - drain).coerceAtLeast(0.0)
                victim.sendRunEnergy(victim.runEnergy.toInt())
            }
        }
    }
}

register(Items.ABYSSAL_WHIP)
register(Items.ABYSSAL_WHIP_4178)
register(Items.VOLCANIC_ABYSSAL_WHIP)
register(Items.FROZEN_ABYSSAL_WHIP)
register(Items.ABYSSAL_WHIP_20405)
register(Items.ABYSSAL_WHIP_OR)
