package com.app.chul.clashroyalysis.jsonobject

data class BattleUserData(
        var tag: String,
        var name: String,
        var crownsEarned: Int,
        var startTrophies: Int,
        var clan: ClanData,
        var deckLink: String,
        var deck: List<CardData>
)