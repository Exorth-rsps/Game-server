package org.alter.plugins.content.items.staves.combination.mist_battlestaff

on_item_equip(Items.MIST_BATTLESTAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.MIST_BATTLESTAFF) {
    player.setVarp(664, 0)
}
