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
        AdSettings.addTestDevice(getString(R.string.test_device_nexus))
        RoyalysisPreferenceManager.init(this)
        UserDataHelper.getInstance(this)
    }
}