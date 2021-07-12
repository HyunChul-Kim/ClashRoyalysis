package com.app.chul.clashroyalysis.jsonobject

import android.os.Parcel
import android.os.Parcelable

data class SeasonData(
        var id: String = "",
        var rank: Int = 0,
        var trophies: Int = 0,
        var bestTrophies: Int = 0): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeInt(rank)
        parcel.writeInt(trophies)
        parcel.writeInt(bestTrophies)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SeasonData> {
        override fun createFromParcel(parcel: Parcel): SeasonData {
            return SeasonData(parcel)
        }

        override fun newArray(size: Int): Array<SeasonData?> {
            return arrayOfNulls(size)
        }
    }
}