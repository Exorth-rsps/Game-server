package org.alter.plugins.content.npcs.critters;

import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.BRINE_RAT_4501
)

val table = DropTableFactory
val droptable =
    table.build {
        guaranteed {
            obj(Items.BONES, quantity = 1)
            obj(Items.RAW_RAT_MEAT, quantity = 1)
        }
        table("rare") {
            total(128)
            obj(Items.CHAOS_RUNE, quantityRange = 1..5, 9)
            obj(Items.DEATH_RUNE, quantityRange = 1..4, 9)
            obj(Items.BLOOD_RUNE, quantityRange = 1..4, 9)
            nothing(101)
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
            hitpoints = 50
            attack = 70
            strength = 79
            defence = 40
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
            attack = Animation.BRINE_RAT_ATTACK
            block = Animation.BRINE_RAT_HIT
            death = Animation.BRINE_RAT_DEATH
        }

        sound {
            attackSound = Sound.RAT_ATTACK
            blockSound = Sound.RAT_HIT
            deathSound = Sound.RAT_DEATH
        }
    }
}