package com.app.chul.clashroyalysis.utils

import com.app.chul.clashroyalysis.R

class ChestUtils {

    private val assetUrl = "https://royaleapi.github.io/cr-api-assets/"

    companion object {
        fun getImage(chest: String): Int {
            val type = chest.replace("\\s+", "")
            return when {
                type.equals("wooden", true) -> {
                    R.drawable.chest_wooden
                }
                type.equals("silver", true) -> {
                    R.drawable.chest_silver
                }
                type.equals("gold", true) -> {
                    R.drawable.chest_gold
                }
                type.equals("gold-fortune", true) -> {
                    R.drawable.chest_gold_fortune
                }
                type.equals("giant", true) -> {
                    R.drawable.chest_giant
                }
                type.equals("epic", true) -> {
                    R.drawable.chest_epic
                }
                type.equals("magical", true) -> {
                    R.drawable.chest_magical
                }
                type.equals("superMagical", true) -> {
                    R.drawable.chest_supermagical
                }
                type.equals("king", true) -> {
                    R.drawable.chest_king
                }
                type.equals("kingLegendary", true) -> {
                    R.drawable.chest_kinglegendary
                }
                type.equals("legendary", true) -> {
                    R.drawable.chest_legendary
                }
                type.equals("lightning", true) -> {
                    R.drawable.chest_lightning
                }
                type.equals("megaLightning", true) -> {
                    R.drawable.chest_megalightning
                }
                type.equals("draft", true) -> {
                    R.drawable.chest_draft
                }
                type.equals("fortune", true) -> {
                    R.drawable.chest_fortune
                }
                else -> {
                    0
                }
            }
        }
    }
}