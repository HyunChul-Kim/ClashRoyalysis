package com.app.chul.clashroyalysis.jsonobject

data class DeckInfo (
        var cards: ArrayList<CardData>,
        var decklink: String,
        var popularity: Int
)