package com.app.chul.clashroyalysis.view

import android.content.Context
import android.graphics.*
import androidx.core.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.app.chul.clashroyalysis.R

class CircleProgressView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mArcRadius = 0f

    private var mArcRect = RectF()
    private var mTextRect = Rect()

    private var mArcPaint = Paint()
    private var mProgressPaint = Paint()
    private var mTextPaint = Paint()

    private var mProgressSweep = 0f
    private var mProgressWidth = 30f
    private var mArcWidth = 30f
    private var mTextSize = 30f
    private var mPoint = 0f
    private var mMax = 100
    private var mMin = 0
    private var mStep = 0
    private var mClockwise = false
    private var mEnabled = false

    init {
        val density =  resources.displayMetrics.density

        var arcColor = ContextCompat.getColor(context, R.color.color_arc)
        var progressColor = ContextCompat.getColor(context, R.color.color_progress)
        var textColor = ContextCompat.getColor(context, R.color.color_text)

        mProgressWidth *= density
        mArcWidth *= density
        mTextSize *= density

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView, 0, 0)

            mPoint = typedArray.getFloat(R.styleable.CircleProgressView_points, mPoint)
            mMax = typedArray.getInteger(R.styleable.CircleProgressView_max, mMax)
            mMin = typedArray.getInteger(R.styleable.CircleProgressView_min, mMin)
            mStep = typedArray.getInteger(R.styleable.CircleProgressView_step, mStep)

            mProgressWidth = typedArray.getDimension(R.styleable.CircleProgressView_progressWidth, mProgressWidth)
            progressColor = typedArray.getColor(R.styleable.CircleProgressView_progressColor, progressColor)

            mArcWidth = typedArray.getDimension(R.styleable.CircleProgressView_arcWidth, mArcWidth)
            arcColor = typedArray.getColor(R.styleable.CircleProgressView_arcColor, arcColor)

            mTextSize = typedArray.getDimension(R.styleable.CircleProgressView_textSize, mTextSize)
            textColor = typedArray.getColor(R.styleable.CircleProgressView_textColor, textColor)

            mClockwise = typedArray.getBoolean(R.styleable.CircleProgressView_clockwise, mClockwise)

            mEnabled = typedArray.getBoolean(R.styleable.CircleProgressView_enabled, mEnabled)

            typedArray.recycle()
        }

        mArcPaint.color = arcColor
        mArcPaint.isAntiAlias = true
        mArcPaint.style = Paint.Style.STROKE
        mArcPaint.strokeWidth = mArcWidth

        mProgressPaint.color = progressColor
        mProgressPaint.isAntiAlias = true
        mProgressPaint.style = Paint.Style.STROKE
        mProgressPaint.strokeWidth = mProgressWidth

        mTextPaint.color = textColor
        mTextPaint.isAntiAlias = true
        mTextPaint.style = Paint.Style.FILL
        mTextPaint.textSize = mTextSize
    }

    override fun onDraw(canvas: Canvas?) {
        var textPoint = String.format("%.1f", mPoint) + "%"
        mTextPaint.getTextBounds(textPoint, 0, textPoint.length, mTextRect)

        var x = width / 2f - (mTextRect.width() / 2)
        var y = (height / 2) - ((mTextPaint.descent() + mTextPaint.ascent()) / 2)
        canvas?.drawText(textPoint, x, y, mTextPaint)

        canvas?.drawArc(mArcRect, 0f, 360f, false, mArcPaint)
        canvas?.drawArc(mArcRect, 270f, mProgressSweep, false, mProgressPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        var desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        var width = measureDimension(desiredWidth, widthMeasureSpec)
        var height = measureDimension(desiredHeight, heightMeasureSpec)

        if(height == 0) {
            height = width
        }

        val min = Math.min(width, height)

        var arcWidth = min - mArcWidth
        mArcRadius = arcWidth / 2f
        var top = (height / 2f) - mArcRadius
        var left = (width / 2f) - mArcRadius
        mArcRect.set(left, top, left + arcWidth, top + arcWidth)

//        setMeasuredDimension(width, height)
        setMeasuredDimension(width, height)
    }

    private fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        return when(specMode) {
            MeasureSpec.EXACTLY -> specSize
            MeasureSpec.AT_MOST -> {
                Math.min(desiredSize, specSize)
            }
            MeasureSpec.UNSPECIFIED -> desiredSize
            else -> desiredSize
        }
    }

    fun setMax(value: Int) {
        mMax = value
    }

    fun setProgress(value: Float) {
        mPoint = value
        mProgressSweep = (value / mMax) * 360f
    }
}