package com.app.chul.clashroyalysis.`interface`

import com.facebook.ads.NativeAdsManager

interface BaseFragmentInterface {
    fun scrollTop()
    fun refresh()
    fun setNativeAdsManager(nativeAdsManager: NativeAdsManager?)
}