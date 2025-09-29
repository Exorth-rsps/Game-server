package org.alter.plugins.content.items.staves.regular.staff_of_earth

on_item_equip(Items.STAFF_OF_EARTH) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.STAFF_OF_EARTH) {
    player.setVarp(664, 0)
}