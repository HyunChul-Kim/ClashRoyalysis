package com.app.chul.clashroyalysis.jsonobject

data class ClanData (
        val tag: String,
        val name: String,
        val description: String,
        val type: String,
        val score: Int,
        val memberCount: Int,
        val requiredScore: Int,
        val donations: Int,
        val badge: BadgeData,
        val location: LocationData,
        val members: ArrayList<ClanMemberData>
)