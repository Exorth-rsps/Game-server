package org.alter.plugins.content.combat

import org.alter.game.action.PawnPathAction
import org.alter.game.model.attr.COMBAT_TARGET_FOCUS_ATTR
import org.alter.game.model.attr.FACING_PAWN_ATTR
import org.alter.game.model.attr.INTERACTING_PLAYER_ATTR
import org.alter.game.model.timer.FROZEN_TIMER
import org.alter.game.model.timer.STUN_TIMER
import org.alter.plugins.content.combat.specialattack.SpecialAttacks
import org.alter.plugins.content.combat.strategy.magic.CombatSpell
import org.alter.plugins.content.interfaces.attack.AttackTab

set_combat_logic {
    pawn.attr[COMBAT_TARGET_FOCUS_ATTR]?.get()?.let { target ->
        pawn.facePawn(target)
    }

    pawn.queue {
        while (true) {
            if (!cycle(this)) {
                break
            }
            wait(1)
        }
    }
}

on_player_option("Attack") {
    val target = pawn.attr[INTERACTING_PLAYER_ATTR]?.get() ?: return@on_player_option
    player.attack(target)
}

suspend fun cycle(it: QueueTask): Boolean {
    val pawn = it.pawn
    val target = pawn.attr[COMBAT_TARGET_FOCUS_ATTR]?.get() ?: return false

    if (!pawn.lock.canAttack()) {
        Combat.reset(pawn)
        return false
    }

    pawn.facePawn(target)

    if (!Combat.canEngage(pawn, target)) {
        Combat.reset(pawn)
        pawn.resetFacePawn()
        return false
    }
    if (pawn is Player) {
        pawn.setVarp(Combat.PRIORITY_PID_VARP, target.index)
        if (!pawn.attr.has(Combat.CASTING_SPELL) && pawn.getVarbit(Combat.SELECTED_AUTOCAST_VARBIT) != 0) {
            val spell = CombatSpell.values.firstOrNull { it.autoCastId == pawn.getVarbit(Combat.SELECTED_AUTOCAST_VARBIT) }
            if (spell != null) {
                pawn.attr[Combat.CASTING_SPELL] = spell
            }
        }
    }

    val strategy = CombatConfigs.getCombatStrategy(pawn)
    val attackRange = strategy.getAttackRange(pawn)
    val pathFound = PawnPathAction.walkTo(it, pawn, target, interactionRange = attackRange, lineOfSight = false)
    if (target != pawn.attr[FACING_PAWN_ATTR]?.get()) {
        return false
    }

    if (!pathFound) {
        pawn.stopMovement()
        if (pawn.entityType.isNpc) {
            /**
             * Npcs will keep trying to find a path to engage in combat.
             */
            return true
        }
        if (pawn is Player) {
            when {
                pawn.timers.has(FROZEN_TIMER) -> pawn.message(Entity.MAGIC_STOPS_YOU_FROM_MOVING)
                pawn.timers.has(STUN_TIMER) -> pawn.message(Entity.YOURE_STUNNED)
                else -> pawn.message(Entity.YOU_CANT_REACH_THAT)
            }
            pawn.clearMapFlag()
        }
        pawn.resetFacePawn()
        Combat.reset(pawn)
        return false
    }

    pawn.stopMovement()

    //if (pawn is Player && pawn.getEquipment(EquipmentType.WEAPON) != null && world.plugins.executeWeaponCombatLogic(pawn, pawn.getEquipment(EquipmentType.WEAPON)!!.id)) else

    if (Combat.isAttackDelayReady(pawn)) {
        if (Combat.canAttack(pawn, target, strategy)) {
            if (pawn is Player && AttackTab.isSpecialEnabled(pawn) && pawn.getEquipment(EquipmentType.WEAPON) != null) {
                AttackTab.disableSpecial(pawn)
                if (SpecialAttacks.execute(pawn, target, world)) {
                    Combat.postAttack(pawn, target)
                    return true
                }
                pawn.message("You don't have enough power left.")
            }

            if (pawn is Player && world.plugins.hasExecItemCmbtLogic(pawn)) {
                pawn.equipment.rawItems.forEach {
                    it?.let {
                        world.plugins.executeItemCombatLogic(pawn, it.id)
                    }
                }
            } else {
                if (pawn is Player) {
                    //pawn.message("There is no config for this npc, please report.")
                }
                strategy.attack(pawn, target)
                Combat.postAttack(pawn, target)
            }
        } else {
            Combat.reset(pawn)
            return false
        }
    }
    return true
}

