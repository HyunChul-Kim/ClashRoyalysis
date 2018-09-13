package com.app.chul.clashroyalysis.jsonobject

data class PlayerStatsData(
        var clanCardsCollected: Int,
        var tournamentCardsWon: Int,
        var maxTrophies: Int,
        var threeCrownWins: Int,
        var cardsFound: Int,
        var favoriteCard: CardData,
        var totalDonations: Int,
        var challengeMaxWins: Int,
        var challengeCardsWon: Int,
        var level: Int
)