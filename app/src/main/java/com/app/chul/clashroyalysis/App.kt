package com.app.chul.clashroyalysis

import android.app.Application
import com.app.chul.clashroyalysis.preference.RoyalysisPreferenceManager
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
        AudienceNetworkAds.initialize(this)
        RoyalysisPreferenceManager.init(this)
    }
}