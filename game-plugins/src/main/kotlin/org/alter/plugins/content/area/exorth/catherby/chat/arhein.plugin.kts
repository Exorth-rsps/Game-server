package org.alter.plugins.content.areas.exorth.catherby.chat

arrayOf(Npcs.ARHEIN).forEach { shop ->

    on_npc_option(npc = shop, option = "talk-to") { player.queue { dialog(this) } }

    on_npc_option(npc = shop, option = "trade") { open_shop(player) }
}

suspend fun dialog(it: QueueTask) {
    it.chatNpc("Hello! <br>Would you like to trade?", animation = 567)
    when (it.options("Sure.", "No thanks.")) {
        1 -> open_shop(it.player)
        2 -> it.chatPlayer("No thanks.", animation = 588)
    }
}

fun open_shop(p: Player) {
    p.openShop("Arhein Store")
}