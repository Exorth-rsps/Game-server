package org.alter.plugins.content.npcs.critters;

import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.CAVE_CRAWLER_406
)

val table = DropTableFactory
val droptable =
    table.build {
        table("main") {
            total(128)
            obj(Items.BRONZE_BOOTS, quantity = 1, 4)
            obj(Items.NATURE_RUNE, quantityRange = 5..7, 15)
            obj(Items.FIRE_RUNE, quantityRange = 15..20, 15)
        }
        table("herbs") {
            total(128)
            obj(Items.GRIMY_GUAM_LEAF, quantity = 1, 15)
            obj(Items.GRIMY_MARRENTILL, quantity = 1, 15)
            obj(Items.GRIMY_TARROMIN, quantity = 1, 15)
            obj(Items.GRIMY_RANARR_WEED, quantity = 1, 15)
            obj(Items.GRIMY_IRIT_LEAF, quantity = 1, 15)
            obj(Items.GRIMY_AVANTOE, quantity = 1, 15)
        }
        table("second") {
            total(128)
            obj(Items.WHITE_BERRIES, quantity = 1, 15)
            obj(Items.UNICORN_HORN_DUST, quantity = 1, 15)
            obj(Items.SNAPE_GRASS, quantity = 1, 15)
            obj(Items.LIMPWURT_ROOT, quantity = 1, 15)
            obj(Items.EYE_OF_NEWT, quantity = 1, 15)
            obj(Items.RED_SPIDERS_EGGS, quantity = 1, 15)
        }
        table("coins") {
            total(128)
            obj(Items.COINS, quantityRange = 25..50, 6)
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
            hitpoints = 22
            attack = 18
            strength = 18
            defence = 18
            magic = 1
            ranged = 1
        }

        bonuses {
            defenceStab = 10
            defenceSlash = 10
            defenceCrush = 5
            defenceMagic = 5
            defenceRanged = 10
        }

        anims {
            attack = Animation.CAVE_CRAWLER_ATTACK
            block = Animation.CAVE_CRAWLER_HIT
            death = Animation.CAVE_CRAWLER_DEATH
        }

        sound {
            attackSound = Sound.CAVE_CRAWLER_ATTACK
            blockSound = Sound.CAVE_CRAWLER_HIT
            deathSound = Sound.CAVE_CRAWLER_DEATH
        }
    }
}