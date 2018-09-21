package com.app.chul.clashroyalysis.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.app.chul.clashroyalysis.utils.LoadingComputations

class ProgressView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
):View(context, attrs, defStyleAttr) {

    private var loadingComputations: LoadingComputations = LoadingComputations(resources.displayMetrics.density)
    private val mLoadingPaint = Paint()

    private var mHeadPoint = 0f
    private var mTailPoint = 0f

    init {
        mLoadingPaint.style = Paint.Style.FILL
        mLoadingPaint.strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawLine(mTailPoint, 0f, mHeadPoint, 0f, mLoadingPaint)
    }
}