package com.app.chul.clashroyalysis.jsonobject

data class TopPlayer (
        val name: String,
        val tag: String,
        val rank: Int,
        val previousRank: Int,
        val expLevel: Int,
        val trophies: Int,
        val donationsDelta: String,
        val clan: PlayerClanData?,
        val arena: ArenaData
)