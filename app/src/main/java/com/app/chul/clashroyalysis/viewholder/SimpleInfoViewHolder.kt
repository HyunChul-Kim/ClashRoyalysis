package com.app.chul.clashroyalysis.viewholder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.app.chul.clashroyalysis.MainActivity
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.UserData
import com.bumptech.glide.Glide

class SimpleInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val userClanImg = itemView?.findViewById<ImageView>(R.id.simple_user_clan_img)
    private val userName = itemView?.findViewById<TextView>(R.id.simple_user_name)
    private val userInfo = itemView?.findViewById<TextView>(R.id.simple_user_info)

    fun bind(userData: UserData) {
        Glide.with(itemView.context).load(userData.clan?.badge?.image).into(userClanImg)
        userName.text = userData.name
        userInfo.text = getUserInfoString(userData)
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, MainActivity::class.java)
            intent.putExtra("tag", userData.tag)
            itemView.context.startActivity(intent)
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