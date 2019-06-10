package com.app.chul.clashroyalysis.viewholder.adUnits

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.app.chul.clashroyalysis.R
import com.facebook.ads.AdOptionsView
import com.facebook.ads.MediaView
import com.facebook.ads.NativeAd
import com.facebook.ads.NativeAdLayout

class NativeBannerAdViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val mNativeBannerAdContainer : NativeAdLayout = itemView.findViewById(R.id.native_banner_ad_container)
    private val mNativeBannerAdStatusLabel : TextView = itemView.findViewById(R.id.native_banner_status_label)
    private val mAdView : LinearLayout
    private val nativeAdIcon: MediaView
    private val nativeAdTitle: TextView
    private val nativeAdSocialContext: TextView
    private val sponsoredLabel: TextView
    private val adChoicesContainer: LinearLayout
    private val nativeAdCallToAction: Button

    init {
        mAdView = LayoutInflater.from(itemView.context).inflate(R.layout.view_native_banner_unit, mNativeBannerAdContainer, false) as LinearLayout
        nativeAdIcon = mAdView.findViewById(R.id.native_ad_icon)
        nativeAdTitle = mAdView.findViewById(R.id.native_ad_title)
        nativeAdSocialContext = mAdView.findViewById(R.id.native_ad_social_context)
        sponsoredLabel = mAdView.findViewById(R.id.native_ad_sponsored_label)
        adChoicesContainer = mAdView.findViewById(R.id.ad_choices_container)
        nativeAdCallToAction = mAdView.findViewById(R.id.native_ad_call_to_action)
    }
    fun bind(ad: NativeAd?, activity: Activity) {
        adChoicesContainer.removeAllViews()

        ad?.let {
            /*nativeAdTitle.text = ad.advertiserName
            nativeAdSocialContext.text = ad.adSocialContext
            sponsoredLabel.setText(R.string.sponsored)
            nativeAdCallToAction.text = ad.adCallToAction
            nativeAdCallToAction.visibility = if(ad.hasCallToAction()) View.VISIBLE else View.INVISIBLE
            val adOptionsView = AdOptionsView(activity, ad, mNativeBannerAdContainer)
            adChoicesContainer.addView(adOptionsView, 0)

            val clickableViews = ArrayList<View>()
            clickableViews.add(nativeAdIcon)
            clickableViews.add(nativeAdMedia)
            clickableViews.add(nativeAdCallToAction)
            ad.registerViewForInteraction(
                    nativeAdLayout,
                    nativeAdMedia,
                    nativeAdIcon,
                    clickableViews
            )*/
        }
    }
}