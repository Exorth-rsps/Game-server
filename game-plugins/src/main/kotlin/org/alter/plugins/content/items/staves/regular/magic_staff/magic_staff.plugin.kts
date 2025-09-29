package org.alter.plugins.content.items.staves.regular.magic_staff

on_item_equip(Items.MAGIC_STAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.MAGIC_STAFF) {
    player.setVarp(664, 0)
}