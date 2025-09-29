package org.alter.plugins.content.items.staves.god_staves.staff_of_balance

on_item_equip(Items.STAFF_OF_BALANCE) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.STAFF_OF_BALANCE) {
    player.setVarp(664, 0)
}
//TO-DO:
//Add special attack