package com.app.chul.clashroyalysis.viewholder.register

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SimpleInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private var playerData: PlayerData? = null

    private val loadingView = itemView.findViewById<StereoLoadingView>(R.id.simple_user_loading_view)
    private val retryView = itemView.findViewById<RetryView>(R.id.retry_view)
    private val userClanImg = itemView.findViewById<ImageView>(R.id.simple_user_clan_img)
    private val userName = itemView.findViewById<TextView>(R.id.simple_user_name)
    private val userInfo = itemView.findViewById<TextView>(R.id.simple_user_info)
    private val userDeck = itemView.findViewById<CardListView>(R.id.simple_user_deck_list)
    private val divider = itemView.findViewById<View>(R.id.divider)

    private var userTag: String = ""

    init {
        retryView.setListener {
            if(!TextUtils.isEmpty(userTag)) {
                retryView.visibility = View.GONE
                requestPlayerData(userTag)
            }
        }
    }

    private fun setData(data: PlayerData) {
        Glide.with(itemView.context).load(data.clan?.badge?.image).into(userClanImg)
        userDeck.bind(data.currentDeck)
        userName.text = data.name
        userInfo.text = getUserInfoString(data)
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, UserInfoActivity::class.java)
//            intent.putExtra("tag", data.tag)
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

    private fun getUserInfoString(playerData: PlayerData): String{
        val stringBuilder = StringBuilder("")
        stringBuilder.append("Trophies ")
        stringBuilder.append(playerData.trophies)
        stringBuilder.append(" | ")
        playerData.arena?.let {
            stringBuilder.append("Arena ")
            stringBuilder.append(it.name)
            stringBuilder.append(" | ")
        }
        stringBuilder.append("Rank ")
        stringBuilder.append(playerData.rank)

        return stringBuilder.toString()
    }
}