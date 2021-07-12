package com.app.chul.clashroyalysis.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class RxBusActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        registerRxBus()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun registerRxBus()
}