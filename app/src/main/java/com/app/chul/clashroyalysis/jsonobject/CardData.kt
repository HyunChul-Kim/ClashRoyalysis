package com.app.chul.clashroyalysis.jsonobject

import android.os.Parcel
import android.os.Parcelable

data class CardData (
        val name: String,
        val level: Int,
        val maxLevel: Int,
        val rarity: String,
        val requiredForUpgrade: Int,
        val icon: String,
        val key: String,
        val elixir: Int,
        val type: String,
        val arena: Int,
        val description: String,
        val id: Int): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(level)
        parcel.writeInt(maxLevel)
        parcel.writeString(rarity)
        parcel.writeInt(requiredForUpgrade)
        parcel.writeString(icon)
        parcel.writeString(key)
        parcel.writeInt(elixir)
        parcel.writeString(type)
        parcel.writeInt(arena)
        parcel.writeString(description)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CardData> {
        override fun createFromParcel(parcel: Parcel): CardData {
            return CardData(parcel)
        }

        override fun newArray(size: Int): Array<CardData?> {
            return arrayOfNulls(size)
        }
    }
}
