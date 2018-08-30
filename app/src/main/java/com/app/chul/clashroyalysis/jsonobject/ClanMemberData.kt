package com.app.chul.clashroyalysis.jsonobject

data class ClanMemberData (
        val name: String,
        val tag: String,
        val rank: Int,
        val previousRank: Int,
        val role: String,
        val expLevel: Int,
        val trophies: Int,
        val clanChestCrowns: Int,
        val donations: Int,
        val donationsReceived: Int,
        val donationsDelta: Int,
        val donationsPercent: Float,
        val arena: ArenaData
)