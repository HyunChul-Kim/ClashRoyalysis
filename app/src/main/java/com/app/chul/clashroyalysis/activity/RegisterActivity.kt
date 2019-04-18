package com.app.chul.clashroyalysis.activity

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.`interface`.BaseFragmentInterface
import com.app.chul.clashroyalysis.fragment.PopularDeckFragment
import com.app.chul.clashroyalysis.fragment.RankFragment
import com.app.chul.clashroyalysis.fragment.RegisterFragment
import com.app.chul.clashroyalysis.jsonobject.PopularDeckList
import com.app.chul.clashroyalysis.jsonobject.TopPlayerList
import com.app.chul.clashroyalysis.listener.TabChangedListener
import com.app.chul.clashroyalysis.presenter.FragmentDataPresenter
import com.app.chul.clashroyalysis.view.FragmentTabView
import com.facebook.ads.*
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*
import kotlin.collections.ArrayList

class RegisterActivity: BaseActivity(){

    private lateinit var nativeAd: NativeAd

    private lateinit var nativeAdLayout: NativeAdLayout
    private lateinit var adView: LinearLayout

    private val fragmentMap = HashMap<String, Fragment>()
    private var selectedTab = FragmentTabView.TabType.Home.name
    private val dataPresenter = FragmentDataPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initFragmentData()
        initFragment()
        initFragmentTab()
        initSwipeRefresh()
        loadNativeAd()
    }

    private fun initFragment() {
        fragmentMap[FragmentTabView.TabType.Home.name] = RegisterFragment.getInstance()
        fragmentMap[FragmentTabView.TabType.Deck.name] = PopularDeckFragment.getInstance()
        fragmentMap[FragmentTabView.TabType.Rank.name] = RankFragment.getInstance()
    }

    private fun initFragmentTab() {
        register_fragment_tab.setTabChangedListener(object: TabChangedListener {
            override fun onChanged(type: FragmentTabView.TabType) {
                when(type) {
                    FragmentTabView.TabType.Home -> {
                        selectedTab = FragmentTabView.TabType.Home.name
                        fragmentManager.beginTransaction()
                                .replace(R.id.register_fragment_container, fragmentMap[selectedTab])
                                .commit()
                        (fragmentMap[selectedTab] as RegisterFragment).setData(dataPresenter.getUserList())
                    }

                    FragmentTabView.TabType.Deck -> {
                        selectedTab = FragmentTabView.TabType.Deck.name
                        fragmentManager.beginTransaction()
                                .replace(R.id.register_fragment_container, fragmentMap[selectedTab])
                                .commit()
                        (fragmentMap[selectedTab] as PopularDeckFragment).setData(dataPresenter.getDeckList())
                    }

                    FragmentTabView.TabType.Rank -> {
                        selectedTab = FragmentTabView.TabType.Rank.name
                        fragmentManager.beginTransaction()
                                .replace(R.id.register_fragment_container, fragmentMap[selectedTab])
                                .commit()
                        (fragmentMap[selectedTab] as RankFragment).setData(dataPresenter.getRankList())
                    }
                }
            }
        })

        // first visible fragment is Home //
        selectedTab = FragmentTabView.TabType.Home.name
        fragmentManager.beginTransaction()
                .replace(R.id.register_fragment_container, fragmentMap[selectedTab])
                .commit()
        (fragmentMap[selectedTab] as RegisterFragment).setData(dataPresenter.getUserList())

    }

    private fun initFragmentData() {
        dataPresenter.initData("kr", 50)
    }

    private fun initSwipeRefresh() {
        register_swipe_refresh.setOnRefreshListener {
            when(selectedTab) {
                FragmentTabView.TabType.Home.name -> {
                    dataPresenter.getRefreshUserList(object: FragmentDataPresenter.ResponseListener<ArrayList<String>>{
                        override fun onResponse(response: ArrayList<String>) {
                            (fragmentMap[selectedTab] as RegisterFragment).setData(response)
                            register_swipe_refresh.isRefreshing = false
                        }
                    })
                }

                FragmentTabView.TabType.Deck.name -> {
                    dataPresenter.getRefreshDeckList(object : FragmentDataPresenter.ResponseListener<PopularDeckList>{
                        override fun onResponse(response: PopularDeckList) {
                            (fragmentMap[selectedTab] as PopularDeckFragment).setData(response)
                            register_swipe_refresh.isRefreshing = false
                        }
                    })
                }

                FragmentTabView.TabType.Rank.name -> {
                    dataPresenter.getRefreshRankList("kr", 50, object : FragmentDataPresenter.ResponseListener<TopPlayerList>{
                        override fun onResponse(response: TopPlayerList) {
                            (fragmentMap[selectedTab] as RankFragment).setData(response)
                            register_swipe_refresh.isRefreshing = false
                        }
                    })
                }
            }
        }
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
        /*nativeAd.unregisterView()

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

        nativeAd.registerViewForInteraction(adView, nativeAdMedia, nativeAdIcon, clickableViews)*/
    }
}