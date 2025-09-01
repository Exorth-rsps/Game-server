package org.alter.plugins.content.items.staves.combination.steam_battlestaff

on_item_equip(Items.STEAM_BATTLESTAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.STEAM_BATTLESTAFF) {
    player.setVarp(664, 0)
}
