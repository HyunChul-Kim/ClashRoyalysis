package com.app.chul.clashroyalysis.jsonobject

import android.os.Parcel
import android.os.Parcelable

data class  PlayerGameData(
        var total: Int,
        var tournamentGames: Int,
        var wins: Int,
        var warDayWins: Int,
        var winsPercent: Float,
        var losses: Int,
        var lossesPercent: Float,
        var draws: Int,
        var drawsPercent: Float): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readFloat(),
            parcel.readInt(),
            parcel.readFloat(),
            parcel.readInt(),
            parcel.readFloat())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(total)
        parcel.writeInt(tournamentGames)
        parcel.writeInt(wins)
        parcel.writeInt(warDayWins)
        parcel.writeFloat(winsPercent)
        parcel.writeInt(losses)
        parcel.writeFloat(lossesPercent)
        parcel.writeInt(draws)
        parcel.writeFloat(drawsPercent)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlayerGameData> {
        override fun createFromParcel(parcel: Parcel): PlayerGameData {
            return PlayerGameData(parcel)
        }

        override fun newArray(size: Int): Array<PlayerGameData?> {
            return arrayOfNulls(size)
        }
    }
}