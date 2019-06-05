package com.app.chul.clashroyalysis.view

import android.content.Context
import android.support.v4.view.ViewCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration

class FixedSwipeRefreshLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet ?= null)
    :SwipeRefreshLayout(context, attrs) {

    private var touchSlop: Int = ViewConfiguration.get(context).scaledTouchSlop
    private var prevX = 0f
    private var childView: View ?= null

    fun setChildView(child: View) {
        childView = child
    }

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
        val child = childView ?: childView ?: getChildAt(0)

        if(child is RecyclerView) {
            val layoutManager = child.layoutManager
            return layoutManager.childCount > 0 &&
                    (layoutManager != null &&
                            layoutManager is LinearLayoutManager &&
                            layoutManager.findFirstVisibleItemPosition() > 0) ||
                    (layoutManager.getChildAt(0) != null &&
                            layoutManager.getChildAt(0).top < layoutManager.paddingTop)
        }
        return child.canScrollVertically(-1) || child.scrollY > 0
    }
}