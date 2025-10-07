package org.alter.plugins.content.npcs.animals;

import org.alter.plugins.content.combat.isBeingAttacked
import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.GORAK
)

val table = DropTableFactory
val droptable =
    table.build {
        guaranteed {
            obj(Items.BONES, quantity = 1)
        }
        table("rare") {
            total(128)
            obj(Items.GORAK_CLAWS, quantity = 1, 1)
            obj(Items.RING_OF_LIFE, quantity = 1, 1)
            nothing(126)
        }
        table("herbs") {
            total(128)
            obj(Items.GRIMY_GUAM_LEAF, quantityRange = 1..5, 25)
            obj(Items.GRIMY_MARRENTILL, quantityRange = 1..5, 25)
            obj(Items.GRIMY_TARROMIN, quantityRange = 1..3, 25)
            obj(Items.GRIMY_HARRALANDER, quantityRange = 1..3, 25)
            obj(Items.GRIMY_RANARR_WEED, quantityRange = 1..3, 25)
            obj(Items.GRIMY_IRIT_LEAF, quantityRange = 1..3, 25)
            nothing(1)
        }
        table("second") {
            total(128)
            obj(Items.GRIMY_AVANTOE, quantityRange = 1..3, 25)
            obj(Items.GRIMY_KWUARM, quantityRange = 1..3, 25)
            obj(Items.GRIMY_CADANTINE, quantityRange = 1..3, 25)
            obj(Items.GRIMY_DWARF_WEED, quantityRange = 1..3, 25)
            nothing(28)
        }
        table("coins") {
            total(128)
            obj(Items.COINS, quantityRange = 100..2215, 15)
            nothing(113)
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
            respawnDelay = 50
            poisonChance = 0.0
            venomChance = 0.0
        }
        stats {
            hitpoints = 112
            attack = 130
            strength = 130
            defence = 131
            magic = 1
            ranged = 1
        }

        bonuses {
            defenceStab = 10
            defenceSlash = 10
            defenceCrush = 0
            defenceMagic = 0
            defenceRanged = 10
        }

        aggro {
            radius = 5
            searchDelay = 2
            aggroTimer = 100
        }

        anims {
            attack = Animation.GORAK_ATTACK
            block = Animation.GORAK_DEFEND
            death = Animation.GORAK_DEATH
        }

        sound {
            attackSound = Sound.GORAK_ATTACK
            blockSound = Sound.GORAK_HIT
            deathSound = Sound.GORAK_DEATH
        }
    }
}