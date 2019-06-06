package com.app.chul.clashroyalysis.utils

import android.content.Context
import android.util.Log
import com.app.chul.clashroyalysis.BuildConfig.DEBUG
import com.facebook.ads.AdSettings
import com.facebook.ads.AudienceNetworkAds

class AudienceNetworkInitializeHelper : AudienceNetworkAds.InitListener {

    override fun onInitialized(result: AudienceNetworkAds.InitResult) {
        Log.d(AudienceNetworkAds.TAG, result.message)
    }

    companion object {

        internal fun initialize(context: Context) {
            if(!AudienceNetworkAds.isInitialized(context)) {
                if(DEBUG) {
                    AdSettings.turnOnSDKDebugger(context)
                }

                AudienceNetworkAds
                        .buildInitSettings(context)
                        .withInitListener(AudienceNetworkInitializeHelper())
                        .initialize()
            }
        }
    }
}