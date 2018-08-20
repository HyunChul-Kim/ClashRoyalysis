package com.app.chul.clashroyalysis.callback

interface ObservableScrollCallback {

    fun onScrollChanged(dy: Int)

    fun onDownMotion()

    fun onUpOrCancelMotion()
}