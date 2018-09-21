package com.app.chul.clashroyalysis.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.view.CircleProgressView

class DoubleRateViewHolder(itemView: View, _leftTitle: String, _rightTitle: String): RecyclerView.ViewHolder(itemView) {

    private val leftTitle = itemView.findViewById<TextView>(R.id.left_title)
    private val rightTitle = itemView.findViewById<TextView>(R.id.right_title)
    private val leftProgress = itemView.findViewById<CircleProgressView>(R.id.left_progress)
    private val rightProgress = itemView.findViewById<CircleProgressView>(R.id.right_progress)

    init {
        leftTitle.text = _leftTitle
        rightTitle.text = _rightTitle
    }

    fun bind(_leftRate: Float, _rightRate: Float) {
        leftProgress.setProgress(_leftRate * 100)
        rightProgress.setProgress(_rightRate * 100)
    }
}