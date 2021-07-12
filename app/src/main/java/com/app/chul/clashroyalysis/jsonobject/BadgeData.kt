package com.app.chul.clashroyalysis.jsonobject

import android.os.Parcel
import android.os.Parcelable

data class BadgeData (
    val name: String,
    val category: String,
    val id: Int,
    val image: String): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readInt(),
            parcel.readString() ?: "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(category)
        parcel.writeInt(id)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BadgeData> {
        override fun createFromParcel(parcel: Parcel): BadgeData {
            return BadgeData(parcel)
        }

        override fun newArray(size: Int): Array<BadgeData?> {
            return arrayOfNulls(size)
        }
    }
}