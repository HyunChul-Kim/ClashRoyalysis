package com.app.chul.clashroyalysis.jsonobject

data class ModeData(
        var name: String,
        var deck: String,
        var cardLevels: String,
        var overtimeSeconds: Int,
        var players: String,
        var sameDeck: Boolean
)