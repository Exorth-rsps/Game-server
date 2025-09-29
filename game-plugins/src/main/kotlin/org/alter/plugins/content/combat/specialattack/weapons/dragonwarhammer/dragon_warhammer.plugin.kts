package org.alter.plugins.content.combat.specialattack.weapons.dragonwarhammer

import org.alter.game.model.entity.AreaSound
import org.alter.game.model.entity.Player
import org.alter.game.model.entity.Npc
import org.alter.api.Skills
import org.alter.api.NpcSkills
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
        player.animate(id = Animation.DRAGON_WARHAMMER_SPECIAL)
        player.graphic(id = Graphic.DRAGON_WARHAMMER_SPECIAL)
        world.spawn(AreaSound(tile = player.tile, id = Sound.QUICKSMASH, radius = 10, volume = 1))

        val maxHit = MeleeCombatFormula.getMaxHit(player, target)
        val accuracy = MeleeCombatFormula.getAccuracy(player, target, specialAttackMultiplier = 1.5)
        val landHit = accuracy >= world.randomDouble()
        val hit = player.dealHit(target = target, maxHit = maxHit, landHit = landHit, delay = 1)

        if (target.entityType.isPlayer) {
            val skills = (target as Player).getSkills()
            val drain = if (hit.landed) {
                (skills.getCurrentLevel(Skills.DEFENCE) * 0.30).toInt()
            } else {
                (skills.getCurrentLevel(Skills.DEFENCE) * 0.05).toInt()
            }
            skills.decrementCurrentLevel(Skills.DEFENCE, drain, capped = false)
        } else if (target.entityType.isNpc) {
            val stats = (target as Npc).stats
            val drain = if (hit.landed) {
                (stats.getCurrentLevel(NpcSkills.DEFENCE) * 0.30).toInt()
            } else {
                (stats.getCurrentLevel(NpcSkills.DEFENCE) * 0.05).toInt()
            }
            stats.decrementCurrentLevel(NpcSkills.DEFENCE, drain, capped = false)
        }
    }
}

register(Items.DRAGON_WARHAMMER)
register(Items.DRAGON_WARHAMMER_20785)
register(Items.DRAGON_WARHAMMER_OR)
register(Items.DRAGON_WARHAMMER_CR)
