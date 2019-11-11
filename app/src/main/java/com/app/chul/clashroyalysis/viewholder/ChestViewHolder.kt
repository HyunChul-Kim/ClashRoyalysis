package com.app.chul.clashroyalysis.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.dto.Chest
import com.app.chul.clashroyalysis.utils.ChestUtils
import com.bumptech.glide.Glide

class ChestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val chestImageView = itemView.findViewById<ImageView>(R.id.chest_viewholder_image_view)
    private val chestLeftCount = itemView.findViewById<TextView>(R.id.chest_viewholder_left_count)

    fun bind(chest: Chest) {
        val image = "https://royaleapi.com/static/img/chests/chest-${chest.type.toLowerCase()}.png"
        Glide.with(itemView.context).load(image).into(chestImageView)
        chestLeftCount.text = if(chest.leftCount == 0) itemView.context.getString(R.string.next) else "+${chest.leftCount}"
    }

}