package com.app.chul.clashroyalysis.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.app.chul.clashroyalysis.App
import com.app.chul.clashroyalysis.jsonobject.TagList
import com.google.gson.Gson

class UserDataHelper {

    companion object {
        private const val USER_TAG_PREF = "user_tag"

        private var Instance: UserDataHelper? = null

        fun getInstance(context: Context?): UserDataHelper =
                Instance ?: synchronized(this) {
                    Instance ?: UserDataHelper(context)
                }
    }

    private var mPref: SharedPreferences?
    private var mUserList: ArrayList<String>

    private constructor(context: Context?) {
        Instance = this
        mPref = context?.getSharedPreferences(App.instance.packageName, Activity.MODE_PRIVATE)
        mUserList = getUserDataToList()
    }

    fun getUserList() = mUserList

    fun addUserData(tag: String): Boolean {
        if(!mUserList.contains(tag)) {
            mUserList.add(tag)
            return true
        }
        return false
    }

    fun deleteUserData(tag: String): Boolean {
        if(mUserList.contains(tag)) {
            mUserList.remove(tag)
            return true
        }

        return false
    }

    fun updateData() {
        // convert ArrayList to JsonArray
        setUserData(Gson().toJson(mUserList))
    }

    private fun setUserData(data: String) {
        synchronized(this@UserDataHelper) {
            val editor = mPref?.edit()
            editor?.putString(USER_TAG_PREF, data)
            editor?.apply()
        }
    }

    private fun getUserDataToList(): ArrayList<String> {
        val dataJson: String? = getUserData()
        return Gson().fromJson(dataJson, TagList::class.java)
    }

    private fun getUserData(): String? {
        val list: ArrayList<String> = ArrayList()
        val emptyJson: String = Gson().toJson(list)
        synchronized(this@UserDataHelper) {
            return mPref?.getString(USER_TAG_PREF, emptyJson)
        }
    }
}