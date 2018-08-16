package com.app.chul.clashroyalysis.utils

import android.view.View
import com.app.chul.clashroyalysis.callbacks.ObservableCallBack

class ScrollController: ObservableCallBack {

    private val HEADER_SPEED: Float = 1f

    private lateinit var header: View
    private var headerScrollHeight: Float = 0f

    override fun onScrollChanged(dy: Int) {
        val scrollY = dy / HEADER_SPEED
        var headerTranslationY = header.translationY - scrollY

        if(dy >= 0) {
            if(headerTranslationY < -headerScrollHeight) {
                headerTranslationY = headerScrollHeight
            }
        }

        if(dy < 0) {
            if(headerTranslationY > 0) {
                headerTranslationY = 0f
            }
        }

        header.translationY = headerTranslationY
    }

}