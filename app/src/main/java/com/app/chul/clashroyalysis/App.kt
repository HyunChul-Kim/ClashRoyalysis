package com.app.chul.clashroyalysis

import android.app.Application
import com.app.chul.clashroyalysis.preference.RoyalysisPreferenceManager

class App: Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        RoyalysisPreferenceManager.init(this)
    }
}