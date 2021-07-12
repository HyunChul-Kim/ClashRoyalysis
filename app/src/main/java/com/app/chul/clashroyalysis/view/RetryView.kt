package com.app.chul.clashroyalysis.view

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.app.chul.clashroyalysis.R

class RetryView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val retryButton: ImageView
    private val text: TextView

    private var refreshListener: RefreshListener? = null

    interface RefreshListener{
        fun refresh()
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_retry, this, true)
        retryButton = findViewById(R.id.retry)
        text = findViewById(R.id.simple_user_info_no_data)

        retryButton.setOnClickListener {
            refreshListener?.refresh()
        }
    }

    fun setListener(listener: () -> Unit) {
        this.refreshListener = object: RefreshListener {
            override fun refresh() {
                listener()
            }
        }
    }
}
