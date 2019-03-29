package com.app.chul.clashroyalysis

import android.app.Application
import com.app.chul.clashroyalysis.preference.RoyalysisPreferenceManager
import com.app.chul.clashroyalysis.utils.UserDataHelper
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
        AudienceNetworkAds.initialize(this)
        RoyalysisPreferenceManager.init(this)
        UserDataHelper.getInstance(this)
    }
}