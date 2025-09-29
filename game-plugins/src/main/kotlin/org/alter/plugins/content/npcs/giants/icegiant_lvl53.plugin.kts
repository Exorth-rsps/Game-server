package org.alter.plugins.content.npcs.giants;

import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.ICE_GIANT
)

val table = DropTableFactory
val droptable =
    table.build {
        guaranteed {
            obj(Items.BIG_BONES, quantity = 1)
        }
        table("main") {
            total(128)
            obj(Items.OAK_LOGS_NOTED, quantityRange = 1..15, 10)
            obj(Items.WILLOW_LOGS_NOTED, quantityRange = 1..10, 1)
            obj(Items.MAPLE_LOGS_NOTED, quantityRange = 1..7, 1)
            obj(Items.YEW_LOGS_NOTED, quantityRange = 1..4, 1)
            nothing(115)
        }
        table("rare") {
            total(128)
            obj(Items.CAPE_OF_LEGENDS, quantity = 1, 1)
            nothing(127)
        }
        table("second") {
            total(128)
            obj(Items.LOGS_NOTED, quantityRange = 1..15, 1)
            nothing(127)
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
            attackSpeed = 5
            respawnDelay = 30
            poisonChance = 0.0
            venomChance = 0.0
        }
        stats {
            hitpoints = 70
            attack = 40
            strength = 40
            defence = 40
            magic = 1
            ranged = 1
        }

        bonuses {
            defenceStab = 0
            defenceSlash = 3
            defenceCrush = 2
            defenceMagic = 0
            defenceRanged = 0
        }


        anims {
            attack = Animation.ICE_GIANT_ATTACK
            block = Animation.ICE_GIANT_HIT
            death = Animation.ICE_GIANT_DEATH
        }

        sound {
            attackSound = Sound.GIANT_ATTACK
            blockSound = Sound.GIANT_HIT
            deathSound = Sound.GIANT_DEATH
        }
    }
}