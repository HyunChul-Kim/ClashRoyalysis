package com.app.chul.clashroyalysis.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.app.chul.clashroyalysis.callback.ObservableScrollCallback

class ObservableRecyclerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private lateinit var mCallback: ObservableScrollCallback

    init {
        init()
    }

    private fun init() {
        super.addOnScrollListener(object: OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                mCallback?.run {
                    onScrollChanged(dy)
                }
            }
        })
    }

    fun setCallback(callback: ObservableScrollCallback) {
        mCallback = callback
    }
}