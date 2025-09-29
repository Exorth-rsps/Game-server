package org.alter.plugins.content.interfaces.sandwich

import org.alter.game.fs.def.ItemDef
import org.alter.game.message.impl.ResumePauseButtonMessage
import org.alter.game.model.queue.QueueTask
import org.alter.api.InterfaceDestination
import org.alter.api.ext.InterfaceEvent
import org.alter.api.cfg.Items
import org.alter.api.ext.*

object SandwichTray {
    const val INTERFACE_ID = 297

    private const val KEY_COMPONENT = 13
    private const val REFRESH_COMPONENT = 2

    val componentToItem = mapOf(
        6 to Items.BAGUETTE,
        7 to Items.TRIANGLE_SANDWICH,
        8 to Items.SQUARE_SANDWICH,
        9 to Items.ROLL,
        10 to Items.MEAT_PIE,
        11 to Items.KEBAB,
        12 to Items.CHOCOLATE_BAR
    )

    val itemComponents: IntArray = componentToItem.keys.toIntArray()

    val items: IntArray = componentToItem.values.toIntArray()

    suspend fun chooseItem(task: QueueTask, requestItem: Int): Boolean {
        val player = task.player
        player.setInterfaceUnderlay(-1, -1)
        player.openInterface(INTERFACE_ID, InterfaceDestination.MAIN_SCREEN)
        player.setComponentText(INTERFACE_ID, KEY_COMPONENT, "")
        val name = player.world.definitions.get(ItemDef::class.java, requestItem).name.lowercase()
        player.setComponentText(INTERFACE_ID, REFRESH_COMPONENT, "Please select the $name.")

        componentToItem.keys.forEach { comp ->
            player.setInterfaceEvents(INTERFACE_ID, component = comp, range = 0..0, setting = InterfaceEvent.ClickOp1)
        }

        task.waitReturnValue()
        val msg = task.requestReturnValue as? ResumePauseButtonMessage
        player.closeInterface(INTERFACE_ID)
        val component = msg?.component ?: return false
        val chosen = componentToItem[component] ?: return false
        return chosen == requestItem
    }
}

