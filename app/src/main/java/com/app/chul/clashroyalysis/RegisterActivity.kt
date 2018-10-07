package com.app.chul.clashroyalysis

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.app.chul.clashroyalysis.`interface`.BaseFragmentInterface
import com.app.chul.clashroyalysis.adapter.RegisterViewPagerAdapter
import com.app.chul.clashroyalysis.bus.RxBus
import com.app.chul.clashroyalysis.bus.RxEvent
import com.app.chul.clashroyalysis.preference.RoyalysisPreferenceManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: AppCompatActivity() {

    private val mAdapter : RegisterViewPagerAdapter by lazy {
        RegisterViewPagerAdapter(supportFragmentManager, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var uri: Uri = Uri.parse("https://images7.alphacoders.com/856/856231.jpg")
        Glide.with(applicationContext).load(uri).into(header_image_view)

        register_view_pager.adapter = mAdapter
        register_tab.setupWithViewPager(register_view_pager)
        register_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                (supportFragmentManager.fragments[register_tab.selectedTabPosition] as BaseFragmentInterface).scrollTop()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

        })
    }

}