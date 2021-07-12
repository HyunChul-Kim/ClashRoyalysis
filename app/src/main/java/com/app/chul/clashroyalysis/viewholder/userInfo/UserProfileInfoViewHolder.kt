package com.app.chul.clashroyalysis.viewholder.userInfo

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.PlayerData
import com.bumptech.glide.Glide

class UserProfileInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val userTrophy = itemView.findViewById<TextView>(R.id.profile_user_trophy)
    private val userRank = itemView.findViewById<TextView>(R.id.profile_user_rank)
    private val userArena = itemView.findViewById<TextView>(R.id.profile_user_arena)
    private val userClanName = itemView.findViewById<TextView>(R.id.profile_user_clan_name)
    private val userClanImg = itemView.findViewById<ImageView>(R.id.profile_user_clan_img)

    fun bind(data: PlayerData){
        userTrophy.text = data.getTrophy()
        userRank.text = data.getRank()
        userClanName.text = data.clan?.name ?: "-"
        userArena.text = data.arena?.name
        Glide.with(itemView.context).load(data.clan?.badge?.image).into(userClanImg)

    }
}