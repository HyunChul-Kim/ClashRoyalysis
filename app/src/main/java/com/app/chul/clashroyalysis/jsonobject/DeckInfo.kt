package com.app.chul.clashroyalysis.jsonobject

data class DeckInfo (
        var cards: List<CardData>,
        var decklink: String,
        var popularity: Int
)