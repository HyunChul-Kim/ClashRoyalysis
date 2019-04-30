package com.app.chul.clashroyalysis.view

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration

class FixedSwipeRefreshLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet ?= null)
    :SwipeRefreshLayout(context, attrs) {

    private var touchSlop: Int = ViewConfiguration.get(context).scaledTouchSlop
    private var prevX = 0f

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                prevX = MotionEvent.obtain(event).x
            }
            MotionEvent.ACTION_MOVE -> {
                val diffX = Math.abs(event.x - prevX)
                if(diffX > touchSlop) return false
            }
        }
        return super.onInterceptTouchEvent(event)
    }

    override fun canChildScrollUp(): Boolean {
        val child = getChildAt(0)

        if(child is RecyclerView) {
            val layoutManager = child.layoutManager
            return child.childCount > 0 &&
                    (layoutManager != null &&
                            layoutManager is LinearLayoutManager &&
                            layoutManager.findFirstVisibleItemPosition() > 0)
        }
        return false
    }
}