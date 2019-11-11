package com.app.chul.clashroyalysis.jsonobject

import android.os.Parcel
import android.os.Parcelable

data class ArenaData(
        var name: String,
        var arena: String?,
        var id: Int,
        var trophyLimit: Int): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(arena)
        parcel.writeInt(id)
        parcel.writeInt(trophyLimit)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArenaData> {
        override fun createFromParcel(parcel: Parcel): ArenaData {
            return ArenaData(parcel)
        }

        override fun newArray(size: Int): Array<ArenaData?> {
            return arrayOfNulls(size)
        }
    }
}