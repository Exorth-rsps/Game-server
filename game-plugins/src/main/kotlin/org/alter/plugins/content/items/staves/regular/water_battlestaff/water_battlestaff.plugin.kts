package org.alter.plugins.content.items.staves.regular.water_battlestaff

on_item_equip(Items.WATER_BATTLESTAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.WATER_BATTLESTAFF) {
    player.setVarp(664, 0)
}