package com.app.chul.clashroyalysis.utils

import com.app.chul.clashroyalysis.App
import com.app.chul.clashroyalysis.jsonobject.RegionDataList
import com.google.gson.Gson

class RegionUtils {

    private lateinit var regionDataList: RegionDataList

    companion object {
        fun getInstance(): RegionUtils {
            return RegionUtils()
        }

        const val FILE_NAME = "regions.json"
    }

    private constructor() {
        initData()
    }

    private fun initData() {
        val jsonString = readAssetJson(App.instance.applicationContext, FILE_NAME)
        regionDataList = Gson().fromJson(jsonString, RegionDataList::class.java)
    }

    fun getRegions(): RegionDataList {
        return regionDataList
    }

}