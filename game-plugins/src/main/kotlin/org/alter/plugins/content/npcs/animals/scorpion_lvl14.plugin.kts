package org.alter.plugins.content.npcs.animals;

import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.SCORPION
)

val table = DropTableFactory
val droptable =
    table.build {
        guaranteed {
            obj(Items.BONES, quantity = 1)
            obj(Items.ASHES, quantity = 1)
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
            respawnDelay = 25
            poisonChance = 0.0
            venomChance = 0.0
        }
        stats {
            hitpoints = 17
            attack = 11
            strength = 12
            defence = 11
            magic = 1
            ranged = 1
        }

        bonuses {
            defenceStab = 5
            defenceSlash = 15
            defenceCrush = 15
            defenceMagic = 0
            defenceRanged = 5
        }

        anims {
            attack = Animation.SCORPION_ATTACK
            block = Animation.SCORPION_HIT
            death = Animation.SCORPION_DEATH
        }

        sound {
            attackSound = Sound.SCORPION_ATTACK
            blockSound = Sound.SCORPION_HIT
            deathSound = Sound.SCORPION_DEATH
        }
    }
}