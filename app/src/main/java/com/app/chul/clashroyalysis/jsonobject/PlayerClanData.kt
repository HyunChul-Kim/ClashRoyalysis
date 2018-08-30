package com.app.chul.clashroyalysis.jsonobject

data class PlayerClanData (

    val tag: String,
    val name: String,
    val role: String,
    val donations: Int,
    val donationsReceived: Int,
    val donationsDelta: Int,
    val badge: BadgeData
)