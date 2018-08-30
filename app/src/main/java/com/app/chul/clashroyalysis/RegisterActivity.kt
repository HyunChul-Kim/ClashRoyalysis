package com.app.chul.clashroyalysis

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.app.chul.clashroyalysis.adapter.RegisterViewPagerAdapter
import com.app.chul.clashroyalysis.bus.RxBus
import com.app.chul.clashroyalysis.bus.RxEvent
import com.app.chul.clashroyalysis.preference.RoyalysisPreferenceManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: AppCompatActivity() {

    private fun registerRxBus() {
        RxBus.register(this, RxBus.listen(RxEvent.EventAddTag::class.java).subscribe {
            RoyalysisPreferenceManager.addUser(it.tag)
        })
    }

    private val mAdapter : RegisterViewPagerAdapter by lazy {
        RegisterViewPagerAdapter(supportFragmentManager, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var uri: Uri = Uri.parse("https://images7.alphacoders.com/856/856231.jpg")
        Glide.with(applicationContext).load(uri).into(header_image_view)

        registerRxBus()
        register_view_pager.adapter = mAdapter
        register_tab.setupWithViewPager(register_view_pager)
    }

}