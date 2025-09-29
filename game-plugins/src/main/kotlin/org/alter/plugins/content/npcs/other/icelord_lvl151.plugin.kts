package org.alter.plugins.content.npcs.other;

import org.alter.plugins.content.drops.DropTableFactory

set_multi_combat_region(region = 11925)
set_multi_combat_region(region = 12181)
val ids = intArrayOf(
    Npcs.ICELORD_855,
)

val table = DropTableFactory
val droptable =
    table.build {
        guaranteed {
            obj(Items.BIG_BONES, quantity = 1)
        }
        table("main") {
            total(128)
            obj(Items.RUNE_SWORD, quantity = 1, 25)
            obj(Items.RUNE_BATTLEAXE, quantity = 1, 10)
            obj(Items.RUNE_WARHAMMER, quantity = 1, 10)
            obj(Items.RUNE_SCIMITAR, quantity = 1, 6)
        }
        table("rare") {
            total(128)
            obj(Items.ICY_KEY, quantity = 1, 1)
            obj(Items.RUNE_PLATELEGS, quantity = 1, 4)
            obj(Items.RUNE_PLATEBODY, quantity = 1, 4)
            obj(Items.RUNE_KITESHIELD, quantity = 1, 4)
            obj(Items.RUNE_FULL_HELM, quantity = 1, 4)
        }
        table("gems") {
            total(128)
            obj(Items.UNCUT_DIAMOND, quantity = 1, 6)
            obj(Items.UNCUT_RUBY, quantity = 1, 8)
            obj(Items.UNCUT_EMERALD, quantity = 1, 12)
            obj(Items.UNCUT_SAPPHIRE, quantity = 1, 15)
        }
        table("herb-secondaries") {
            total(128)
            obj(Items.EYE_OF_NEWT, quantity = 1, 25)
            obj(Items.LIMPWURT_ROOT, quantity = 1, 25)
        }
    }



table.register(droptable, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    val playerName = p.username
    p.world.players.forEach {
        it.message("<col=8900331>[GLOBAL]</col> The Icelord Champion has been slain by $playerName!", ChatMessageType.CONSOLE)
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
            hitpoints = 220
            attack = 80
            strength = 80
            defence = 60
            magic = 10
            ranged = 10
        }

        bonuses {
            defenceStab = 5
            defenceSlash = 35
            defenceCrush = 5
            defenceMagic = -10
            defenceRanged = 200
        }


        anims {
            attack = Animation.ICE_LORD_ATTACK
            block = Animation.ICE_LORD_HIT
            death = Animation.ICE_LORD_DEATH
        }

        sound {
            attackSound = Sound.GIANT_ATTACK
            blockSound = Sound.GIANT_HIT
            deathSound = Sound.GIANT_DEATH
        }
    }
}