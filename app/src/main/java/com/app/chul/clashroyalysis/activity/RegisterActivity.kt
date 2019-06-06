package com.app.chul.clashroyalysis.activity

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.`interface`.BaseFragmentInterface
import com.app.chul.clashroyalysis.`interface`.BaseInterface
import com.app.chul.clashroyalysis.bus.RxBus
import com.app.chul.clashroyalysis.bus.RxEvent
import com.app.chul.clashroyalysis.fragment.PopularDeckFragment
import com.app.chul.clashroyalysis.fragment.RankFragment
import com.app.chul.clashroyalysis.fragment.RegisterFragment
import com.app.chul.clashroyalysis.jsonobject.PlayerData
import com.app.chul.clashroyalysis.jsonobject.PlayerDataList
import com.app.chul.clashroyalysis.jsonobject.PopularDeckList
import com.app.chul.clashroyalysis.jsonobject.TopPlayerList
import com.app.chul.clashroyalysis.listener.FragmentStateListener
import com.app.chul.clashroyalysis.listener.TabChangedListener
import com.app.chul.clashroyalysis.presenter.BaseDataPresenter
import com.app.chul.clashroyalysis.utils.UserDataHelper
import com.app.chul.clashroyalysis.view.FragmentTabView
import com.facebook.ads.*
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity: BaseActivity(), BaseInterface{

    private lateinit var nativeAd: NativeAd

    private lateinit var nativeAdLayout: NativeAdLayout
    private lateinit var adView: LinearLayout

    private val fragmentMap = HashMap<String, Fragment>()
    private var selectedTab = FragmentTabView.TabType.Home.name
    private val dataPresenter = BaseDataPresenter.getInstance(this)

    private var fragmentStateListener: FragmentStateListener ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerRxBus()
        initFragmentListener()
        initFragment()
        initFragmentTab()
        initFragmentData()
        initSwipeRefresh()
        loadNativeAd()
    }

    private fun initFragment() {
        fragmentMap[FragmentTabView.TabType.Home.name] = RegisterFragment()
        fragmentMap[FragmentTabView.TabType.Deck.name] = PopularDeckFragment()
        fragmentMap[FragmentTabView.TabType.Rank.name] = RankFragment()
    }

    private fun initFragmentTab() {
        register_fragment_tab.setTabChangedListener(object: TabChangedListener {
            override fun onTabSelected(type: FragmentTabView.TabType) {
                when(type) {
                    FragmentTabView.TabType.Home -> {
                        selectedTab = FragmentTabView.TabType.Home.name
                        fragmentManager.beginTransaction()
                                .replace(R.id.register_fragment_container, fragmentMap[selectedTab])
                                .commit()
                        (fragmentMap[selectedTab] as RegisterFragment).setData(dataPresenter.getUserList())
                        (fragmentMap[selectedTab] as RegisterFragment).setFragmentListener(fragmentStateListener)
                    }

                    FragmentTabView.TabType.Deck -> {
                        selectedTab = FragmentTabView.TabType.Deck.name
                        fragmentManager.beginTransaction()
                                .replace(R.id.register_fragment_container, fragmentMap[selectedTab])
                                .commit()
                        (fragmentMap[selectedTab] as PopularDeckFragment).setData(dataPresenter.getDeckList())
                        (fragmentMap[selectedTab] as PopularDeckFragment).setFragmentListener(fragmentStateListener)
                    }

                    FragmentTabView.TabType.Rank -> {
                        selectedTab = FragmentTabView.TabType.Rank.name
                        fragmentManager.beginTransaction()
                                .replace(R.id.register_fragment_container, fragmentMap[selectedTab])
                                .commit()
                        (fragmentMap[selectedTab] as RankFragment).setData(dataPresenter.getRankList())
                        (fragmentMap[selectedTab] as RankFragment).setFragmentListener(fragmentStateListener)
                    }
                }
            }

            override fun onTabReselected() {
                (fragmentMap[selectedTab] as BaseFragmentInterface).scrollTop()
            }
        })

        // first visible fragment is Home //
        selectedTab = FragmentTabView.TabType.Home.name
        fragmentManager.beginTransaction()
                .replace(R.id.register_fragment_container, fragmentMap[selectedTab])
                .commit()
        (fragmentMap[selectedTab] as RegisterFragment).setData(dataPresenter.getUserList())
        (fragmentMap[selectedTab] as RegisterFragment).setFragmentListener(fragmentStateListener)
    }

    private fun initFragmentData() {
        register_swipe_refresh.isRefreshing = true
        dataPresenter.requestPlayersDataList(object : BaseDataPresenter.ResponseListener<PlayerDataList>{
            override fun onError(message: String) {
                register_swipe_refresh.isRefreshing = false
            }

            override fun onResponse(response: PlayerDataList) {
                (fragmentMap[FragmentTabView.TabType.Home.name] as RegisterFragment).setData(response)
                register_swipe_refresh.isRefreshing = false
            }
        })
        dataPresenter.requestDeckList(object : BaseDataPresenter.ResponseListener<PopularDeckList>{
            override fun onError(message: String) {

            }

            override fun onResponse(response: PopularDeckList) {
                (fragmentMap[FragmentTabView.TabType.Deck.name] as PopularDeckFragment).setData(response)
            }
        })
        dataPresenter.requestRankList("kr", 50, object : BaseDataPresenter.ResponseListener<TopPlayerList>{
            override fun onError(message: String) {

            }

            override fun onResponse(response: TopPlayerList) {
                (fragmentMap[FragmentTabView.TabType.Rank.name] as RankFragment).setData(response)
            }
        })
    }

    private fun initFragmentListener() {
        fragmentStateListener = object: FragmentStateListener {
            override fun onActivityCreated(type: FragmentTabView.TabType, recyclerView: RecyclerView) {
                when(type) {
                    FragmentTabView.TabType.Home -> {
                        register_swipe_refresh.setChildView(recyclerView)
                    }
                    FragmentTabView.TabType.Deck -> {
                        register_swipe_refresh.setChildView(recyclerView)
                    }
                    FragmentTabView.TabType.Rank -> {
                        register_swipe_refresh.setChildView(recyclerView)
                    }
                }
            }
        }
    }

    private fun initSwipeRefresh() {
        register_swipe_refresh.setOnRefreshListener {
            when(selectedTab) {
                FragmentTabView.TabType.Home.name -> {
                    dataPresenter.requestPlayersDataList(object: BaseDataPresenter.ResponseListener<PlayerDataList>{
                        override fun onError(message: String) {
                            register_swipe_refresh.isRefreshing = false
                        }

                        override fun onResponse(response: PlayerDataList) {
                            (fragmentMap[FragmentTabView.TabType.Home.name] as RegisterFragment).setData(response)
                            (fragmentMap[FragmentTabView.TabType.Home.name] as RegisterFragment).refresh()
                            register_swipe_refresh.isRefreshing = false
                        }
                    })
                }

                FragmentTabView.TabType.Deck.name -> {
                    dataPresenter.requestDeckList(object : BaseDataPresenter.ResponseListener<PopularDeckList>{
                        override fun onError(message: String) {
                            register_swipe_refresh.isRefreshing = false
                        }

                        override fun onResponse(response: PopularDeckList) {
                            (fragmentMap[FragmentTabView.TabType.Deck.name] as PopularDeckFragment).setData(response)
                            (fragmentMap[FragmentTabView.TabType.Deck.name] as PopularDeckFragment).refresh()
                            register_swipe_refresh.isRefreshing = false
                        }
                    })
                }

                FragmentTabView.TabType.Rank.name -> {
                    dataPresenter.requestRankList("kr", 50, object : BaseDataPresenter.ResponseListener<TopPlayerList>{
                        override fun onError(message: String) {
                            register_swipe_refresh.isRefreshing = false
                        }

                        override fun onResponse(response: TopPlayerList) {
                            (fragmentMap[FragmentTabView.TabType.Rank.name] as RankFragment).setData(response)
                            (fragmentMap[FragmentTabView.TabType.Rank.name] as RankFragment).refresh()
                            register_swipe_refresh.isRefreshing = false
                        }
                    })
                }
            }
        }
    }

    private fun registerRxBus() {
        RxBus.register(this,
                RxBus.listen(RxEvent.EventAddTag::class.java).subscribe {
                    dataPresenter.requestPlayerData(it.tag, true, object : BaseDataPresenter.ResponseListener<PlayerData> {
                        override fun onError(message: String) {

                        }

                        override fun onResponse(response: PlayerData) {
                            (fragmentMap[FragmentTabView.TabType.Home.name] as RegisterFragment).addUser()
                        }
                    })
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

    override fun addUser(tag: String) {
        register_swipe_refresh.isRefreshing = true
        UserDataHelper.getInstance(this).addUserData(tag)
        dataPresenter.requestPlayerData(tag, true, object : BaseDataPresenter.ResponseListener<PlayerData>{
            override fun onError(message: String) {
                register_swipe_refresh.isRefreshing = false
            }

            override fun onResponse(response: PlayerData) {
                register_swipe_refresh.isRefreshing = false
                if(fragmentMap[selectedTab] is RegisterFragment) {
                    (fragmentMap[selectedTab] as RegisterFragment).addUser()
                }
            }

        })
    }
}