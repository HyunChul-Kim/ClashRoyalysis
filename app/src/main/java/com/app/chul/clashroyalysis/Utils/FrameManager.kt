package com.app.chul.clashroyalysis.Utils

import java.util.concurrent.TimeUnit

class FrameManager(timestampProvider: TimestampProvider) {

    companion object {
        const val TIME_PER_FRAME: Long = 1000 / 60
    }

    private var fps: Int = 0
    private var frames: Int = 0

    private var lastTime: Long = 0
    private var timeSpan: Long = 0
    private var timeFrame: Long = 0

    private val timestampProvider = timestampProvider

    fun frame() {
        if(canGo()) {
            timeFrame %= TIME_PER_FRAME
        }

        var currentTime = timestampProvider.timeStamp()
        var timeStamp = currentTime - lastTime
        timeFrame += timeStamp
        timeSpan += timeStamp
        if(timeSpan > TimeUnit.SECONDS.toMillis(1)){
            fps = getFrameCount()
            timeSpan = 0
            frames = 0
        }

        if(canGo()) {
            frames++
        }
        lastTime = currentTime
    }

    fun getFrameCount(): Int {
        return frames
    }

    fun fps(): Int {
        return fps
    }

    fun canGo(): Boolean {
        return timeFrame > TIME_PER_FRAME
    }

}