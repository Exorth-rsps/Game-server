package org.alter.plugins.content.items.staves.regular.white_magic_staff

on_item_equip(Items.WHITE_MAGIC_STAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.WHITE_MAGIC_STAFF) {
    player.setVarp(664, 0)
}