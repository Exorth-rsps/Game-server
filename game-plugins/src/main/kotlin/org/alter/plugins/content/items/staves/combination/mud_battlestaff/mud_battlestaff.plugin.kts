package org.alter.plugins.content.items.staves.combination.mud_battlestaff

on_item_equip(Items.MUD_BATTLESTAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.MUD_BATTLESTAFF) {
    player.setVarp(664, 0)
}
