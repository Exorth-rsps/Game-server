package org.alter.plugins.content.items.staves.acient_magicks.kodai_wand

on_item_equip(Items.KODAI_WAND) {
    player.setVarp(664, Items.ANCIENT_STAFF)
    player.setVarbit(4070, 1)
    player.setVarbit(358, 20)
}

on_item_unequip(Items.KODAI_WAND) {
    player.setVarp(664, 0)
    player.setVarbit(4070, 0)
    player.setVarbit(358, 0)
}
