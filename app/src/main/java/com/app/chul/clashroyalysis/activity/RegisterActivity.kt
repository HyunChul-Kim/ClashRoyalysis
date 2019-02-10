package com.app.chul.clashroyalysis.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.`interface`.BaseFragmentInterface
import com.app.chul.clashroyalysis.adapter.RegisterViewPagerAdapter
import com.facebook.ads.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: BaseActivity() {

    private lateinit var nativeAd: NativeAd

    private lateinit var nativeAdLayout: NativeAdLayout
    private lateinit var adView: LinearLayout

    private val mAdapter : RegisterViewPagerAdapter by lazy {
        RegisterViewPagerAdapter(supportFragmentManager, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViewPager()
        loadNativeAd()
    }

    private fun initViewPager() {
        val limit = if(mAdapter.count > 1) mAdapter.count - 1 else 1
        register_view_pager.offscreenPageLimit = limit
        register_view_pager.adapter = mAdapter
        register_tab.setupWithViewPager(register_view_pager)
        register_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                (supportFragmentManager.fragments[register_tab.selectedTabPosition] as BaseFragmentInterface).scrollTop()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun loadNativeAd() {
        nativeAd = NativeAd(this, "257266801836381_257268315169563")
        nativeAd.setAdListener(object : NativeAdListener{
            override fun onMediaDownloaded(ad: Ad?) {

            }

            override fun onError(ad: Ad?, error: AdError?) {

            }

            override fun onAdLoaded(ad: Ad?) {
                if(nativeAd == null || nativeAd != ad) {
                    return
                }

                inflateAd(nativeAd)
            }

            override fun onAdClicked(ad: Ad?) {

            }

            override fun onLoggingImpression(ad: Ad?) {

            }
        })
        nativeAd.loadAd()
    }


    private fun inflateAd(nativeAd: NativeAd) {
        nativeAd.unregisterView()

        nativeAdLayout = findViewById(R.id.register_native_ad_container)
        adView = LayoutInflater.from(this).inflate(R.layout.view_native_ad, nativeAdLayout, false) as LinearLayout
        nativeAdLayout.addView(adView)

        val adChoicesContainer = findViewById<LinearLayout>(R.id.ad_choices_container)
        val adOptionsView = AdOptionsView(this, nativeAd, nativeAdLayout)
        adChoicesContainer.removeAllViews()
        adChoicesContainer.addView(adOptionsView, 0)

        val nativeAdIcon = adView.findViewById<AdIconView>(R.id.native_ad_icon)
        val nativeAdTitle = adView.findViewById<TextView>(R.id.native_ad_title)
        val nativeAdMedia = adView.findViewById<MediaView>(R.id.native_ad_media)
        val nativeAdSocialContext = adView.findViewById<TextView>(R.id.native_ad_social_context)
        val nativeAdBody = adView.findViewById<TextView>(R.id.native_ad_body)
        val sponsoredLabel = adView.findViewById<TextView>(R.id.native_ad_sponsored_label)
        val nativeAdCallToAction = adView.findViewById<Button>(R.id.native_ad_call_to_action)

        nativeAdTitle.text = nativeAd.advertiserName
        nativeAdBody.text = nativeAd.adBodyText
        nativeAdSocialContext.text = nativeAd.adSocialContext
        nativeAdCallToAction.text = nativeAd.adCallToAction
        nativeAdCallToAction.visibility = if(nativeAd.hasCallToAction()) View.VISIBLE else View.INVISIBLE
        sponsoredLabel.text = nativeAd.sponsoredTranslation

        val clickableViews = ArrayList<View>()
        clickableViews.add(nativeAdTitle)
        clickableViews.add(nativeAdCallToAction)

        nativeAd.registerViewForInteraction(adView, nativeAdMedia, nativeAdIcon, clickableViews)
    }
}