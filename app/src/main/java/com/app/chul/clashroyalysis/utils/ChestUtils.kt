package com.app.chul.clashroyalysis.utils

import com.app.chul.clashroyalysis.R

class ChestUtils {
    companion object {
        fun getImage(type: String): Int {
            return when {
                type.equals("wooden", true) -> {
                    R.drawable.chest_wooden_50x50
                }
                type.equals("silver", true) -> {
                    R.drawable.chest_silver_50x50
                }
                type.equals("gold", true) -> {
                    R.drawable.chest_golden_50x50
                }
                type.equals("giant", true) -> {
                    R.drawable.chest_giant_50x50
                }
                type.equals("epic", true) -> {
                    R.drawable.chest_epic_50x50
                }
                type.equals("magical", true) -> {
                    R.drawable.chest_magical_50x50
                }
                type.equals("superMagical", true) -> {
                    R.drawable.chest_super_magical_50x50
                }
                type.equals("legendary", true) -> {
                    R.drawable.chest_legendary_50x50
                }
                else -> {
                    0
                }
            }
        }
    }
}