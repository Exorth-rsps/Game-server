package org.alter.plugins.content.npcs.human;

import org.alter.plugins.content.drops.DropTableFactory
import org.alter.game.model.combat.AttackStyle
import org.alter.game.model.combat.CombatClass
import org.alter.game.model.combat.CombatStyle
import org.alter.plugins.content.combat.*
import org.alter.plugins.content.combat.formula.RangedCombatFormula
import org.alter.plugins.content.combat.strategy.RangedCombatStrategy
import org.alter.api.ProjectileType
import org.alter.api.cfg.Graphic

val ids = intArrayOf(
    Npcs.ARCHER_3301
)

val table = DropTableFactory
val droptable =
    table.build {
        guaranteed {
            obj(Items.BONES, quantity = 1)
        }
        main {
            total(512)
            obj(Items.STEEL_ARROW, quantityRange = 5..100, slots = 4)
            obj(Items.IRON_ARROW, quantityRange = 5..200, slots = 4)
            obj(Items.SHORTBOW, quantity = 1, slots = 4)
            obj(Items.OAK_SHORTBOW, quantity = 1, slots = 4)
            obj(Items.STEEL_SWORD, quantity = 1, slots = 4)
            obj(Items.COINS_995, quantity = 25, slots = 16)
            nothing(slots = 64)
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
            respawnDelay = 25
            poisonChance = 0.0
            venomChance = 0.0
        }
        stats {
            hitpoints = 50
            attack = 20
            strength = 20
            defence = 20
            magic = 1
            ranged = 40
        }

        bonuses {
            defenceStab = 18
            defenceSlash = 23
            defenceCrush = 27
            defenceMagic = 10
            defenceRanged = 19
        }


        anims {
            attack = Animation.HUMAN_BOW_ATTACK
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

on_npc_combat(Npcs.ARCHER_3301) {
    npc.queue {
        combat(this)
    }
}

suspend fun combat(it: QueueTask) {
    val npc = it.npc
    var target = npc.getCombatTarget() ?: return

    while (npc.canEngageCombat(target)) {
        npc.facePawn(target)
        if (npc.moveToAttackRange(it, target, distance = 6, projectile = true) && npc.isAttackDelayReady()) {
            rangedAttack(npc, target)
            npc.postAttackLogic(target)
        }
        it.wait(1)
        target = npc.getCombatTarget() ?: break
    }

    npc.resetFacePawn()
    npc.removeCombatTarget()
}

fun rangedAttack(npc: Npc, target: Pawn) {
    val minHit = 2
    val maxHit = 8
    val projectile = npc.createProjectile(target, gfx = Graphic.IRON_ARROW_PROJECTILE, type = ProjectileType.ARROW)
    npc.prepareAttack(CombatClass.RANGED, CombatStyle.RANGED, AttackStyle.ACCURATE)
    npc.animate(Animation.HUMAN_BOW_ATTACK)
    world.spawn(projectile)
    val hitDelay = RangedCombatStrategy.getHitDelay(npc.getFrontFacingTile(target), target.getCentreTile()) - 1
    if (RangedCombatFormula.getAccuracy(npc, target) >= world.randomDouble()) {
        target.hit(damage = world.random(minHit..maxHit), type = HitType.HIT, delay = hitDelay)
    } else {
        target.hit(damage = 0, type = HitType.BLOCK, delay = hitDelay)
    }
}
