package com.app.chul.clashroyalysis.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

abstract class RxBusActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        registerRxBus()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun registerRxBus()
}