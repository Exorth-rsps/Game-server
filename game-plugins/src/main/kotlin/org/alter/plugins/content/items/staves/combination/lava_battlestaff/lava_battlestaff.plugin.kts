package org.alter.plugins.content.items.staves.combination.lava_battlestaff

on_item_equip(Items.LAVA_BATTLESTAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.LAVA_BATTLESTAFF) {
    player.setVarp(664, 0)
}
