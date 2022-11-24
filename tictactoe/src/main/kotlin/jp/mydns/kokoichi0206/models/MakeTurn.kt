package jp.mydns.kokoichi0206.models

import kotlinx.serialization.Serializable

@Serializable
data class MakeTurn(
    val x: Int,
    val y: Int,
)
