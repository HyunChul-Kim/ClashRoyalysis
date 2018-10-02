package com.app.chul.clashroyalysis.jsonobject

import android.os.Parcel
import android.os.Parcelable

data class PlayerLeagueData(
        var currentSeason: SeasonData?,
        var previousSeason: SeasonData?,
        var bestSeason: SeasonData?): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(SeasonData::class.java.classLoader),
            parcel.readParcelable(SeasonData::class.java.classLoader),
            parcel.readParcelable(SeasonData::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(currentSeason, flags)
        parcel.writeParcelable(previousSeason, flags)
        parcel.writeParcelable(bestSeason, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlayerLeagueData> {
        override fun createFromParcel(parcel: Parcel): PlayerLeagueData {
            return PlayerLeagueData(parcel)
        }

        override fun newArray(size: Int): Array<PlayerLeagueData?> {
            return arrayOfNulls(size)
        }
    }
}