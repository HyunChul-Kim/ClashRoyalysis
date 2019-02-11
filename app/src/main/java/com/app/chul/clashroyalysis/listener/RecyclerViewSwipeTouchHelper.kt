package com.app.chul.clashroyalysis.listener

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.ViewConfiguration

const val TAG = "RecyclerViewSwipeTouchListener"

class RecyclerViewSwipeTouchHelper: RecyclerView.OnItemTouchListener, OnActivityTouchListener {

    private lateinit var recyclerView: RecyclerView

    private var minFling: Int = 0
    private var maxFling: Int = 0

    constructor() {

    }

    fun attachToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        val viewConfiguration = ViewConfiguration.get(recyclerView.context)
        minFling = viewConfiguration.scaledMinimumFlingVelocity * 16
        maxFling = viewConfiguration.scaledMaximumFlingVelocity
    }

    override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTouchCoordinates(ev: MotionEvent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}