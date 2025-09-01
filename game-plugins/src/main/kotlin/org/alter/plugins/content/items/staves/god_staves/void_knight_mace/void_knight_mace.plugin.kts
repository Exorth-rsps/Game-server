package org.alter.plugins.content.items.staves.god_staves.void_knight_mace

on_item_equip(Items.VOID_KNIGHT_MACE) {
    player.setVarp(664, -1)
}

on_item_unequip(Items.VOID_KNIGHT_MACE) {
    player.setVarp(664, 0)
}
//TO-DO:
//Add claws of guthix and crumble undead
//Change modern to only wave and surge spells