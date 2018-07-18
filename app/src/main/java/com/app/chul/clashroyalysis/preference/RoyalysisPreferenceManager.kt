package com.app.chul.clashroyalysis.preference

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object RoyalysisPreferenceManager {

    fun getUsers(context: Context): String {
        val pref: SharedPreferences = context.getSharedPreferences("UserInfo", Activity.MODE_PRIVATE)
        return pref.getString("tag", "")
    }

    fun setUsers(context: Context, tag: String) {
        val pref = context.getSharedPreferences("UserInfo", Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("tag", tag)
        editor.commit()
    }
}