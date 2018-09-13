package com.app.chul.clashroyalysis.jsonobject

data class PlayerGameData(
        var total: Int,
        var tournamentGames: Int,
        var wins: Int,
        var warDayWins: Int,
        var winsPercent: Float,
        var losses: Int,
        var lossesPercent: Float,
        var draws: Int,
        var drawsPercent: Float
)