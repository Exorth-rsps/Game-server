package org.alter.plugins.content.items.staves.regular.earth_battlestaff

on_item_equip(Items.EARTH_BATTLESTAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.EARTH_BATTLESTAFF) {
    player.setVarp(664, 0)
}