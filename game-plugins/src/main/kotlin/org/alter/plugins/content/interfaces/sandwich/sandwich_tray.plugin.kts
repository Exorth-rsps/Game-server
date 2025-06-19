package org.alter.plugins.content.interfaces.sandwich

import org.alter.game.message.impl.ResumePauseButtonMessage

SandwichTray.itemComponents.forEach { component ->
    on_button(interfaceId = SandwichTray.INTERFACE_ID, component = component) {
        player.queues.submitReturnValue(ResumePauseButtonMessage(SandwichTray.INTERFACE_ID, component, -1))
    }
}
