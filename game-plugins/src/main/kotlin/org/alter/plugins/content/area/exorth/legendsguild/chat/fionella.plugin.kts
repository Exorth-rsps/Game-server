package org.alter.plugins.content.areas.exorth.legendsguild.chat

arrayOf(Npcs.FIONELLA).forEach { shop ->

    on_npc_option(npc = shop, option = "talk-to") { player.queue { dialog(this) } }

    on_npc_option(npc = shop, option = "trade") { open_shop(player) }
}

suspend fun dialog(it: QueueTask) {
    it.chatNpc("Can I help you at all?", animation = 567)
    when (it.options("Yes please. What are you selling?", "No thanks.")) {
        1 -> open_shop(it.player)
        2 -> it.chatPlayer("No thanks.", animation = 588)
    }
}

fun open_shop(p: Player) {
    p.openShop("Legends Guild General Store")
}