package com.app.chul.clashroyalysis.viewholder.userInfo

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.ArenaData
import com.app.chul.clashroyalysis.utils.ArenaInfo
import com.bumptech.glide.Glide

class UserProfileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val userArenaImage = itemView.findViewById<ImageView>(R.id.profile_user_arena_image)
    private val userName = itemView.findViewById<TextView>(R.id.profile_user_name)

    fun bind(name: String, arenaData: ArenaData?){
        userName.text = name
        arenaData?.let { data ->
            val param = "arena${data.id}.png"
            Glide.with(itemView.context).load("https://royaleapi.com/static/img/arenas/${param}").into(userArenaImage)
        }

    }
}