package org.alter.plugins.content.npcs.critters;

import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.GIANT_RAT_2858
)

val table = DropTableFactory
val droptable =
    table.build {
        guaranteed {
            obj(Items.BONES, quantity = 1)
            obj(Items.RAW_RAT_MEAT, quantity = 1)
            obj(Items.COINS, quantityRange = 1..25)
        }
        table("second") {
            total(11)
            obj(Items.IRON_MED_HELM, quantity = 1, 1)
            obj(Items.IRON_CHAINBODY, quantity = 1, 1)
            nothing(9)
        }
        table("rare") {
            total(128)
            obj(Items.IRON_FULL_HELM, quantity = 1, 1)
            obj(Items.IRON_PLATEBODY, quantity = 1, 18)
            obj(Items.IRON_PLATELEGS, quantity = 1, 17)
            obj(Items.IRON_PLATESKIRT, quantity = 1, 16)
            obj(Items.IRON_2H_SWORD, quantity = 1, 7)
            obj(Items.IRON_SCIMITAR, quantity = 1, 3)
            nothing(66)
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
            followRange = 5
        }
        stats {
            hitpoints = 5
            attack = 2
            strength = 3
            defence = 2
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
            attack = Animation.GIANT_CRYPT_RAT_ATTACK
            block = Animation.GIANT_CRYPT_RAT_DEFEND
            death = Animation.GIANT_CRYPT_RAT_DEATH
        }

        sound {
            attackSound = Sound.RAT_ATTACK
            blockSound = Sound.RAT_HIT
            deathSound = Sound.RAT_DEATH
        }
    }
}