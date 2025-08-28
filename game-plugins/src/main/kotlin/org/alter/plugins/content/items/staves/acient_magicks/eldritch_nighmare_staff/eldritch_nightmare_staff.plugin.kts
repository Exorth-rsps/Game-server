package org.alter.plugins.content.items.staves.acient_magicks.eldritch_nighmare_staff

on_item_equip(Items.ELDRITCH_NIGHTMARE_STAFF) {
    player.setVarp(664, Items.ANCIENT_STAFF)
    player.setVarbit(4070, 1)
    player.setVarbit(358, 20)
}

on_item_unequip(Items.ELDRITCH_NIGHTMARE_STAFF) {
    player.setVarp(664, 0)
    player.setVarbit(4070, 0)
    player.setVarbit(358, 0)
}
