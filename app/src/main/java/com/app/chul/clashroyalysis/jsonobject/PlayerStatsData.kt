package com.app.chul.clashroyalysis.jsonobject

import android.os.Parcel
import android.os.Parcelable

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
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readParcelable(CardData::class.java.classLoader),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(clanCardsCollected)
        parcel.writeInt(tournamentCardsWon)
        parcel.writeInt(maxTrophies)
        parcel.writeInt(threeCrownWins)
        parcel.writeInt(cardsFound)
        parcel.writeParcelable(favoriteCard, flags)
        parcel.writeInt(totalDonations)
        parcel.writeInt(challengeMaxWins)
        parcel.writeInt(challengeCardsWon)
        parcel.writeInt(level)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlayerStatsData> {
        override fun createFromParcel(parcel: Parcel): PlayerStatsData {
            return PlayerStatsData(parcel)
        }

        override fun newArray(size: Int): Array<PlayerStatsData?> {
            return arrayOfNulls(size)
        }
    }
}