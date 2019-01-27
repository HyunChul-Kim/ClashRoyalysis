package com.app.chul.clashroyalysis.viewholder.rank

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.bus.RxBus
import com.app.chul.clashroyalysis.bus.RxEvent
import com.app.chul.clashroyalysis.jsonobject.TopPlayer
import com.bumptech.glide.Glide

class TopPlayerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val playerRank = itemView.findViewById<TextView>(R.id.top_player_rank)
    private val clanImage = itemView.findViewById<ImageView>(R.id.top_player_clan_image)
    private val playerName = itemView.findViewById<TextView>(R.id.top_player_name)
    private val playerTrophies = itemView.findViewById<TextView>(R.id.top_player_trophies)
    private val favoritesButton = itemView.findViewById<ImageView>(R.id.top_player_favorites_button)

    private var playerTag = ""

    init {
        favoritesButton.setOnClickListener {
            if(!TextUtils.isEmpty(playerTag)) {
                RxBus.publish(RxEvent.EventAddTag(playerTag))
            }
        }
    }

    fun bind(playerData: TopPlayer?) {
        playerData?.let {
            it.clan?.let {
                Glide.with(itemView.context).load(it.badge.image).into(clanImage)
            }
            playerRank.text = playerData.rank.toString()
            playerName.text = playerData.name
            playerTrophies.text = playerData.trophies.toString()
            playerTag = playerData.tag
        }
    }
}