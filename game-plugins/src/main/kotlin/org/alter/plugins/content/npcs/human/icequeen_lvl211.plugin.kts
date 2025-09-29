package org.alter.plugins.content.npcs.human;

import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.ICE_QUEEN
)

val table = DropTableFactory
val droptable =
    table.build {
        guaranteed {
            obj(Items.COINS, quantityRange = 250..500, 1)
        }
        table("rare") {
            total(128)
            obj(Items.ICE_GLOVES, quantity = 1, 1)
            obj(Items.NEW_CRYSTAL_BOW, quantity = 1, 1)
            obj(Items.NEW_CRYSTAL_SHIELD, quantity = 1, 1)
        }
        table("main") {
            total(128)
            obj(Items.RANARR_WEED_NOTED, quantityRange = 1..8, 7)
            obj(Items.DWARF_WEED_NOTED, quantityRange = 1..5, 7)
            obj(Items.TORSTOL_NOTED, quantityRange = 1..3, 5)
            obj(Items.BLOOD_RUNE, quantityRange = 1..10, 9)
            obj(Items.DEATH_RUNE, quantityRange = 1..10, 9)
        }
        table("second") {
            total(128)
            obj(Items.RING_OF_LIFE, quantity = 1, 2)
            obj(Items.RUNE_ARROWTIPS, quantityRange = 1..5, 5)
            obj(Items.ADAMANT_ARROWTIPS, quantityRange = 1..5, 5)
            obj(Items.MITHRIL_ARROWTIPS, quantityRange = 1..8, 5)
        }

    }


table.register(droptable, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    val playerName = p.username
    p.world.players.forEach {
        it.message("<col=8900331>[GLOBAL]</col> Ice Queen has been slain by $playerName!", ChatMessageType.CONSOLE)
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
        }
        stats {
            hitpoints = 304
            attack = 185
            strength = 184
            defence = 185
            magic = 120
            ranged = 1
        }

        bonuses {
            defenceStab = 60
            defenceSlash = 80
            defenceCrush = 20
            defenceMagic = 20
            defenceRanged = 60
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