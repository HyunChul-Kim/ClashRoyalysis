package com.app.chul.clashroyalysis.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.view.ProgressView

class CircleProgressViewHolder(itemView: View, title: String): RecyclerView.ViewHolder(itemView) {

    private val progressTitle = itemView.findViewById<TextView>(R.id.circle_progress_title)
    private val progress = itemView.findViewById<ProgressView>(R.id.circle_progress)

    init {
        progressTitle.text = title
    }

    fun bind(winRate: Float) {
        progress.setProgress(winRate * 100)
    }
}