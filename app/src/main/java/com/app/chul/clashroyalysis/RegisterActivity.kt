package com.app.chul.clashroyalysis

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.app.chul.clashroyalysis.preference.RoyalysisPreferenceManager
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        register_btn.setOnClickListener {
            if(checkUserTag()){
                RoyalysisPreferenceManager.setRegisters(this@RegisterActivity, getUserTag())
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("tag", getUserTag())
                startActivity(intent)
            }
        }
    }

    private fun checkUserTag(): Boolean {
        val tag = getUserTag()
        return if(!TextUtils.isEmpty(tag)){
            tag.length == 8
        }else{
            false
        }
    }

    private fun getUserTag(): String {
        return if(register_id.text.toString().contains("#")) {
            register_id.text.toString().replace("#", "")
        }else{
            register_id.text.toString()
        }
    }
}