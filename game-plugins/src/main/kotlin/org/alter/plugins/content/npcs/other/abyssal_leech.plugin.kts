package org.alter.plugins.content.npcs.other

import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.ABYSSAL_LEECH
)

val table = DropTableFactory
val droptable =
    table.build {
        guaranteed {
            obj(Items.BONES, quantity = 1)
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
            hitpoints = 35
            attack = 30
            strength = 30
            defence = 20
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
            attack = Animation.LEECH_ATTACK
            block = Animation.LEECH_DEFEND
            death = Animation.LEECH_DEATH
        }

        sound {
            attackSound = Sound.ABYSSAL_LEECH_SUCK
            blockSound = Sound.ABYSSAL_LEECH_HIT
            deathSound = Sound.ABYSSAL_LEECH_DEATH
        }
    }
}
