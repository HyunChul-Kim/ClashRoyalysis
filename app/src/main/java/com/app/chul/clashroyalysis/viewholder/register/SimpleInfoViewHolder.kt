package com.app.chul.clashroyalysis.viewholder.register

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.app.chul.clashroyalysis.MainActivity
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.CardData
import com.app.chul.clashroyalysis.jsonobject.PlayerData
import com.app.chul.clashroyalysis.retrofit.ClashRoyaleRetrofit
import com.app.chul.clashroyalysis.view.DeckListView
import com.app.chul.clashroyalysis.view.StereoLoadingView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SimpleInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private var playerData: PlayerData? = null

    private val loadingView = itemView.findViewById<StereoLoadingView>(R.id.simple_user_loading_view)
    private val userClanImg = itemView.findViewById<ImageView>(R.id.simple_user_clan_img)
    private val userName = itemView.findViewById<TextView>(R.id.simple_user_name)
    private val userInfo = itemView.findViewById<TextView>(R.id.simple_user_info)
    private val userDeckList = itemView.findViewById<DeckListView>(R.id.simple_user_deck_list)

    fun setData(playerData: PlayerData) {
        Glide.with(itemView.context).load(playerData.playerClan?.badge?.image).into(userClanImg)
        userDeckList.setUserDeckList(playerData.currentDeck)
        userName.text = playerData.name
        userInfo.text = getUserInfoString(playerData)
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, MainActivity::class.java)
            intent.putExtra("tag", playerData.tag)
            itemView.context.startActivity(intent)
        }
    }

    fun bind(userTag: String) {
        if(playerData == null) {
            loadingView.start()
            val userDataCall = ClashRoyaleRetrofit.getService().getPlayer(userTag)
            userDataCall.enqueue(object: Callback<PlayerData> {
                override fun onFailure(call: Call<PlayerData>?, t: Throwable?) {
                    loadingView.stop()
                    loadingView.visibility = View.GONE
                }

                override fun onResponse(call: Call<PlayerData>?, response: Response<PlayerData>?) {
                    response?.body()?.let {
                        setData(it)
                    }
                    loadingView.stop()
                    loadingView.visibility = View.GONE
                }

            })
        }
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