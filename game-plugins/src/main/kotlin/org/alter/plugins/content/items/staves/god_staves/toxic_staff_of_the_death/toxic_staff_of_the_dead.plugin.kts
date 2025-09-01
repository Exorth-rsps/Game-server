package org.alter.plugins.content.items.staves.god_staves.toxic_staff_of_the_death

on_item_equip(Items.TOXIC_STAFF_OF_THE_DEAD) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.TOXIC_STAFF_OF_THE_DEAD) {
    player.setVarp(664, 0)
}
//TO-DO:
//Add special attack