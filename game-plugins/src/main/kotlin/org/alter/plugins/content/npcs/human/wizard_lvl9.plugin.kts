package org.alter.plugins.content.npcs.human

import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.WIZARD_3257
)

val table = DropTableFactory
val droptable =
    table.build {
        guaranteed {
            obj(Items.BONES, quantity = 1)
        }
        main {
            total(128)
            obj(Items.FIRE_RUNE, quantity = 5, slots = 3)
            obj(Items.MIND_RUNE, quantity = 5, slots = 3)
            obj(Items.WATER_RUNE, quantity = 5, slots = 3)
            obj(Items.AIR_RUNE, quantity = 5, slots = 3)
            obj(Items.AIR_RUNE, quantity = 12, slots = 2)
            obj(Items.EARTH_RUNE, quantity = 12, slots = 2)
            obj(Items.BLOOD_RUNE, quantity = 2, slots = 1)
            obj(Items.STAFF, quantity = 1, slots = 8)
            obj(Items.WATER_TALISMAN, quantity = 1, slots = 3)
            obj(Items.MIND_TALISMAN, quantity = 1, slots = 4)
            obj(Items.CHAOS_RUNE, quantity = 2, slots = 8)
            obj(Items.COINS_995, quantity = 1, slots = 23)
            obj(Items.COINS_995, quantity = 2, slots = 9)
            obj(Items.COINS_995, quantity = 18, slots = 7)
            obj(Items.COINS_995, quantity = 30, slots = 1)
            obj(Items.BLUE_WIZARD_ROBE, quantity = 1, slots = 7)
            obj(Items.BLUE_WIZARD_HAT, quantity = 1, slots = 3)
            nothing(slots = 15)
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
            hitpoints = 14
            attack = 8
            strength = 8
            defence = 5
            magic = 10
            ranged = 1
        }
        bonuses {
            defenceStab = 0
            defenceSlash = 0
            defenceCrush = 0
            defenceMagic = 3
            defenceRanged = 0
        }
        anims {
            attack = Animation.HUMAN_PUNCH
            block = Animation.HUMAN_DEFEND
            death = Animation.HUMAN_DEATH
        }
        sound {
            attackSound = Sound.HUMAN_ATTACK
            blockSound = Sound.HUMAN_BLOCK_1
            deathSound = Sound.HUMAN_DEATH
        }
    }
}
