package org.alter.plugins.content.items.staves.combination.mystic_steam_staff

on_item_equip(Items.MYSTIC_STEAM_STAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.MYSTIC_STEAM_STAFF) {
    player.setVarp(664, 0)
}
