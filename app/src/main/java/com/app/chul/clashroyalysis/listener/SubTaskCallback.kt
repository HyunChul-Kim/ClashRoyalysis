package com.app.chul.clashroyalysis.listener

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.app.chul.clashroyalysis.utils.ChulLog
import com.app.chul.clashroyalysis.viewholder.register.SimpleInfoViewHolder

open class SubTaskCallback(dragDirs: Int, swipeDirs: Int): ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    private var selectedViewHolder: SimpleInfoViewHolder? = null

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        if(viewHolder != null && viewHolder is SimpleInfoViewHolder) {
//            undo()
            selectedViewHolder = viewHolder
        }
    }

    override fun getSwipeDirs(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        return if(viewHolder is SimpleInfoViewHolder) super.getSwipeDirs(recyclerView, viewHolder) else 0
    }

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        if(viewHolder != null && viewHolder is SimpleInfoViewHolder) {
            ChulLog.log("foreground right : ${viewHolder.getForegroundTask().right} / background left : ${viewHolder.getBackgroundTask().left}")
            var finalDeltaX = if(viewHolder.getForegroundTask().right < viewHolder.getBackgroundTask().left) 0f else dX
            getDefaultUIUtil().onDraw(c, recyclerView, viewHolder.getForegroundTask(), finalDeltaX, dY, actionState, isCurrentlyActive)
        }
    }

    /*override fun onChildDrawOver(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        if(viewHolder != null && viewHolder is SimpleInfoViewHolder) {
            var finalDeltaX = if(viewHolder.getForegroundTask().x + viewHolder.getForegroundTask().width + dX < viewHolder.getBackgroundTask().x) {
                viewHolder.getBackgroundTask().x - (viewHolder.getForegroundTask().x + viewHolder.getForegroundTask().width)
            } else {
                dX
            }

            getDefaultUIUtil().onDraw(c, recyclerView, viewHolder.getForegroundTask(), finalDeltaX, dY, actionState, isCurrentlyActive)
            *//*if(viewHolder.getForegroundTask().x + viewHolder.getForegroundTask().width < viewHolder.getBackgroundTask().x && dX < 0) return
            getDefaultUIUtil().onDraw(c, recyclerView, viewHolder.getForegroundTask(), dX, dY, actionState, isCurrentlyActive)*//*
        }
    }*/

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if(viewHolder != null && viewHolder is SimpleInfoViewHolder) {
            getDefaultUIUtil().onSelected(viewHolder.getForegroundTask())
        }
    }

    fun undo() {
        if(selectedViewHolder != null) {
            getDefaultUIUtil().clearView(selectedViewHolder!!.getForegroundTask())
            selectedViewHolder = null
        }
    }

}