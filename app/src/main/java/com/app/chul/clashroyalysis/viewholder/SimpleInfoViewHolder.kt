package com.app.chul.clashroyalysis.viewholder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.app.chul.clashroyalysis.MainActivity
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.CardData
import com.app.chul.clashroyalysis.jsonobject.UserData
import com.app.chul.clashroyalysis.retrofit.ClashRoyaleRetrofit
import com.app.chul.clashroyalysis.view.StereoLoadingView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SimpleInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private var userData: UserData? = null

    private val loadingView = itemView.findViewById<StereoLoadingView>(R.id.simple_user_loading_view)
    private val userClanImg = itemView.findViewById<ImageView>(R.id.simple_user_clan_img)
    private val userName = itemView.findViewById<TextView>(R.id.simple_user_name)
    private val userInfo = itemView.findViewById<TextView>(R.id.simple_user_info)
    private val userDeckCard1 = itemView.findViewById<ImageView>(R.id.simple_deck_card1)
    private val userDeckCard2 = itemView.findViewById<ImageView>(R.id.simple_deck_card2)
    private val userDeckCard3 = itemView.findViewById<ImageView>(R.id.simple_deck_card3)
    private val userDeckCard4 = itemView.findViewById<ImageView>(R.id.simple_deck_card4)
    private val userDeckCard5 = itemView.findViewById<ImageView>(R.id.simple_deck_card5)
    private val userDeckCard6 = itemView.findViewById<ImageView>(R.id.simple_deck_card6)
    private val userDeckCard7 = itemView.findViewById<ImageView>(R.id.simple_deck_card7)
    private val userDeckCard8 = itemView.findViewById<ImageView>(R.id.simple_deck_card8)

    fun setData(userData: UserData) {
        Glide.with(itemView.context).load(userData.clan?.badge?.image).into(userClanImg)
        setUserDeckList(userData.currentDeck)
        userName.text = userData.name
        userInfo.text = getUserInfoString(userData)
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, MainActivity::class.java)
            intent.putExtra("tag", userData.tag)
            itemView.context.startActivity(intent)
        }
    }

    fun bind(userTag: String) {
        if(userData == null) {
            loadingView.start()
            val userDataCall = ClashRoyaleRetrofit.getService().getPlayer(userTag)
            userDataCall.enqueue(object: Callback<UserData> {
                override fun onFailure(call: Call<UserData>?, t: Throwable?) {
                    loadingView.stop()
                    loadingView.visibility = View.GONE
                }

                override fun onResponse(call: Call<UserData>?, response: Response<UserData>?) {
                    if(response?.body() != null){
                        setData(response.body() as UserData)
                    }
                    loadingView.stop()
                    loadingView.visibility = View.GONE
                }

            })
        }
    }

    private fun setUserDeckList(deckList: List<CardData>?) {
        deckList?.let {
            for(index in it.indices){
                Glide.with(itemView.context).load(it[index].icon).into(getCardView(index))
            }
        }
    }

    private fun getCardView(position: Int): ImageView{
        return when(position){
            0 -> userDeckCard1
            1 -> userDeckCard2
            2 -> userDeckCard3
            3 -> userDeckCard4
            4 -> userDeckCard5
            5 -> userDeckCard6
            6 -> userDeckCard7
            7 -> userDeckCard8
            else -> userDeckCard1
        }
    }

    private fun getUserInfoString(userData: UserData): String{
        val stringBuilder = StringBuilder("")
        stringBuilder.append("Trophies ")
        stringBuilder.append(userData.trophies)
        stringBuilder.append(" | ")
        userData.arena?.let {
            stringBuilder.append("Arena ")
            stringBuilder.append(it.name)
            stringBuilder.append(" | ")
        }
        stringBuilder.append("Rank ")
        stringBuilder.append(userData.rank)

        return stringBuilder.toString()
    }
}