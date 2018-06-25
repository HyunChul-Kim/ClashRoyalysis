package com.app.chul.clashroyalysis

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.app.chul.clashroyalysis.preference.RoyalysisPreferenceManager

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        checkUserInfo()
    }

    private fun checkUserInfo(){
        val tag = RoyalysisPreferenceManager.getRegisters(this@IntroActivity)
        if(!TextUtils.isEmpty(tag)){
            val intent =  Intent(this, RegisterActivity::class.java)
            intent.putExtra("tag", tag)
            startActivity(intent)
        }else{
            val intent =  Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
