package com.app.chul.clashroyalysis.jsonobject

import android.os.Parcel
import android.os.Parcelable

data class PlayerClanData (

    val tag: String,
    val name: String,
    val role: String,
    val donations: Int,
    val donationsReceived: Int,
    val donationsDelta: Int,
    val badge: BadgeData): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readParcelable(BadgeData::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(tag)
        parcel.writeString(name)
        parcel.writeString(role)
        parcel.writeInt(donations)
        parcel.writeInt(donationsReceived)
        parcel.writeInt(donationsDelta)
        parcel.writeParcelable(badge, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlayerClanData> {
        override fun createFromParcel(parcel: Parcel): PlayerClanData {
            return PlayerClanData(parcel)
        }

        override fun newArray(size: Int): Array<PlayerClanData?> {
            return arrayOfNulls(size)
        }
    }
}