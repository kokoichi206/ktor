package jp.mydns.kokoichi0206

import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import jp.mydns.kokoichi0206.models.MakeTurn
import jp.mydns.kokoichi0206.models.TicTacToeGame
import kotlinx.coroutines.channels.consumeEach
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.lang.Exception

fun Route.socket(game: TicTacToeGame) {
    route("/play") {
        webSocket {

            val player = game.connectPlayer(this)

            if (player == null) {
                close(CloseReason(CloseReason.Codes.CANNOT_ACCEPT, "2 players already connected!"))
                return@webSocket
            }

            try {
                // ReceiveChannel
                incoming.consumeEach { frame ->
                    if (frame is Frame.Text) {
                        val action = extractAction(frame.readText())
                        game.finishTurn(player, action.x, action.y)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                game.disconnectPlayer(player)
            }
        }
    }
}

private fun extractAction(message: String): MakeTurn {
    // make_turn#{...}
    val type = message.substringBefore("#")
    val body = message.substringAfter("#")
    return if (type == "make_turn") {
        Json.decodeFromString(body)
    } else MakeTurn(-1, -1)
}
