package org.alter.plugins.content.combat.specialattack.weapons.dragon2hsword

import org.alter.game.model.entity.AreaSound
import org.alter.api.cfg.Animation
import org.alter.api.cfg.Graphic
import org.alter.api.cfg.Items
import org.alter.api.cfg.Sound
import org.alter.plugins.content.combat.dealHit
import org.alter.plugins.content.combat.formula.MeleeCombatFormula
import org.alter.plugins.content.combat.specialattack.SpecialAttacks

val SPECIAL_REQUIREMENT = 60

fun register(item: Int) {
    SpecialAttacks.register(item, SPECIAL_REQUIREMENT) {
        player.animate(id = Animation.DRAGON_2H_SWORD_SPECIAL)
        player.graphic(id = Graphic.DRAGON_2H_SWORD_SPECIAL)
        world.spawn(AreaSound(tile = player.tile, id = Sound.CLEAVE, radius = 10, volume = 1))

        val maxHit = MeleeCombatFormula.getMaxHit(player, target, specialAttackMultiplier = 1.15)
        val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.25)
        val landHit = accuracy >= world.randomDouble()
        player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)
    }
}

register(Items.DRAGON_2H_SWORD)
register(Items.DRAGON_2H_SWORD_20559)
register(Items.DRAGON_2H_SWORD_CR)
