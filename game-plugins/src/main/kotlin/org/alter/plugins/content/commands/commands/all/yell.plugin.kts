package gg.rsmod.plugins.content.commands

import org.alter.api.ChatMessageType
import org.alter.game.model.entity.Player
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// Houdt voor elke speler het tijdstip van de laatste 'yell' bij
private val lastYellTime = mutableMapOf<Player, Long>()

// Formatter voor je logfile
private val timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
// Path naar de yell-log
private val yellLogPath: Path = Paths.get("data", "yell.log")

on_command("yell", description = "Yell to everyone") {
    val now = System.currentTimeMillis()

    // Cooldown logic…
    val cooldownMs = if (player.privilege.id == 0) 15_000L else 0L
    val last = lastYellTime[player] ?: 0L
    if (now - last < cooldownMs) {
        val secondsLeft = ((cooldownMs - (now - last)) / 1000) + 1
        player.message(
            "You need to wait $secondsLeft second${if (secondsLeft > 1) "s" else ""} before your next yell!.",
            ChatMessageType.CONSOLE
        )
        return@on_command
    }
    lastYellTime[player] = now

    // Rank & kleur bepalen
    val (rank, color) = when (player.privilege.id) {
        -1 -> "Banned" to ""
        0 -> "Player" to ""
        1 -> "<img=0>Moderator" to "<col=8900331>"
        2 -> "<img=1>Admin" to "<col=8900331>"
        3 -> "<img=1>Admin" to "<col=8900331>"
        else -> "unidentified" to ""
    }
    val visibleName = player.username.replace(" ", "<col=ffffff>·</col>")
    val text = player.getCommandArgs().joinToString(" ")

    // Broadcast naar iedereen
    player.world.players.forEach {
        it.message("$color[$rank] $visibleName: $text", ChatMessageType.CONSOLE)
    }

    // --- NIEUW: log deze yell naar data/yell.log ---
    try {
        // zorg dat de parent-dir bestaat
        yellLogPath.parent?.let { Files.createDirectories(it) }
        // de regel met timestamp, rank, username en text
        val ts = LocalDateTime.now().format(timeFormatter)
        val line = "[$ts] [$rank] ${player.username}: $text${System.lineSeparator()}"
        Files.write(
            yellLogPath,
            line.toByteArray(Charsets.UTF_8),
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND
        )
    } catch (e: Exception) {
        println("Could not update yell.log: ${e.message}")
    }
}
