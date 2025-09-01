package org.alter.plugins.content.items.staves.regular.mystic_fire_staff

on_item_equip(Items.MYSTIC_FIRE_STAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.MYSTIC_FIRE_STAFF) {
    player.setVarp(664, 0)
}