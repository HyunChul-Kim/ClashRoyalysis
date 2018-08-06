package com.app.chul.clashroyalysis.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.app.chul.clashroyalysis.Utils.FrameManager
import com.app.chul.clashroyalysis.Utils.LoadingComputations
import com.app.chul.clashroyalysis.Utils.RealTimestampProvider

class StereoLoadingView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): View(context, attrs, defStyleAttr){

    private var loadingComputations: LoadingComputations = LoadingComputations(resources.displayMetrics.density)
    private var frameManager: FrameManager = FrameManager(RealTimestampProvider())

    private val barRedPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val barYellowPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val barBluePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var start: Boolean = false

    init {
        barRedPaint.color = Color.parseColor("#b0b0b0")
        barRedPaint.style = Paint.Style.FILL
        barRedPaint.strokeWidth = loadingComputations.dpToPx(10)
    }

    override fun onDraw(canvas: Canvas?) {
        if(frameManager.canGo()) {
            canvas?.drawLine(loadingComputations.dpToPx(50)
                    - loadingComputations.dpToPx(12)
                    , loadingComputations.dpToPx(50)
                    , loadingComputations.dpToPx(50)
                    - loadingComputations.dpToPx(12)
                    , loadingComputations.dpToPx(50)
                    - loadingComputations.dpToPx(20)
                    * loadingComputations.verticalPosition(System.currentTimeMillis(), 125).toFloat(), barRedPaint)

            canvas?.drawLine(loadingComputations.dpToPx(50)
                    , loadingComputations.dpToPx(50)
                    , loadingComputations.dpToPx(50)
                    , loadingComputations.dpToPx(50)
                    - loadingComputations.dpToPx(20)
                    * loadingComputations.verticalPosition(System.currentTimeMillis(), 0).toFloat(), barRedPaint)

            canvas?.drawLine(loadingComputations.dpToPx(50)
                    + loadingComputations.dpToPx(12)
                    , loadingComputations.dpToPx(50)
                    , loadingComputations.dpToPx(50)
                    + loadingComputations.dpToPx(12)
                    , loadingComputations.dpToPx(50)
                    - loadingComputations.dpToPx(20)
                    * loadingComputations.verticalPosition(System.currentTimeMillis(), 250).toFloat(), barRedPaint)
        }

        if(start) {
            frameManager.frame()
            invalidate()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(loadingComputations.dpToPx(100).toInt(),
                loadingComputations.dpToPx(100).toInt())
    }

    fun start() {
        start = true
        invalidate()
    }

    fun stop() {
        start = false
        invalidate()
    }

}