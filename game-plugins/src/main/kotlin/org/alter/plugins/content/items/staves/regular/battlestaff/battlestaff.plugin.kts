package org.alter.plugins.content.items.staves.regular.battlestaff

on_item_equip(Items.BATTLESTAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.BATTLESTAFF) {
    player.setVarp(664, 0)
}