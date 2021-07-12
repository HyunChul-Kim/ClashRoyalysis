package com.app.chul.clashroyalysis.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.preference.RoyalysisPreferenceManager
import com.google.android.gms.ads.MobileAds

class IntroActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        initAds()
        checkUserInfo()
    }

    private fun checkUserInfo(){
        val intent =  Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun initAds() {
        MobileAds.initialize(this)
    }
}
