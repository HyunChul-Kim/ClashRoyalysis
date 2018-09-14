package com.app.chul.clashroyalysis.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.view.ProgressView

class CircleProgressViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val progress = itemView.findViewById<ProgressView>(R.id.circle_progress)

    fun bind(winRate: Float) {
        progress.setMax(100)
        progress.setProgress(winRate * 100)
    }
}