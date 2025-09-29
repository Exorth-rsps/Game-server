package org.alter.plugins.content.npcs.other

import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.GIANT_ROC
)

val table = DropTableFactory
val droptable =
    table.build {
        guaranteed {
            obj(Items.BIG_BONES, quantity = 1)
        }
        table("rare") {
            total(128)
            obj(Items.CRYSTAL_HELM_PERFECTED, quantity = 1, 1)
            obj(Items.CRYSTAL_BODY_PERFECTED, quantity = 1, 1)
            obj(Items.CRYSTAL_LEGS_PERFECTED, quantity = 1, 1)
            obj(Items.ARCHERS_RING, quantity = 1, 4)
            obj(Items.NECKLACE_OF_ANGUISH, quantity = 1, 1)
        }
        table("main") {
            total(128)
            obj(Items.RUNE_ARROW, quantityRange = 1..15, 5)
            obj(Items.ADAMANT_ARROW, quantityRange = 1..15, 10)
        }
        table("coins") {
            total(128)
            obj(Items.COINS, quantityRange = 400..1000, 1)
            obj(Items.COINS, quantityRange = 150..300, 5)
            obj(Items.COINS, quantityRange = 200..500, 7)
        }

    }

table.register(droptable, *ids)

on_npc_spawn(npc = Npcs.GIANT_ROC) {
    npc.animate(Animation.GIANT_ROC_SPAWN)
}

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    val playerName = p.username
    p.world.players.forEach {
        it.message("<col=8900331>[GLOBAL]</col> The Giant Roc has been slain by $playerName!", ChatMessageType.CONSOLE)
    }
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 300
            poisonChance = 0.0
            venomChance = 0.0
            followRange = 5
        }
        stats {
            hitpoints = 200
            attack = 150
            strength = 150
            defence = 150
            magic = 1
            ranged = 150
        }

        bonuses {
            defenceStab = 50
            defenceSlash = 50
            defenceCrush = 50
            defenceMagic = 0
            defenceRanged = 50
        }

        anims {
            attack = Animation.GIANT_ROC_ATTACK
            block = Animation.GIANT_ROC_HIT
            death = Animation.GIANT_ROC_DEATH
        }

        sound {
            attackSound = Sound.GIANT_ROC_PECK_ATTACK
            blockSound = Sound.GIANT_ROC_HIT
            deathSound = Sound.GIANT_ROC_DEATH
        }
    }
}
