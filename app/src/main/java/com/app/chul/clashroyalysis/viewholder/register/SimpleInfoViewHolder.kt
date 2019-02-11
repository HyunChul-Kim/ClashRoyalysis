package com.app.chul.clashroyalysis.viewholder.register

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.app.chul.clashroyalysis.activity.UserInfoActivity
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.PlayerData
import com.app.chul.clashroyalysis.retrofit.ClashRoyaleRetrofit
import com.app.chul.clashroyalysis.view.CardListView
import com.app.chul.clashroyalysis.view.RetryView
import com.app.chul.clashroyalysis.view.StereoLoadingView
import com.app.chul.clashroyalysis.viewholder.SubTaskViewHolder
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

const val TAG = "SimpleInfo"

class SimpleInfoViewHolder(parent: ViewGroup): SubTaskViewHolder(parent) {

    private var playerData: PlayerData? = null

    private val loadingView: StereoLoadingView
    private val retryView: RetryView
    private val userClanImg: ImageView
    private val userName: TextView
    private val userInfo: TextView
    private val userDeck: CardListView
    private val divider: View

    private var userTag: String = ""

    init {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_user_simple_info, parent, false)

        loadingView = view.findViewById(R.id.simple_user_loading_view)
        retryView = view.findViewById(R.id.retry_view)
        retryView.setListener {
            if(!TextUtils.isEmpty(userTag)) {
                retryView.visibility = View.GONE
                requestPlayerData(userTag)
            }
        }
        userClanImg = view.findViewById(R.id.simple_user_clan_img)
        userName = view.findViewById(R.id.simple_user_name)
        userInfo = view.findViewById(R.id.simple_user_info)
        userDeck = view.findViewById(R.id.simple_user_deck_list)
        divider = view.findViewById(R.id.divider)

        addForegroundTask(view)
    }

    private fun setData(data: PlayerData) {
        Glide.with(itemView.context).load(data.clan?.badge?.image).into(userClanImg)
        userDeck.bind(data.currentDeck)
        userName.text = data.name
        userInfo.text = getUserInfoFormat(data)
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, UserInfoActivity::class.java)
            intent.putExtra("data", data)
            itemView.context.startActivity(intent)
        }
    }

    fun bind(tag: String) {
        playerData?.let { setData(it) } ?: let {
            userTag = tag
            requestPlayerData(userTag)
        }
    }

    private fun requestPlayerData(tag: String) {
        loadingView.start()
        ClashRoyaleRetrofit.getService().getPlayer(tag)
                .timeout(10000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    playerData = it
                    setData(it)
                    divider.visibility = View.VISIBLE
                    retryView.visibility = View.GONE
                }, {
                    retryView.visibility = View.VISIBLE
                }, {
                    loadingView.stop()
                    loadingView.visibility = View.GONE
                })
    }

    private fun getUserInfoFormat(playerData: PlayerData): SpannableStringBuilder{
        val stringBuilder = SpannableStringBuilder("")
        stringBuilder.append("Trophies ")
        var startIdx = stringBuilder.length
        stringBuilder.append(playerData.trophies.toString())
        stringBuilder.setSpan(StyleSpan(Typeface.BOLD), startIdx, stringBuilder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        stringBuilder.append(" | ")
        playerData.arena?.let {
            stringBuilder.append("Arena ")
            startIdx = stringBuilder.length
            stringBuilder.append(it.arena)
            stringBuilder.setSpan(StyleSpan(Typeface.BOLD), startIdx, stringBuilder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            stringBuilder.append(" | ")
        }
        stringBuilder.append("Rank ")
        startIdx = stringBuilder.length
        stringBuilder.append(playerData.rank.toString())
        stringBuilder.setSpan(StyleSpan(Typeface.BOLD), startIdx, stringBuilder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        return stringBuilder
    }

    fun getTag() = userTag
}