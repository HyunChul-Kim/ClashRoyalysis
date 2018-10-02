package com.app.chul.clashroyalysis.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.app.chul.clashroyalysis.R

class ProgressViewHolder(itemView: View, _title: String): RecyclerView.ViewHolder(itemView) {

    private val max = itemView.findViewById<TextView>(R.id.max_progress)
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val progress = itemView.findViewById<ProgressBar>(R.id.progress)
    private val noDataFilter = itemView.findViewById<TextView>(R.id.progress_no_data)

    init {
        title.text = _title
    }

    fun bind(maxValue: Int?, value: Int) {
        maxValue?.let {
            if (maxValue > 0) {
                noDataFilter.visibility = View.GONE
                progress.max = maxValue
                progress.progress = value
                max.text = maxValue.toString()
            } else {
                noDataFilter.visibility = View.VISIBLE
            }
        }
    }
}