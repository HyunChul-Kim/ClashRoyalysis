package com.app.chul.clashroyalysis.jsonobject

import android.os.Parcel
import android.os.Parcelable

data class AchievementData(
        var name: String,
        var stars: Int,
        var value: Int,
        var target: Int,
        var info: String): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString() ?: "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(stars)
        parcel.writeInt(value)
        parcel.writeInt(target)
        parcel.writeString(info)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AchievementData> {
        override fun createFromParcel(parcel: Parcel): AchievementData {
            return AchievementData(parcel)
        }

        override fun newArray(size: Int): Array<AchievementData?> {
            return arrayOfNulls(size)
        }
    }
}