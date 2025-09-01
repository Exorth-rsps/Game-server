package org.alter.plugins.content.items.staves.combination.smoke_battlestaff

on_item_equip(Items.SMOKE_BATTLESTAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.SMOKE_BATTLESTAFF) {
    player.setVarp(664, 0)
}
