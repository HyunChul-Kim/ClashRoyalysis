package com.app.chul.clashroyalysis.`interface`

interface BaseFragmentInterface<in T> {
    fun scrollTop()
    fun refresh()
    fun setData(data : T)
}