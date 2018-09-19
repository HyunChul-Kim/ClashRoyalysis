package com.app.chul.clashroyalysis.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.app.chul.clashroyalysis.R

class CircleProgressView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val title: TextView
    private val progress: ProgressView

    init {
        LayoutInflater.from(context).inflate(R.layout.circle_progress_view, this, true)
        title = findViewById(R.id.circle_progress_title)
        progress = findViewById(R.id.circle_progress)
    }

    fun setTitle(title: String) {
        this.title.text = title
    }

    fun setMax(value: Int) {
        progress.setMax(value)
    }

    fun setProgress(value: Float) {
        progress.setProgress(value)
    }
}