package org.alter.plugins.content.items.staves.regular.staff

on_item_equip(Items.STAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.STAFF) {
    player.setVarp(664, 0)
}