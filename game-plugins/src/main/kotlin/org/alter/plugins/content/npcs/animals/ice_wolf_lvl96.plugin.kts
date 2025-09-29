package org.alter.plugins.content.npcs.animals;

import org.alter.plugins.content.combat.isBeingAttacked
import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.ICE_WOLF
)

val table = DropTableFactory
val droptable =
    table.build {
            guaranteed {
                obj(Items.WOLF_BONES, quantity = 1)
            }
        }




table.register(droptable, *ids)
on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player

}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}


ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 30
            poisonChance = 0.0
            venomChance = 0.0
        }
        stats {
            hitpoints = 70
            attack = 100
            strength = 90
            defence = 70
            magic = 1
            ranged = 1
        }

        bonuses {
            defenceStab = 0
            defenceSlash = 0
            defenceCrush = 0
            defenceMagic = 0
            defenceRanged = 0
        }

        anims {
            attack = Animation.ICE_WOLF_ATTACK
            block = Animation.ICE_WOLF_DEFEND
            death = Animation.ICE_WOLF_DEATH
        }

        sound {
            attackSound = Sound.WOLF_ATTACK
            blockSound = Sound.WOLF_HIT
            deathSound = Sound.WOLF_DEATH
        }
    }
}