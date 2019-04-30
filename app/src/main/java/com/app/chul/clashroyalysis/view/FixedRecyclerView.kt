package com.app.chul.clashroyalysis.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

class FixedRecyclerView @JvmOverloads constructor(context: Context, attrs: AttributeSet ?= null, defStyleAttrs: Int = 0)
    :RecyclerView(context, attrs, defStyleAttrs) {

    override fun canScrollVertically(direction: Int): Boolean {
        if(direction < 1) {
            val original = super.canScrollVertically(direction)
            return !original && getChildAt(0) != null && getChildAt(0).top < 0 || original
        }
        return super.canScrollVertically(direction)
    }
}