package org.alter.plugins.content.items.barrows

import org.alter.api.cfg.Items
import org.alter.game.model.entity.Player
import org.alter.game.model.item.Item
import org.alter.game.model.item.ItemAttribute
import org.alter.api.ext.*

/**
 * Handles degrading of Barrows equipment.
 */
object BarrowsDegrade {

    private const val MAX_CHARGES = 1000

    private val SETS = arrayOf(
        intArrayOf(Items.AHRIMS_HOOD, Items.AHRIMS_HOOD_100, Items.AHRIMS_HOOD_75, Items.AHRIMS_HOOD_50, Items.AHRIMS_HOOD_25, Items.AHRIMS_HOOD_0),
        intArrayOf(Items.AHRIMS_ROBETOP, Items.AHRIMS_ROBETOP_100, Items.AHRIMS_ROBETOP_75, Items.AHRIMS_ROBETOP_50, Items.AHRIMS_ROBETOP_25, Items.AHRIMS_ROBETOP_0),
        intArrayOf(Items.AHRIMS_ROBESKIRT, Items.AHRIMS_ROBESKIRT_100, Items.AHRIMS_ROBESKIRT_75, Items.AHRIMS_ROBESKIRT_50, Items.AHRIMS_ROBESKIRT_25, Items.AHRIMS_ROBESKIRT_0),
        intArrayOf(Items.AHRIMS_STAFF, Items.AHRIMS_STAFF_100, Items.AHRIMS_STAFF_75, Items.AHRIMS_STAFF_50, Items.AHRIMS_STAFF_25, Items.AHRIMS_STAFF_0),

        intArrayOf(Items.DHAROKS_HELM, Items.DHAROKS_HELM_100, Items.DHAROKS_HELM_75, Items.DHAROKS_HELM_50, Items.DHAROKS_HELM_25, Items.DHAROKS_HELM_0),
        intArrayOf(Items.DHAROKS_GREATAXE, Items.DHAROKS_GREATAXE_100, Items.DHAROKS_GREATAXE_75, Items.DHAROKS_GREATAXE_50, Items.DHAROKS_GREATAXE_25, Items.DHAROKS_GREATAXE_0),
        intArrayOf(Items.DHAROKS_PLATEBODY, Items.DHAROKS_PLATEBODY_100, Items.DHAROKS_PLATEBODY_75, Items.DHAROKS_PLATEBODY_50, Items.DHAROKS_PLATEBODY_25, Items.DHAROKS_PLATEBODY_0),
        intArrayOf(Items.DHAROKS_PLATELEGS, Items.DHAROKS_PLATELEGS_100, Items.DHAROKS_PLATELEGS_75, Items.DHAROKS_PLATELEGS_50, Items.DHAROKS_PLATELEGS_25, Items.DHAROKS_PLATELEGS_0),

        intArrayOf(Items.GUTHANS_HELM, Items.GUTHANS_HELM_100, Items.GUTHANS_HELM_75, Items.GUTHANS_HELM_50, Items.GUTHANS_HELM_25, Items.GUTHANS_HELM_0),
        intArrayOf(Items.GUTHANS_WARSPEAR, Items.GUTHANS_WARSPEAR_100, Items.GUTHANS_WARSPEAR_75, Items.GUTHANS_WARSPEAR_50, Items.GUTHANS_WARSPEAR_25, Items.GUTHANS_WARSPEAR_0),
        intArrayOf(Items.GUTHANS_PLATEBODY, Items.GUTHANS_PLATEBODY_100, Items.GUTHANS_PLATEBODY_75, Items.GUTHANS_PLATEBODY_50, Items.GUTHANS_PLATEBODY_25, Items.GUTHANS_PLATEBODY_0),
        intArrayOf(Items.GUTHANS_CHAINSKIRT, Items.GUTHANS_CHAINSKIRT_100, Items.GUTHANS_CHAINSKIRT_75, Items.GUTHANS_CHAINSKIRT_50, Items.GUTHANS_CHAINSKIRT_25, Items.GUTHANS_CHAINSKIRT_0),

        intArrayOf(Items.KARILS_COIF, Items.KARILS_COIF_100, Items.KARILS_COIF_75, Items.KARILS_COIF_50, Items.KARILS_COIF_25, Items.KARILS_COIF_0),
        intArrayOf(Items.KARILS_CROSSBOW, Items.KARILS_CROSSBOW_100, Items.KARILS_CROSSBOW_75, Items.KARILS_CROSSBOW_50, Items.KARILS_CROSSBOW_25, Items.KARILS_CROSSBOW_0),
        intArrayOf(Items.KARILS_LEATHERTOP, Items.KARILS_LEATHERTOP_100, Items.KARILS_LEATHERTOP_75, Items.KARILS_LEATHERTOP_50, Items.KARILS_LEATHERTOP_25, Items.KARILS_LEATHERTOP_0),
        intArrayOf(Items.KARILS_LEATHERSKIRT, Items.KARILS_LEATHERSKIRT_100, Items.KARILS_LEATHERSKIRT_75, Items.KARILS_LEATHERSKIRT_50, Items.KARILS_LEATHERSKIRT_25, Items.KARILS_LEATHERSKIRT_0),

        intArrayOf(Items.TORAGS_HELM, Items.TORAGS_HELM_100, Items.TORAGS_HELM_75, Items.TORAGS_HELM_50, Items.TORAGS_HELM_25, Items.TORAGS_HELM_0),
        intArrayOf(Items.TORAGS_HAMMERS, Items.TORAGS_HAMMERS_100, Items.TORAGS_HAMMERS_75, Items.TORAGS_HAMMERS_50, Items.TORAGS_HAMMERS_25, Items.TORAGS_HAMMERS_0),
        intArrayOf(Items.TORAGS_PLATEBODY, Items.TORAGS_PLATEBODY_100, Items.TORAGS_PLATEBODY_75, Items.TORAGS_PLATEBODY_50, Items.TORAGS_PLATEBODY_25, Items.TORAGS_PLATEBODY_0),
        intArrayOf(Items.TORAGS_PLATELEGS, Items.TORAGS_PLATELEGS_100, Items.TORAGS_PLATELEGS_75, Items.TORAGS_PLATELEGS_50, Items.TORAGS_PLATELEGS_25, Items.TORAGS_PLATELEGS_0),

        intArrayOf(Items.VERACS_HELM, Items.VERACS_HELM_100, Items.VERACS_HELM_75, Items.VERACS_HELM_50, Items.VERACS_HELM_25, Items.VERACS_HELM_0),
        intArrayOf(Items.VERACS_FLAIL, Items.VERACS_FLAIL_100, Items.VERACS_FLAIL_75, Items.VERACS_FLAIL_50, Items.VERACS_FLAIL_25, Items.VERACS_FLAIL_0),
        intArrayOf(Items.VERACS_BRASSARD, Items.VERACS_BRASSARD_100, Items.VERACS_BRASSARD_75, Items.VERACS_BRASSARD_50, Items.VERACS_BRASSARD_25, Items.VERACS_BRASSARD_0),
        intArrayOf(Items.VERACS_PLATESKIRT, Items.VERACS_PLATESKIRT_100, Items.VERACS_PLATESKIRT_75, Items.VERACS_PLATESKIRT_50, Items.VERACS_PLATESKIRT_25, Items.VERACS_PLATESKIRT_0)
    )

