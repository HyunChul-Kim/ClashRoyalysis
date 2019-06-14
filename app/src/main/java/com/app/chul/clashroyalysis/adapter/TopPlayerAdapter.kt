package com.app.chul.clashroyalysis.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.TopPlayerList
import com.app.chul.clashroyalysis.viewholder.adUnits.NativeAdViewHolder
import com.app.chul.clashroyalysis.viewholder.adUnits.NativeBannerAdViewHolder
import com.app.chul.clashroyalysis.viewholder.rank.TopPlayerViewHolder
import com.facebook.ads.NativeAd
import com.facebook.ads.NativeAdLayout
import com.facebook.ads.NativeAdsManager

class TopPlayerAdapter(private val activity: Activity, private var nativeAdsManager: NativeAdsManager?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mAdItems : MutableList<NativeAd>
    private var topPlayerList: TopPlayerList = TopPlayerList()

    companion object ViewType {
        const val AD_DISPLAY_FREQUENCY = 10
        const val AD_VIEW_TYPE = 0
        const val PLAYER_VIEW_TYPE = 1
    }

    init {
        mAdItems = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            AD_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_native_banner_ad, parent, false) as NativeAdLayout
                NativeBannerAdViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_top_player, parent, false)
                return TopPlayerViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return topPlayerList.size + mAdItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            AD_VIEW_TYPE -> {
                val ad: NativeAd?

                if(mAdItems.size > position / AD_DISPLAY_FREQUENCY) {
                    ad = mAdItems[position / AD_DISPLAY_FREQUENCY]
                } else {
                    ad = nativeAdsManager?.nextNativeAd()
                    if(ad != null && !ad.isAdInvalidated) {
                        mAdItems.add(ad)
                    } else {
                        Log.w(TopPlayerAdapter::class.java.simpleName, "Ad is invalidated!")
                    }
                }
                (holder as NativeBannerAdViewHolder).bind(ad, activity)
            }
            PLAYER_VIEW_TYPE -> {
                val viewHolder = holder as TopPlayerViewHolder
                viewHolder.bind(topPlayerList[position - ((position / AD_DISPLAY_FREQUENCY) + 1)])
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if(position % AD_DISPLAY_FREQUENCY == 0) AD_VIEW_TYPE else PLAYER_VIEW_TYPE
    }

    fun setNativeAdsManager(manager: NativeAdsManager?) {
        nativeAdsManager = manager
        notifyDataSetChanged()
    }

    fun setData(list: TopPlayerList) {
        topPlayerList = list
        notifyDataSetChanged()
    }

}