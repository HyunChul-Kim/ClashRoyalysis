package com.app.chul.clashroyalysis.viewholder.adUnits

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.app.chul.clashroyalysis.R
import com.facebook.ads.AdIconView
import com.facebook.ads.MediaView

class NativeAdViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val nativeAdMedia: MediaView
    val nativeAdIcon: AdIconView
    val nativeAdTitle: TextView
    val nativeAdSocialContext: TextView
    val nativeAdBody: TextView
    val sponsoredLabel: TextView
    val nativeAdCallToAction: Button
    val adChoicesContainer: LinearLayout

    init {
        nativeAdIcon = itemView.findViewById(R.id.native_ad_icon)
        nativeAdTitle = itemView.findViewById(R.id.native_ad_title)
        nativeAdMedia = itemView.findViewById(R.id.native_ad_media)
        nativeAdSocialContext = itemView.findViewById(R.id.native_ad_social_context)
        nativeAdBody = itemView.findViewById(R.id.native_ad_body)
        sponsoredLabel = itemView.findViewById(R.id.native_ad_sponsored_label)
        nativeAdCallToAction = itemView.findViewById(R.id.native_ad_call_to_action)
        adChoicesContainer = itemView.findViewById(R.id.ad_choices_container)
    }
}