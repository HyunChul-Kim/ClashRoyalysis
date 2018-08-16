package com.app.chul.clashroyalysis.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.app.chul.clashroyalysis.utils.FrameManager
import com.app.chul.clashroyalysis.utils.LoadingComputations
import com.app.chul.clashroyalysis.utils.RealTimestampProvider

class StereoLoadingView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): View(context, attrs, defStyleAttr){

    private var loadingComputations: LoadingComputations = LoadingComputations(resources.displayMetrics.density)

    private val barRedPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val barYellowPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val barBluePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var start: Boolean = false

    init {
        barRedPaint.color = Color.parseColor("#ff0048")
        barRedPaint.style = Paint.Style.FILL
        barRedPaint.strokeWidth = loadingComputations.dpToPx(10)

        barYellowPaint.color = Color.parseColor("#ffb600")
        barYellowPaint.style = Paint.Style.FILL
        barYellowPaint.strokeWidth = loadingComputations.dpToPx(10)

        barBluePaint.color = Color.parseColor("#0087ff")
        barBluePaint.style = Paint.Style.FILL
        barBluePaint.strokeWidth = loadingComputations.dpToPx(10)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawLine(loadingComputations.dpToPx(50) - loadingComputations.dpToPx(12)
                , loadingComputations.dpToPx(50)
                , loadingComputations.dpToPx(50) - loadingComputations.dpToPx(12)
                , loadingComputations.dpToPx(50) - loadingComputations.dpToPx(20) * loadingComputations.verticalPosition(System.currentTimeMillis()
                , 125).toFloat(), barRedPaint)

        canvas?.drawLine(loadingComputations.dpToPx(50)
                , loadingComputations.dpToPx(50)
                , loadingComputations.dpToPx(50)
                , loadingComputations.dpToPx(50) - loadingComputations.dpToPx(20) * loadingComputations.verticalPosition(System.currentTimeMillis()
                , 0).toFloat(), barYellowPaint)

        canvas?.drawLine(loadingComputations.dpToPx(50) + loadingComputations.dpToPx(12)
                , loadingComputations.dpToPx(50)
                , loadingComputations.dpToPx(50) + loadingComputations.dpToPx(12)
                , loadingComputations.dpToPx(50) - loadingComputations.dpToPx(20) * loadingComputations.verticalPosition(System.currentTimeMillis()
                , 250).toFloat(), barBluePaint)

        if(start) {
            invalidate()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        setMeasuredDimension(measureDimension(desiredWidth, widthMeasureSpec),
                measureDimension(desiredHeight, heightMeasureSpec))
        /*setMeasuredDimension(loadingComputations.dpToPx(100).toInt(),
                loadingComputations.dpToPx(100).toInt())*/
    }

    private fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        return when(specMode) {
            MeasureSpec.EXACTLY -> specSize
            MeasureSpec.AT_MOST -> {
                var size = Math.min(desiredSize, specSize)
                if(size == 0) {
                    size = loadingComputations.dpToPx(100).toInt()
                }
                size
            }
            MeasureSpec.UNSPECIFIED -> loadingComputations.dpToPx(100).toInt()
            else -> desiredSize
        }
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