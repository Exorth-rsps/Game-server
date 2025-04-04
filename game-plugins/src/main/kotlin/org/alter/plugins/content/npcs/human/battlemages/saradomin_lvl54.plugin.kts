package org.alter.plugins.content.npcs.human.battlemages;

import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.BATTLE_MAGE_1611
)

val table = DropTableFactory
val droptable =
    table.build {
        guaranteed {
            obj(Items.BONES, quantity = 1)
        }
        main {
            total(512)
            nothing(64)
            obj(Items.MIND_RUNE, quantityRange = 1..41, slots = 16)
            obj(Items.MIND_TALISMAN, quantity = 1, slots = 2)
            obj(Items.SARADOMIN_STAFF, quantity = 1, slots = 1)
            obj(Items.MIND_TIARA, quantity = 1, slots = 1)
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
            respawnDelay = 50
            poisonChance = 0.0
            venomChance = 0.0
        }
        stats {
            hitpoints = 25
            attack = 5
            strength = 5
            defence = 10
            magic = 10
            ranged = 0
        }

        bonuses {
            defenceStab = 0
            defenceSlash = 0
            defenceCrush = 0
            defenceMagic = 15
            defenceRanged = 0
        }


        anims {
            attack = Animation.HUMAN_STAFF_BASH
            block = Animation.HUMAN_STAFF_DEFEND
            death = Animation.HUMAN_DEATH
        }

        sound {
            attackSound = Sound.HUMAN_ATTACK
            blockSound = Sound.HUMAN_BLOCK_1
            deathSound = Sound.HUMAN_DEATH
        }
    }
}