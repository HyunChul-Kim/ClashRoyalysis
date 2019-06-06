package com.app.chul.clashroyalysis.viewholder.adUnits

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.app.chul.clashroyalysis.R
import com.facebook.ads.AdOptionsView
import com.facebook.ads.MediaView
import com.facebook.ads.NativeAd
import com.facebook.ads.NativeAdLayout

class NativeAdViewHolder(private var nativeAdLayout: NativeAdLayout): RecyclerView.ViewHolder(nativeAdLayout) {
    private val nativeAdMedia: MediaView = nativeAdLayout.findViewById(R.id.native_ad_media)
    private val nativeAdIcon: MediaView = nativeAdLayout.findViewById(R.id.native_ad_icon)
    private val nativeAdTitle: TextView = nativeAdLayout.findViewById(R.id.native_ad_title)
    private val nativeAdSocialContext: TextView = nativeAdLayout.findViewById(R.id.native_ad_social_context)
    private val nativeAdBody: TextView = nativeAdLayout.findViewById(R.id.native_ad_body)
    private val sponsoredLabel: TextView = nativeAdLayout.findViewById(R.id.native_ad_sponsored_label)
    private val nativeAdCallToAction: Button = nativeAdLayout.findViewById(R.id.native_ad_call_to_action)
    private val adChoicesContainer: LinearLayout = nativeAdLayout.findViewById(R.id.ad_choices_container)

    fun bind(ad: NativeAd?, activity: Activity) {
        adChoicesContainer.removeAllViews()

        ad?.let {
            nativeAdTitle.text = ad.advertiserName
            nativeAdBody.text = ad.adBodyText
            nativeAdSocialContext.text = ad.adSocialContext
            sponsoredLabel.setText(R.string.sponsored)
            nativeAdCallToAction.text = ad.adCallToAction
            nativeAdCallToAction.visibility = if(ad.hasCallToAction()) View.VISIBLE else View.INVISIBLE
            val adOptionsView = AdOptionsView(activity, ad, nativeAdLayout)
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
            )
        }
    }
}