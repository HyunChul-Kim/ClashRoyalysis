package com.app.chul.clashroyalysis.preference

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.app.chul.clashroyalysis.App
import com.app.chul.clashroyalysis.jsonobject.TagList
import com.google.gson.Gson

object RoyalysisPreferenceManager{

    private const val MAX_SIZE: Int = 10
    private const val USER_TAG_PREF = "tag"

//    private val mPref = App.instance.context().getSharedPreferences(App.instance.packageName, Activity.MODE_PRIVATE)

    private lateinit var mPref: SharedPreferences

    fun init(context: Context) {
        mPref = context.getSharedPreferences(App.instance.packageName, Activity.MODE_PRIVATE)
    }

    fun getUsers(): String {
        return mPref.getString(USER_TAG_PREF, "")
    }

    fun addUser(tag: String) {
        val list = getUserList()
        if(!list.contains(tag)) {
            list.add(tag)
            setUserList(list)
        }
    }

    private fun setUserList(list: ArrayList<String>) {
        val dataList: String = Gson().toJson(list)
        setData(dataList)
    }

    fun getUserList(): ArrayList<String> {
        val dataJson: String = getData()
        return Gson().fromJson(dataJson, TagList::class.java)
    }

    private fun setData(data: String) {
        synchronized(this@RoyalysisPreferenceManager) {
            val editor = mPref.edit()
            editor.putString(USER_TAG_PREF, data)
            editor.commit()
        }
    }

    private fun getData(): String {
        synchronized(this@RoyalysisPreferenceManager) {
            val list: ArrayList<String> = ArrayList()
            val emptyJson: String = Gson().toJson(list)
            return mPref.getString(USER_TAG_PREF, emptyJson)
        }
    }
}