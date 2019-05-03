package com.app.chul.clashroyalysis.utils

import com.app.chul.clashroyalysis.R

enum class ArenaInfo(val league: String, val resourceId: Int) {
    Arena0("", R.drawable.arena0),
    Arena1("", R.drawable.arena1),
    Arena2("", R.drawable.arena2),
    Arena3("", R.drawable.arena3),
    Arena4("", R.drawable.arena4),
    Arena5("", R.drawable.arena5),
    Arena6("", R.drawable.arena6),
    Arena7("", R.drawable.arena7),
    Arena8("", R.drawable.arena8),
    Arena9("", R.drawable.arena9),
    Arena10("", R.drawable.arena10),
    Arena11("", R.drawable.arena11),
    Arena12("", R.drawable.arena12),
    Arena13("League 1", R.drawable.league1),
    Arena14("League 2", R.drawable.league2),
    Arena15("League 3", R.drawable.league3),
    Arena16("League 4", R.drawable.league4),
    Arena17("League 5", R.drawable.league5),
    Arena18("League 6", R.drawable.league6),
    Arena19("League 7", R.drawable.league7),
    Arena20("League 8", R.drawable.league8),
    Arena21("League 9", R.drawable.league9);

    companion object {
        fun get(name: String?): ArenaInfo? {
            for(info in values()) {
                if(info.name.equals(name, true) || info.league.equals(name, true)) {
                    return info
                }
            }

            return null
        }
    }
}