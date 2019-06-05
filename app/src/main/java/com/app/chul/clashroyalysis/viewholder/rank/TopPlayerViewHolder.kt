package com.app.chul.clashroyalysis.viewholder.rank

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.TopPlayer
import com.app.chul.clashroyalysis.utils.UserDataHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TopPlayerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val playerRank = itemView.findViewById<TextView>(R.id.top_player_rank)
    private val clanImage = itemView.findViewById<ImageView>(R.id.top_player_clan_image)
    private val playerName = itemView.findViewById<TextView>(R.id.top_player_name)
    private val playerTrophies = itemView.findViewById<TextView>(R.id.top_player_trophies)
    private val favoritesButton = itemView.findViewById<ToggleButton>(R.id.top_player_favorites_button)

    private var playerTag = ""

    init {
        favoritesButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) UserDataHelper.getInstance(itemView.context).deleteUserData(playerTag)
            else UserDataHelper.getInstance(itemView.context).addUserData(playerTag)
        }
    }

    fun bind(playerData: TopPlayer?) {
        playerData?.let {
            it.clan?.let {clan ->
                Glide.with(itemView.context)
                        .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.no_clan).error(R.drawable.no_clan))
                        .load(clan.badge.image)
                        .into(clanImage)
            } ?: Glide.with(itemView.context).load(R.drawable.no_clan).into(clanImage)

            playerRank.text = playerData.rank.toString()
            playerName.text = playerData.name
            playerTrophies.text = playerData.trophies.toString()
            playerTag = playerData.tag
            favoritesButton.isChecked = UserDataHelper.getInstance(itemView.context).getUserList().contains(playerTag)
        }
    }
}