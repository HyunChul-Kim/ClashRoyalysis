package com.app.chul.clashroyalysis.viewholder

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.CardData
import com.bumptech.glide.Glide

class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    private val imageView = itemView.findViewById<ImageView>(R.id.card_image_view)

    fun bindData(card: CardData) {
        Glide.with(itemView.context).load(card.icon).into(imageView)
    }
}