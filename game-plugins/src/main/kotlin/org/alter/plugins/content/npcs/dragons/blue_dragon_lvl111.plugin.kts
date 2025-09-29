package org.alter.plugins.content.npcs.dragons

import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.BLUE_DRAGON,
)
val table = DropTableFactory
val droptable =
    table.build {
        guaranteed {
            obj(Items.DRAGON_BONES, quantity = 1)
            obj(Items.BLUE_DRAGONHIDE, quantity = 1)
        }
        table("herbs-noted") {
            total(128)
            obj(Items.GRIMY_LANTADYME_NOTED, quantityRange = 3..8, 12)
            obj(Items.GRIMY_RANARR_WEED_NOTED, quantityRange = 3..8, 10)
            obj(Items.GRIMY_GUAM_LEAF_NOTED, quantityRange = 3..8, 20)
            obj(Items.GRIMY_MARRENTILL_NOTED, quantityRange = 3..8, 24)
            obj(Items.GRIMY_HARRALANDER_NOTED, quantityRange = 3..8, 22)
        }
        table("gems") {
            total(128)
            obj(Items.UNCUT_SAPPHIRE, quantity = 1, 90)
            obj(Items.UNCUT_EMERALD, quantity = 1, 80)
        }
        table("second") {
            total(128)
            obj(Items.UNCUT_RUBY, quantity = 1, 75)
            obj(Items.LIMPWURT_ROOT_NOTED, quantityRange = 2..6, 40)
            obj(Items.RED_SPIDERS_EGGS_NOTED, quantityRange = 2..6, 34)
        }
        table("main") {
            total(128)
            obj(Items.MITHRIL_SWORD, quantity = 1, 45)
            obj(Items.ADAMANT_SWORD, quantity = 1, 40)
            obj(Items.RUNE_SWORD, quantity = 1, 28)
        }
        table("rare") {
            total(128)
            obj(Items.WHITE_BERRIES_NOTED, quantityRange = 2..6, 22)
            obj(Items.SNAPE_GRASS_NOTED, quantityRange = 2..6, 28)
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
        species {
            + NpcSpecies.DRAGON
            + NpcSpecies.BASIC_DRAGON
        }
        configs {
            attackSpeed = 4
            respawnDelay = 30
            poisonChance = 0.0
            venomChance = 0.0
        }
        stats {
            hitpoints = 105
            attack = 95
            strength = 95
            defence = 95
            magic = 1
            ranged = 1
        }

        bonuses {
            defenceStab = 10
            defenceSlash = 70
            defenceCrush = 70
            defenceMagic = 60
            defenceRanged = 50
        }

        anims {
            attack = Animation.DRAGON_ATTACK
            block = Animation.DRAGON_HIT
            death = Animation.DRAGON_DEATH
        }

        sound {
            attackSound = Sound.DRAGON_ATTACK
            blockSound = Sound.DRAGON_HIT
            deathSound = Sound.DRAGON_DEATH
        }

    }
}
