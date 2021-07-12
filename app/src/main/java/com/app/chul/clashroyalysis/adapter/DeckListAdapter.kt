package com.app.chul.clashroyalysis.adapter

import android.app.Activity
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.DeckInfo
import com.app.chul.clashroyalysis.viewholder.DeckViewHolder
import com.app.chul.clashroyalysis.viewholder.adUnits.NativeAdViewHolder
import com.facebook.ads.NativeAd
import com.facebook.ads.NativeAdLayout
import com.facebook.ads.NativeAdsManager

class DeckListAdapter(private val activity: Activity, private var nativeAdsManager: NativeAdsManager?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mAdItems : MutableList<NativeAd>
    private var mDeckList = ArrayList<DeckInfo>()

    companion object ViewType {
        const val AD_DISPLAY_FREQUENCY = 5
        const val AD_VIEW_TYPE = 0
        const val DECK_VIEW_TYPE = 1
    }

    init {
        mAdItems = ArrayList()
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
                        Log.w(RegisterAdapter::class.java.simpleName, "Ad is invalidated!")
                    }
                }
                (holder as NativeAdViewHolder).bind(ad, activity)
            }
            DECK_VIEW_TYPE -> {
                val viewHolder = holder as DeckViewHolder
                viewHolder.bindData(mDeckList[position - ((position / AD_DISPLAY_FREQUENCY) + 1)])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            AD_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_native_ad_unit, parent, false) as NativeAdLayout
                NativeAdViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_deck, parent, false)
                return DeckViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return mDeckList.size + mAdItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(position % AD_DISPLAY_FREQUENCY == 0) AD_VIEW_TYPE else DECK_VIEW_TYPE
    }

    fun setNativeAdsManager(manager: NativeAdsManager?) {
        nativeAdsManager = manager
        notifyDataSetChanged()
    }

    fun setData(list: ArrayList<DeckInfo>) {
        mDeckList = list
        notifyDataSetChanged()
    }

    fun addData(list: ArrayList<DeckInfo>) {
        mDeckList.addAll(list)
        notifyDataSetChanged()
    }
}