package com.app.chul.clashroyalysis.Utils

class RealTimestampProvider: TimestampProvider {

    override fun timeStamp(): Long {
        return System.currentTimeMillis()
    }

}