package org.alter.plugins.content.npcs.other

import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.ABYSSAL_GUARDIAN
)

val table = DropTableFactory
val droptable =
    table.build {
        guaranteed {
            obj(Items.ASHES, quantity = 1)
        }
        table("main") {
            total(128)
            obj(Items.PURE_ESSENCE_NOTED, quantityRange = 5..15, 11)
            nothing(117)
        }
        table("rare") {
            total(128)
            obj(Items.MEDIUM_POUCH, quantity = 1, 22)
            obj(Items.LARGE_POUCH, quantity = 1, 19)
            nothing(87)
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
            hitpoints = 45
            attack = 40
            strength = 40
            defence = 35
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
            attackSound = Sound.DEMON_ATTACK
            blockSound = Sound.DEMON_HIT
            deathSound = Sound.DEMON_DEATH
        }
    }
}
