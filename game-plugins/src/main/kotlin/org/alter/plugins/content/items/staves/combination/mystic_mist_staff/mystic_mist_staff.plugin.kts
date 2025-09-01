package org.alter.plugins.content.items.staves.combination.mystic_mist_staff

on_item_equip(Items.MYSTIC_MIST_STAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.MYSTIC_MIST_STAFF) {
    player.setVarp(664, 0)
}
