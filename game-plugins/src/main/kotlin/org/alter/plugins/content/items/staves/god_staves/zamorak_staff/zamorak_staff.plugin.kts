package org.alter.plugins.content.items.staves.god_staves.zamorak_staff

on_item_equip(Items.ZAMORAK_STAFF) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.ZAMORAK_STAFF) {
    player.setVarp(664, 0)
}
//TO-DO:
//Add godspells