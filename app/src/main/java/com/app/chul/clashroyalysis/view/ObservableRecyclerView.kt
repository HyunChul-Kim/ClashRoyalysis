package com.app.chul.clashroyalysis.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.app.chul.clashroyalysis.callbacks.ObservableCallBack

class ObservableRecyclerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private lateinit var mCallBack: ObservableCallBack

    init {
        super.addOnScrollListener(object : OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                mCallBack?.run {
                    onScrollChanged(dy)
                }
            }
        })
    }

    fun setCallBack(callBack: ObservableCallBack) {
        mCallBack = callBack
    }

}