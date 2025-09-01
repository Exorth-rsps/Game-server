package org.alter.plugins.content.items.staves.combination.mystic_dust_staff

on_item_equip(Items.MYSTIC_DUST_STAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.MYSTIC_DUST_STAFF) {
    player.setVarp(664, 0)
}
