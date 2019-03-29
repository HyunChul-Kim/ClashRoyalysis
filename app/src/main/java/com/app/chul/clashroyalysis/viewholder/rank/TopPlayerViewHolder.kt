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
import com.app.chul.clashroyalysis.preference.RoyalysisPreferenceManager
import com.app.chul.clashroyalysis.utils.UserDataHelper
import com.bumptech.glide.Glide

class TopPlayerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val playerRank = itemView.findViewById<TextView>(R.id.top_player_rank)
    private val clanImage = itemView.findViewById<ImageView>(R.id.top_player_clan_image)
    private val playerName = itemView.findViewById<TextView>(R.id.top_player_name)
    private val playerTrophies = itemView.findViewById<TextView>(R.id.top_player_trophies)
    private val favoritesButton = itemView.findViewById<ImageView>(R.id.top_player_favorites_button)

    private var playerTag = ""
    private var isChecked = false

    init {
        favoritesButton.setOnClickListener {
            if(!TextUtils.isEmpty(playerTag)) {
                RxBus.publish(RxEvent.EventAddTag(playerTag))
                isChecked = if(isChecked) {
                    favoritesButton.setImageResource(R.drawable.star_border_36)
                    false
                } else {
                    favoritesButton.setImageResource(R.drawable.star_black_36)
                    true
                }
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
            isChecked = if(UserDataHelper.getInstance(itemView.context).getUserList().contains(playerTag)) {
                favoritesButton.setImageResource(R.drawable.star_black_36)
                true
            } else {
                favoritesButton.setImageResource(R.drawable.star_border_36)
                false
            }
        }
    }
}