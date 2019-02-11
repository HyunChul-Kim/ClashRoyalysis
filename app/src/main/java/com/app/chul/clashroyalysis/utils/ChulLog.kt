package com.app.chul.clashroyalysis.utils

import com.app.chul.clashroyalysis.BuildConfig

class ChulLog {
    companion object {
        const val TAG = "chul"
        fun log(tag: String, msg: String) {
            if(BuildConfig.DEBUG) {
                val log = "#DEBUG TID:${Thread.currentThread().id} #$tag [ $msg ]"
                System.out.println(log)
            }
        }

        fun log(msg: String) {
            if(BuildConfig.DEBUG) {
                val log = "#DEBUG TID:${Thread.currentThread().id} #$TAG [ $msg ]"
                System.out.println(log)
            }
        }
    }
}