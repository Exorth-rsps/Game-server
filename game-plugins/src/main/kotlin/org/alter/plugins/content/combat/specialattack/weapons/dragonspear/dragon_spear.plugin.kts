package org.alter.plugins.content.combat.specialattack.weapons.dragonspear

import org.alter.game.model.entity.AreaSound
import org.alter.api.cfg.Animation
import org.alter.api.cfg.Graphic
import org.alter.api.cfg.Items
import org.alter.api.cfg.Sound
import org.alter.plugins.content.combat.specialattack.SpecialAttacks

val SPECIAL_REQUIREMENT = 25

fun register(item: Int) {
    SpecialAttacks.register(item, SPECIAL_REQUIREMENT) {
        player.animate(id = Animation.SHOVE_SPEAR_SPECIAL)
        player.graphic(id = Graphic.DRAGON_SPEAR_SPECIAL)
        world.spawn(AreaSound(tile = player.tile, id = Sound.SHOVE, radius = 10, volume = 1))

        target.stun(cycles = 4) {
            target.graphic(id = Graphic.DRAGON_SPEAR_STUN)
        }
    }
}

register(Items.DRAGON_SPEAR)
register(Items.DRAGON_SPEARP)
register(Items.DRAGON_SPEARP_5716)
register(Items.DRAGON_SPEARP_5730)
register(Items.DRAGON_SPEARKP)
register(Items.DRAGON_SPEAR_CR)
