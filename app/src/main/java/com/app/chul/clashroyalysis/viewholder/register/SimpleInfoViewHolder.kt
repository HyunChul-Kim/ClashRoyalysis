package com.app.chul.clashroyalysis.viewholder.register

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.StyleSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.app.chul.clashroyalysis.activity.UserInfoActivity
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.PlayerData
import com.app.chul.clashroyalysis.retrofit.ClashRoyaleRetrofit
import com.app.chul.clashroyalysis.view.CardListView
import com.app.chul.clashroyalysis.view.RetryView
import com.app.chul.clashroyalysis.view.StereoLoadingView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SimpleInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val userClanImg = itemView.findViewById<ImageView>(R.id.simple_user_clan_img)
    private val userName = itemView.findViewById<TextView>(R.id.simple_user_name)
    private val userInfo = itemView.findViewById<TextView>(R.id.simple_user_info)
    private val userDeck = itemView.findViewById<CardListView>(R.id.simple_user_deck_list)

    private var userTag: String = ""

    fun bind(data: PlayerData) {
        data.clan?.let {clan ->
            Glide.with(itemView.context)
                    .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.no_clan).error(R.drawable.no_clan))
                    .load(clan.badge.image)
                    .into(userClanImg)
        } ?: Glide.with(itemView.context).load(R.drawable.no_clan).into(userClanImg)
        userDeck.bind(data.currentDeck)
        userName.text = data.name
        userInfo.text = getUserInfoFormat(data)
        userTag = data.tag
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, UserInfoActivity::class.java)
            intent.putExtra("data", data)
            itemView.context.startActivity(intent)
        }
    }

    private fun getUserInfoFormat(playerData: PlayerData): SpannableStringBuilder{
        val stringBuilder = SpannableStringBuilder("")
        stringBuilder.append("Trophies ")
        var startIdx = stringBuilder.length
        stringBuilder.append(playerData.trophies.toString())
        stringBuilder.setSpan(StyleSpan(Typeface.BOLD), startIdx, stringBuilder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        stringBuilder.append(" | ")
        playerData.arena?.let {
            if(!TextUtils.isEmpty(it.arena)) {
                stringBuilder.append("Arena ")
                startIdx = stringBuilder.length
                stringBuilder.append(it.arena)
                stringBuilder.setSpan(StyleSpan(Typeface.BOLD), startIdx, stringBuilder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                stringBuilder.append(" | ")
            }
        }
        stringBuilder.append("Rank ")
        startIdx = stringBuilder.length
        stringBuilder.append(playerData.getRank())
        stringBuilder.setSpan(StyleSpan(Typeface.BOLD), startIdx, stringBuilder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        return stringBuilder
    }

    fun getTag() = userTag
}