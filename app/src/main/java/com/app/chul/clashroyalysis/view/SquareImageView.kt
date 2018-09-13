package com.app.chul.clashroyalysis.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.app.chul.clashroyalysis.R

class SquareImageView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    private var fitMode: String = "normal"

    init {
        fitMode = context.obtainStyledAttributes(attrs, R.styleable.SquareImageView).getString(R.styleable.SquareImageView_fit)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        when(fitMode){
            "width" -> super.onMeasure(widthMeasureSpec, widthMeasureSpec)
            "height" -> super.onMeasure(heightMeasureSpec, heightMeasureSpec)
            else -> super.onMeasure(widthMeasureSpec, widthMeasureSpec)
        }
    }
}