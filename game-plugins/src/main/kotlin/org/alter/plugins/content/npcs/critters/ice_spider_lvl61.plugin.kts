package org.alter.plugins.content.npcs.critters;

import org.alter.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.ICE_SPIDER
)

val table = DropTableFactory
val droptable =
    table.build {
        main {
            total(512)
            obj(Items.WHITE_BERRIES, quantity = 1, slots = 4)
            obj(Items.LIMPWURT_ROOT, quantity = 1, slots = 4)
            obj(Items.EYE_OF_NEWT, quantity = 1, slots = 4)
            obj(Items.WILLOW_ROOTS, quantity = 1, slots = 1)
            obj(Items.NIHIL_DUST, quantity = 1, slots = 1)
            obj(Items.GOAT_HORN_DUST, quantity = 1, slots = 1)
            obj(Items.SNAPE_GRASS, quantity = 1, slots = 1)
            obj(Items.WINE_OF_ZAMORAK, quantity = 1, slots = 1)
            obj(Items.YEW_ROOTS, quantity = 1, slots = 1)
            obj(Items.MAGIC_ROOTS, quantity = 1, slots = 1)
            obj(Items.GOAT_HORN_DUST, quantity = 1, slots = 1)
            obj(Items.POTATO_CACTUS, quantity = 1, slots = 1)
            obj(Items.ANCIENT_ESSENCE, quantity = 1, slots = 1)
            obj(Items.GORAK_CLAW_POWDER, quantity = 1, slots = 1)
            obj(Items.CRUSHED_NEST, quantity = 1, slots = 1)
            obj(Items.JANGERBERRIES, quantity = 1, slots = 1)
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
            hitpoints = 65
            attack = 50
            strength = 55
            defence = 43
            magic = 1
            ranged = 1
        }

        bonuses {
            defenceStab = 20
            defenceSlash = 17
            defenceCrush = 17
            defenceMagic = 13
            defenceRanged = 13
        }
         aggro {
             radius = 8
             searchDelay = 1

        }


        anims {
            attack = Animation.GIANT_SPIDER_ATTACK
            block = Animation.GIANT_SPIDER_DEFEND
            death = Animation.GIANT_SPIDER_DEATH
        }

        sound {
            attackSound = Sound.BIG_SPIDER_ATTACK
            blockSound = Sound.BIG_SPIDER_HIT
            deathSound = Sound.BIG_SPIDER_DEATH
        }
    }
}