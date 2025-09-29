package org.alter.plugins.content.npcs.human;

import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.MONK_OF_ZAMORAK_529
)

val table = DropTableFactory
val droptable =
    table.build {
        guaranteed {
            obj(Items.BONES, quantity = 1)
        }
        table("main") {
            total(128)
            obj(Items.EYE_OF_NEWT_NOTED, quantityRange = 1..5, 10)
            obj(Items.LIMPWURT_ROOT_NOTED, quantityRange = 1..5, 8)
            obj(Items.UNICORN_HORN_DUST_NOTED, quantityRange = 1..5, 6)
            obj(Items.WHITE_BERRIES_NOTED, quantityRange = 1..5, 5)
            obj(Items.DRAGON_SCALE_DUST_NOTED, quantityRange = 1..5, 5)
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
            hitpoints = 40
            attack = 38
            strength = 38
            defence = 42
            magic = 40
            ranged = 1
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