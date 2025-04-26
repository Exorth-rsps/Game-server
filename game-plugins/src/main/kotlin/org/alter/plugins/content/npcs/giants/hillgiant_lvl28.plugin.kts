package org.alter.plugins.content.npcs.giants;

import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.HILL_GIANT,
    Npcs.HILL_GIANT_2099,
    Npcs.HILL_GIANT_2100,
    Npcs.HILL_GIANT_2101,
    Npcs.HILL_GIANT_2102,
    Npcs.HILL_GIANT_2103
)

val table = DropTableFactory
val droptable =
    table.build {
        guaranteed {
            obj(Items.BIG_BONES, quantity = 1)
        }
        main {
            total(1024)
            obj(Items.IRON_ARROW, quantityRange = 25..150, slots = 8)
            obj(Items.STEEL_ARROW, quantityRange = 1..75, slots = 4)
            obj(Items.AIR_RUNE, quantityRange = 10..125, slots = 8)
            obj(Items.WATER_RUNE, quantityRange = 5..100, slots = 8)
            obj(Items.EARTH_RUNE, quantityRange = 5..75, slots = 8)
            obj(Items.FIRE_RUNE, quantityRange = 5..75, slots = 8)
            obj(Items.MIND_RUNE, quantityRange = 1..50, slots = 8)
            obj(Items.CHAOS_RUNE, quantityRange = 1..10, slots = 4)
            obj(Items.DEATH_RUNE, quantityRange = 1..5, slots = 2)
            obj(Items.BEER, quantity = 1, slots = 4)
            obj(Items.COINS, quantityRange = 1..75, slots = 4)
            obj(Items.UNCUT_SAPPHIRE, quantity = 1, slots = 4)
            obj(Items.UNCUT_EMERALD, quantity = 1, slots = 2)
            obj(Items.UNCUT_RUBY, quantity = 1, slots = 1)
            obj(Items.STEEL_AXE, quantity = 1, slots = 2)
            obj(Items.BLACK_AXE, quantity = 1, slots = 2)
            obj(Items.MITHRIL_AXE, quantity = 1, slots = 1)
            obj(Items.STEEL_PICKAXE, quantity = 1, slots = 2)
            obj(Items.BLACK_PICKAXE, quantity = 1, slots = 2)
            obj(Items.MITHRIL_PICKAXE, quantity = 1, slots = 1)
            obj(Items.IRON_ORE_NOTED, quantityRange = 1..5, slots = 1)
            obj(Items.RUNE_ESSENCE_NOTED, quantityRange = 1..15, slots = 1)
            nothing(slots = 128)
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
            attackSpeed = 6
            respawnDelay = 30
            poisonChance = 0.0
            venomChance = 0.0
        }
        stats {
            hitpoints = 35
            attack = 18
            strength = 22
            defence = 26
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
            attack = Animation.GIANT_ATTACK
            block = Animation.GIANT_HIT
            death = Animation.GIANT_DEATH
        }

        sound {
            attackSound = Sound.GIANT_ATTACK
            blockSound = Sound.GIANT_HIT
            deathSound = Sound.GIANT_DEATH
        }
    }
}