package com.app.chul.clashroyalysis.utils

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class DragAndDropHelperCallback(listenerAnd: DragAndDropHelperCallback.DragAndDropListener): ItemTouchHelper.Callback() {
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlag = ItemTouchHelper.LEFT

        return makeMovementFlags(dragFlag, swipeFlag)
    }

    override fun onMove(p0: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        if(viewHolder != null && target != null) {
            return mListener.itemMoved(viewHolder.adapterPosition, viewHolder.adapterPosition)
        }

        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, p1: Int) {
        if(viewHolder != null) {
            mListener.itemSwiped(viewHolder.adapterPosition)
        }
    }

    var mListener = listenerAnd

    interface DragAndDropListener {
        fun itemMoved(position: Int, targetPosition: Int): Boolean
        fun itemSwiped(position: Int)
    }

}