package com.app.chul.clashroyalysis

import android.app.Application
import com.app.chul.clashroyalysis.preference.RoyalysisPreferenceManager
import com.app.chul.clashroyalysis.utils.AudienceNetworkInitializeHelper
import com.app.chul.clashroyalysis.utils.UserDataHelper
import com.facebook.ads.AdSettings
import com.facebook.ads.AudienceNetworkAds

class App: Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()

        if(AudienceNetworkAds.isInAdsProcess(this)) {
            return
        }
        AudienceNetworkInitializeHelper.initialize(this)
        AdSettings.addTestDevice("8450cc6f-f28f-4756-83b2-baec77bece9a")
        RoyalysisPreferenceManager.init(this)
        UserDataHelper.getInstance(this)
    }
}