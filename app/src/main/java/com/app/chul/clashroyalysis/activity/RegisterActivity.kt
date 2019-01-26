package com.app.chul.clashroyalysis.activity

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.`interface`.BaseFragmentInterface
import com.app.chul.clashroyalysis.adapter.RegisterViewPagerAdapter
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: BaseActivity() {

    lateinit var adView: AdView

    private val mAdapter : RegisterViewPagerAdapter by lazy {
        RegisterViewPagerAdapter(supportFragmentManager, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

//        var uri: Uri = Uri.parse("https://images7.alphacoders.com/856/856231.jpg")
//        Glide.with(applicationContext).load(uri).into(header_image_view)

        initAd()
        initViewPager()

    }

    private fun initAd() {
        adView = findViewById(R.id.register_top_banner)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    private fun initViewPager() {
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