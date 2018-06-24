package com.app.chul.clashroyalysis.preference

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object RoyalysisPreferenceManager {

    fun getRegisterList(context: Context): String {
        val pref: SharedPreferences = context.getSharedPreferences("UserInfo", Activity.MODE_PRIVATE)
        return pref.getString("RegisterList", "")
    }
}