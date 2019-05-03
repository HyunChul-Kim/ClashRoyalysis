package com.app.chul.clashroyalysis.viewholder.home

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.PlayerData
import com.app.chul.clashroyalysis.utils.ArenaInfo
import com.bumptech.glide.Glide

class UserProfileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val userArenaImage = itemView.findViewById<ImageView>(R.id.profile_user_arena_image)
    private val userTrophy = itemView.findViewById<TextView>(R.id.profile_user_trophy)
    private val userRank = itemView.findViewById<TextView>(R.id.profile_user_rank)
    private val userArena = itemView.findViewById<TextView>(R.id.profile_user_arena)
    private val userName = itemView.findViewById<TextView>(R.id.profile_user_name)
    private val userClanName = itemView.findViewById<TextView>(R.id.profile_user_clan_name)
    private val userClanImg = itemView.findViewById<ImageView>(R.id.profile_user_clan_img)

    fun bind(data: PlayerData){
        userName.text = data.name
        userTrophy.text = data.trophies.toString()
        userRank.text = data.rank.toString()
        userClanName.text = data.clan?.name
        userArena.text = data.arena?.name
        Glide.with(itemView.context).load(data.clan?.badge?.image).into(userClanImg)
        data.arena?.let { it ->
            if(!TextUtils.isEmpty(it.arena)) {
                ArenaInfo.get(it.arena)?.let { info ->
                    Glide.with(itemView.context).load(info.resourceId).into(userArenaImage)
                }
            }
        }

    }
}