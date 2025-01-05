package org.alter.plugins.content.area.exorth.yanille.chat

arrayOf(Npcs.WIZARD_AKUTHA).forEach { shop ->

    on_npc_option(npc = shop, option = "talk-to", lineOfSightDistance = 1) { player.queue { dialog() } }
    on_npc_option(npc = shop, option = "trade") { open_shop(player) }
}

    suspend fun QueueTask.dialog() {
        chatNpc("Welcome to the Magic Guild Store. <br>Would you like to buy some magic supplies?")
        var continueDialog = true
        while (continueDialog) {
            when (options()) {
                1 -> question_1()
                2 -> {
                    question_2()
                    continueDialog = false
                }
            }
        }
    }

    suspend fun QueueTask.options(): Int = options(
        "Yes please.",
        "No thank you.",
    )

    suspend fun QueueTask.question_1() {
        chatPlayer("Yes please.", animation = 568)
        open_shop(player)
    }

    suspend fun QueueTask.question_2() {
        chatPlayer("No thank you.", animation = 568)
    }

fun open_shop(p: Player) {
    p.openShop("Magic Guild Store")
}