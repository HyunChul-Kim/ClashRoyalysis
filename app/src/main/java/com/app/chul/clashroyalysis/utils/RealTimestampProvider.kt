package com.app.chul.clashroyalysis.utils

class RealTimestampProvider: TimestampProvider {

    override fun timeStamp(): Long {
        return System.currentTimeMillis()
    }

}