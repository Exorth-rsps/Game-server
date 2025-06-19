package org.alter.plugins.content.items.xpitems

import org.alter.api.cfg.Items
import org.alter.plugins.content.interfaces.xpreward.XpReward

on_item_option(item = Items.LAMP, option = "Rub") {
    XpReward.open(player, Items.LAMP, 10)
}

on_item_option(item = Items.BOOK_OF_KNOWLEDGE, option = "Read") {
    XpReward.open(player, Items.BOOK_OF_KNOWLEDGE, 15)
}

on_item_option(item = Items.ANTIQUE_LAMP, option = "Rub") {
    XpReward.open(player, Items.ANTIQUE_LAMP, 20)
}
