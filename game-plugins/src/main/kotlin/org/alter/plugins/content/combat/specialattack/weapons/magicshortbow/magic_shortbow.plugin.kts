package org.alter.plugins.content.combat.specialattack.weapons.magicshortbow

import org.alter.game.model.entity.AreaSound
import org.alter.api.cfg.Animation
import org.alter.api.cfg.Graphic
import org.alter.api.cfg.Items
import org.alter.api.cfg.Sound
import org.alter.plugins.content.combat.dealHit
import org.alter.plugins.content.combat.formula.RangedCombatFormula
import org.alter.plugins.content.combat.specialattack.SpecialAttacks

val SPECIAL_REQUIREMENT = 55

fun register(item: Int) {
    SpecialAttacks.register(item, SPECIAL_REQUIREMENT) {
        player.animate(id = Animation.MAGIC_SHORTBOW_SPECIAL)
        player.graphic(id = Graphic.MAGIC_SHORTBOW_SPECIAL_DRAWBACK)
        world.spawn(AreaSound(tile = player.tile, id = Sound.SHORTBOW, radius = 10, volume = 1))

        val accuracy = RangedCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.25)
        val landHit = accuracy >= world.randomDouble()

        for (i in 0 until 2) {
            val maxHit = RangedCombatFormula.getMaxHit(player, target)
            val delay = if (target.entityType.isNpc) i + 1 else 1
            player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = delay)
        }
    }
}

register(Items.MAGIC_SHORTBOW)
register(Items.MAGIC_SHORTBOW_I)
