package org.alter.plugins.content.items.barrows

import org.alter.api.Skills
import org.alter.api.cfg.Items
import org.alter.api.ext.hasEquipped
import org.alter.api.ext.heal
import org.alter.api.ext.sendRunEnergy
import org.alter.game.model.combat.PawnHit
import org.alter.game.model.entity.Pawn
import org.alter.game.model.entity.Player
import org.alter.game.model.item.EquipmentType

/**
 * Handles special set effects for Barrows equipment.
 */
object BarrowsEffects {

    fun onHit(attacker: Pawn, target: Pawn, hit: PawnHit) {
        if (attacker !is Player || !hit.landed()) return

        if (isWearingAhrim(attacker) && target is Player && attacker.world.chance(1, 4)) {
            target.getSkills().alterCurrentLevel(Skills.STRENGTH, -5)
            target.message("You feel weakened!")
        }

        if (isWearingGuthan(attacker) && attacker.world.chance(1, 4)) {
            val heal = hit.hit.hitmarks.sumOf { it.damage }
            if (heal > 0) {
                val cap = if (attacker.hasEquipped(EquipmentType.AMULET, Items.AMULET_OF_THE_DAMNED_FULL)) 10 else 0
                attacker.heal(heal, cap)
            }
        }

        if (isWearingKaril(attacker) && target is Player && attacker.world.chance(1, 4)) {
            target.getSkills().alterCurrentLevel(Skills.AGILITY, -2)
            target.message("You feel your legs weaken!")
        }

        if (isWearingTorag(attacker) && target is Player && attacker.world.chance(1, 4)) {
            target.runEnergy = (target.runEnergy - 200.0).coerceAtLeast(0.0)
            target.sendRunEnergy(target.runEnergy.toInt())
            target.message("You feel drained of energy!")
        }
    }

    fun modifyAccuracy(attacker: Pawn, target: Pawn, accuracy: Double): Double {
        if (attacker !is Player) return accuracy
        if (isWearingVerac(attacker) && attacker.world.chance(1, 4)) {
            return 1.0
        }
        return accuracy
    }

    fun modifyMaxHit(attacker: Pawn, target: Pawn, maxHit: Int): Int {
        if (attacker !is Player) return maxHit
        var hit = maxHit
        if (isWearingDharok(attacker)) {
            val missing = attacker.getMaxHp() - attacker.getCurrentHp()
            hit += (maxHit * 0.005 * missing).toInt()
        }
        return hit
    }

    private fun isWearingAhrim(player: Player): Boolean {
        return player.hasEquipped(EquipmentType.HEAD, Items.AHRIMS_HOOD, Items.AHRIMS_HOOD_25, Items.AHRIMS_HOOD_50, Items.AHRIMS_HOOD_75, Items.AHRIMS_HOOD_100)
                && player.hasEquipped(EquipmentType.WEAPON, Items.AHRIMS_STAFF, Items.AHRIMS_STAFF_25, Items.AHRIMS_STAFF_50, Items.AHRIMS_STAFF_75, Items.AHRIMS_STAFF_100)
                && player.hasEquipped(EquipmentType.CHEST, Items.AHRIMS_ROBETOP, Items.AHRIMS_ROBETOP_25, Items.AHRIMS_ROBETOP_50, Items.AHRIMS_ROBETOP_75, Items.AHRIMS_ROBETOP_100)
                && player.hasEquipped(EquipmentType.LEGS, Items.AHRIMS_ROBESKIRT, Items.AHRIMS_ROBESKIRT_25, Items.AHRIMS_ROBESKIRT_50, Items.AHRIMS_ROBESKIRT_75, Items.AHRIMS_ROBESKIRT_100)
    }

    private fun isWearingGuthan(player: Player): Boolean {
        return player.hasEquipped(EquipmentType.HEAD, Items.GUTHANS_HELM, Items.GUTHANS_HELM_25, Items.GUTHANS_HELM_50, Items.GUTHANS_HELM_75, Items.GUTHANS_HELM_100)
                && player.hasEquipped(EquipmentType.WEAPON, Items.GUTHANS_WARSPEAR, Items.GUTHANS_WARSPEAR_25, Items.GUTHANS_WARSPEAR_50, Items.GUTHANS_WARSPEAR_75, Items.GUTHANS_WARSPEAR_100)
                && player.hasEquipped(EquipmentType.CHEST, Items.GUTHANS_PLATEBODY, Items.GUTHANS_PLATEBODY_25, Items.GUTHANS_PLATEBODY_50, Items.GUTHANS_PLATEBODY_75, Items.GUTHANS_PLATEBODY_100)
                && player.hasEquipped(EquipmentType.LEGS, Items.GUTHANS_CHAINSKIRT, Items.GUTHANS_CHAINSKIRT_25, Items.GUTHANS_CHAINSKIRT_50, Items.GUTHANS_CHAINSKIRT_75, Items.GUTHANS_CHAINSKIRT_100)
    }

