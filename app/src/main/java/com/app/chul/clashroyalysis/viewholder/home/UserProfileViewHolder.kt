package com.app.chul.clashroyalysis.viewholder.home

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.app.chul.clashroyalysis.R

class UserProfileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val userName = itemView?.findViewById<TextView>(R.id.profile_user_name)
    private val userTrophy = itemView?.findViewById<TextView>(R.id.profile_user_trophy)

    fun bind(name: String?, trophy: Int){
        if(!TextUtils.isEmpty(name)){
            userName.text = name
        }
        trophy?.let {
            userTrophy.text = trophy.toString()
        }
    }
}