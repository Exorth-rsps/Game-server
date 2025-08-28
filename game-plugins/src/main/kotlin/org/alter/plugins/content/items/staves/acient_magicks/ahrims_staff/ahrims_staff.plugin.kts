package org.alter.plugins.content.items.staves.acient_magicks.ahrims_staff

on_item_equip(Items.AHRIMS_STAFF) {
    player.setVarp(664, Items.ANCIENT_STAFF)
    player.setVarbit(4070, 1)
    player.setVarbit(358, 20)
}

on_item_unequip(Items.AHRIMS_STAFF) {
    player.setVarp(664, 0)
    player.setVarbit(4070, 0)
    player.setVarbit(358, 0)
}
