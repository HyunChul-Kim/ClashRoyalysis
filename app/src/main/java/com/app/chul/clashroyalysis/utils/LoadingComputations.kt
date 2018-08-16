package com.app.chul.clashroyalysis.utils

class LoadingComputations(fl: Float) {

    companion object {
        const val ANIMATION_LENGTH: Long = 1000
    }

    private var density: Float = fl

    fun dpToPx(size: Int): Float {
        return size * density
    }

    fun verticalPosition(time: Long, offset: Long): Double {
        val x = 2 * Math.PI * (time + offset) / ANIMATION_LENGTH
        return (Math.sin(x) + 1) / 2
    }

}