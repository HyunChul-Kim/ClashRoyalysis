package com.app.chul.clashroyalysis.jsonobject

import android.os.Parcel
import android.os.Parcelable

data class PlayerData (
        var tag: String,
        var name: String,
        var trophies: Int,
        var rank: Int,
        var arena: ArenaData?,
        var clan: PlayerClanData?,
        var stats: PlayerStatsData?,
        var games: PlayerGameData?,
        var leagueStatistics: PlayerLeagueData?,
        var deckLink: String,
        var currentDeck: ArrayList<CardData>,
        var cards: List<CardData>,
        var achievements: List<AchievementData>): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readParcelable(ArenaData::class.java.classLoader),
            parcel.readParcelable(PlayerClanData::class.java.classLoader),
            parcel.readParcelable(PlayerStatsData::class.java.classLoader),
            parcel.readParcelable(PlayerGameData::class.java.classLoader),
            parcel.readParcelable(PlayerLeagueData::class.java.classLoader),
            parcel.readString(),
            parcel.createTypedArrayList(CardData),
            parcel.createTypedArrayList(CardData),
            parcel.createTypedArrayList(AchievementData))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(tag)
        parcel.writeString(name)
        parcel.writeInt(trophies)
        parcel.writeInt(rank)
        parcel.writeParcelable(arena, flags)
        parcel.writeParcelable(clan, flags)
        parcel.writeParcelable(stats, flags)
        parcel.writeParcelable(games, flags)
        parcel.writeParcelable(leagueStatistics, flags)
        parcel.writeString(deckLink)
        parcel.writeTypedList(currentDeck)
        parcel.writeTypedList(cards)
        parcel.writeTypedList(achievements)
    }

    fun getTrophy(): String {
        return if(trophies <= 0) "-" else trophies.toString()
    }

    fun getRank(): String {
        return if(rank <= 0) "-" else rank.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlayerData> {
        override fun createFromParcel(parcel: Parcel): PlayerData {
            return PlayerData(parcel)
        }

        override fun newArray(size: Int): Array<PlayerData?> {
            return arrayOfNulls(size)
        }
    }

    override fun equals(other: Any?): Boolean {
        if(other is PlayerData) {
            return tag.equals(other.tag, true)
        }
        return super.equals(other)
    }
}