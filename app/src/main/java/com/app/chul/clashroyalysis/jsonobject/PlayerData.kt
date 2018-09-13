package com.app.chul.clashroyalysis.jsonobject

data class PlayerData (
        var tag: String,
        var name: String,
        var trophies: Int,
        var rank: Int,
        var arena: ArenaData,
        var clan: PlayerClanData,
        var stats: PlayerStatsData,
        var games: PlayerGameData,
        var leagueStatistics: PlayerLeagueData,
        var deckLink: String,
        var currentDeck: List<CardData>,
        var cards: List<CardData>,
        var achievements: List<AchievementData>
)