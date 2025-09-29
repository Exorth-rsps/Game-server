package org.alter.plugins.content.items.staves.combination.dust_battlestaff

on_item_equip(Items.DUST_BATTLESTAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.DUST_BATTLESTAFF) {
    player.setVarp(664, 0)
}
