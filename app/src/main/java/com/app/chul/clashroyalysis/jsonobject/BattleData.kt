package com.app.chul.clashroyalysis.jsonobject

data class BattleData (
        var type: String,
        var challengeType: String,
        var mode: ModeData,
        var winCountBefore: Int,
        var utcTime: Long,
        var deckType: String,
        var teamSize: Int,
        var winner: Int,
        var teamCrowns: Int,
        var opponentCrowns: Int,
        var team: List<BattleUserData>,
        var opponent: List<BattleUserData>,
        var arena: ArenaData
)