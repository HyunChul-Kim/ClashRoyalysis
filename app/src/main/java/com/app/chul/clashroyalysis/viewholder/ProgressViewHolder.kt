package com.app.chul.clashroyalysis.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.app.chul.clashroyalysis.R

class ProgressViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val max = itemView.findViewById<TextView>(R.id.max_progress)
    private val low = itemView.findViewById<TextView>(R.id.low_progress)
    private val progress = itemView.findViewById<ProgressBar>(R.id.progress)

    fun bind(maxValue: Int, value: Int) {
        progress.max = maxValue
        progress.progress = value
        low.text = "0"
        max.text = maxValue.toString()
    }
}