package jp.mydns.kokoichi0206.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import jp.mydns.kokoichi0206.models.TicTacToeGame
import jp.mydns.kokoichi0206.socket

fun Application.configureRouting(game: TicTacToeGame) {

    routing {
        socket(game)
    }
}