    private fun isWearingKaril(player: Player): Boolean {
        return player.hasEquipped(EquipmentType.HEAD, Items.KARILS_COIF, Items.KARILS_COIF_25, Items.KARILS_COIF_50, Items.KARILS_COIF_75, Items.KARILS_COIF_100)
                && player.hasEquipped(EquipmentType.WEAPON, Items.KARILS_CROSSBOW, Items.KARILS_CROSSBOW_25, Items.KARILS_CROSSBOW_50, Items.KARILS_CROSSBOW_75, Items.KARILS_CROSSBOW_100)
                && player.hasEquipped(EquipmentType.CHEST, Items.KARILS_LEATHERTOP, Items.KARILS_LEATHERTOP_25, Items.KARILS_LEATHERTOP_50, Items.KARILS_LEATHERTOP_75, Items.KARILS_LEATHERTOP_100)
                && player.hasEquipped(EquipmentType.LEGS, Items.KARILS_LEATHERSKIRT, Items.KARILS_LEATHERSKIRT_25, Items.KARILS_LEATHERSKIRT_50, Items.KARILS_LEATHERSKIRT_75, Items.KARILS_LEATHERSKIRT_100)
    }

    private fun isWearingTorag(player: Player): Boolean {
        return player.hasEquipped(EquipmentType.HEAD, Items.TORAGS_HELM, Items.TORAGS_HELM_25, Items.TORAGS_HELM_50, Items.TORAGS_HELM_75, Items.TORAGS_HELM_100)
                && player.hasEquipped(EquipmentType.WEAPON, Items.TORAGS_HAMMERS, Items.TORAGS_HAMMERS_25, Items.TORAGS_HAMMERS_50, Items.TORAGS_HAMMERS_75, Items.TORAGS_HAMMERS_100)
                && player.hasEquipped(EquipmentType.CHEST, Items.TORAGS_PLATEBODY, Items.TORAGS_PLATEBODY_25, Items.TORAGS_PLATEBODY_50, Items.TORAGS_PLATEBODY_75, Items.TORAGS_PLATEBODY_100)
                && player.hasEquipped(EquipmentType.LEGS, Items.TORAGS_PLATELEGS, Items.TORAGS_PLATELEGS_25, Items.TORAGS_PLATELEGS_50, Items.TORAGS_PLATELEGS_75, Items.TORAGS_PLATELEGS_100)
    }

    private fun isWearingDharok(player: Player): Boolean {
        return player.hasEquipped(EquipmentType.HEAD, Items.DHAROKS_HELM, Items.DHAROKS_HELM_25, Items.DHAROKS_HELM_50, Items.DHAROKS_HELM_75, Items.DHAROKS_HELM_100)
                && player.hasEquipped(EquipmentType.WEAPON, Items.DHAROKS_GREATAXE, Items.DHAROKS_GREATAXE_25, Items.DHAROKS_GREATAXE_50, Items.DHAROKS_GREATAXE_75, Items.DHAROKS_GREATAXE_100)
                && player.hasEquipped(EquipmentType.CHEST, Items.DHAROKS_PLATEBODY, Items.DHAROKS_PLATEBODY_25, Items.DHAROKS_PLATEBODY_50, Items.DHAROKS_PLATEBODY_75, Items.DHAROKS_PLATEBODY_100)
                && player.hasEquipped(EquipmentType.LEGS, Items.DHAROKS_PLATELEGS, Items.DHAROKS_PLATELEGS_25, Items.DHAROKS_PLATELEGS_50, Items.DHAROKS_PLATELEGS_75, Items.DHAROKS_PLATELEGS_100)
    }

    private fun isWearingVerac(player: Player): Boolean {
        return player.hasEquipped(EquipmentType.HEAD, Items.VERACS_HELM, Items.VERACS_HELM_25, Items.VERACS_HELM_50, Items.VERACS_HELM_75, Items.VERACS_HELM_100)
                && player.hasEquipped(EquipmentType.WEAPON, Items.VERACS_FLAIL, Items.VERACS_FLAIL_25, Items.VERACS_FLAIL_50, Items.VERACS_FLAIL_75, Items.VERACS_FLAIL_100)
                && player.hasEquipped(EquipmentType.CHEST, Items.VERACS_BRASSARD, Items.VERACS_BRASSARD_25, Items.VERACS_BRASSARD_50, Items.VERACS_BRASSARD_75, Items.VERACS_BRASSARD_100)
                && player.hasEquipped(EquipmentType.LEGS, Items.VERACS_PLATESKIRT, Items.VERACS_PLATESKIRT_25, Items.VERACS_PLATESKIRT_50, Items.VERACS_PLATESKIRT_75, Items.VERACS_PLATESKIRT_100)
    }
}
