package org.alter.plugins.content.combat.specialattack.weapons.dragonbattleaxe

import org.alter.game.model.entity.AreaSound
import org.alter.api.cfg.Animation
import org.alter.api.cfg.Graphic
import org.alter.api.cfg.Items
import org.alter.api.Skills
import org.alter.plugins.content.combat.specialattack.SpecialAttacks

val SPECIAL_REQUIREMENT = 100

SpecialAttacks.register(Items.DRAGON_BATTLEAXE, SPECIAL_REQUIREMENT, executeOnSpecBar = true) {
    // Special attack animation and effects
    player.animate(id = Animation.DRAGON_BATTLEAXE_SPECIAL)
    player.graphic(id = Graphic.DRAGON_BATTLEAXE_SPECIAL)
    world.spawn(AreaSound(tile = player.tile, id = 2537, radius = 10, volume = 1))
    player.forceChat("Raarrrrrgggggghhhhhhh!")

    val skills = player.getSkills()

    val atkDrain = (skills.getBaseLevel(Skills.ATTACK) * 0.1).toInt()
    val defDrain = (skills.getBaseLevel(Skills.DEFENCE) * 0.1).toInt()
    val rangeDrain = (skills.getBaseLevel(Skills.RANGED) * 0.1).toInt()
    val magicDrain = (skills.getBaseLevel(Skills.MAGIC) * 0.1).toInt()

    val strBoost = atkDrain + defDrain + rangeDrain + magicDrain

    skills.decrementCurrentLevel(Skills.ATTACK, atkDrain, capped = true)
    skills.decrementCurrentLevel(Skills.DEFENCE, defDrain, capped = true)
    skills.decrementCurrentLevel(Skills.RANGED, rangeDrain, capped = true)
    skills.decrementCurrentLevel(Skills.MAGIC, magicDrain, capped = true)

    skills.incrementCurrentLevel(Skills.STRENGTH, strBoost, capped = false)
}
