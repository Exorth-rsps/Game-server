package org.alter.plugins.content.items.staves.regular.air_battlestaff

on_item_equip(Items.AIR_BATTLESTAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.AIR_BATTLESTAFF) {
    player.setVarp(664, 0)
}