package org.alter.plugins.content.npcs.other

import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.ABYSSAL_WALKER
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
            hitpoints = 55
            attack = 55
            strength = 55
            defence = 45
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
            attack = Animation.DEMON_ATTACK
            block = Animation.DEMON_HIT
            death = Animation.DEMON_DEATH
        }

        sound {
            attackSound = Sound.ABYSSAL_WALKER_ATTACK
            blockSound = Sound.ABYSSAL_WALKER_HIT
            deathSound = Sound.ABYSSAL_WALKER_DEATH
        }
    }
}