    private val DEGRADE_CHAIN = buildMap<Int, Int> {
        SETS.forEach { chain ->
            for (i in 0 until chain.size - 1) {
                put(chain[i], chain[i + 1])
            }
        }
    }

    private val BROKEN = SETS.map { it.last() }.toSet()

    fun onEquip(player: Player, slot: Int) {
        if (!player.world.gameContext.barrowsDegrade) return
        val item = player.equipment[slot] ?: return
        if (item.attr[ItemAttribute.DEGRADE] == null) {
            val next = DEGRADE_CHAIN[item.id] ?: return
            val newItem = Item(next, item.amount)
            newItem.putAttr(ItemAttribute.DEGRADE, MAX_CHARGES)
            player.equipment[slot] = newItem
        }
    }

    fun degrade(player: Player) {
        if (!player.world.gameContext.barrowsDegrade) return
        val equip = player.equipment
        for (slot in equip.rawItems.indices) {
            val item = equip[slot] ?: continue
            if (!DEGRADE_CHAIN.containsKey(item.id)) continue
            var charges = item.attr[ItemAttribute.DEGRADE] ?: MAX_CHARGES
            charges--
            if (charges <= 0) {
                val next = DEGRADE_CHAIN[item.id] ?: continue
                val newItem = Item(next)
                if (next in BROKEN) {
                    equip[slot] = null
                    player.inventory.add(newItem, assureFullInsertion = false)
                    player.message("Your ${item.getDef(player.world.definitions).name.lowercase()} has degraded completely.")
                } else {
                    newItem.putAttr(ItemAttribute.DEGRADE, MAX_CHARGES)
                    equip[slot] = newItem
                    player.message("Your ${item.getDef(player.world.definitions).name.lowercase()} degrades.")
                }
            } else {
                item.attr[ItemAttribute.DEGRADE] = charges
            }
        }
    }
}

