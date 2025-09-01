package org.alter.plugins.content.items.staves.regular.fire_battlestaff

on_item_equip(Items.FIRE_BATTLESTAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.FIRE_BATTLESTAFF) {
    player.setVarp(664, 0)
}