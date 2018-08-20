package com.app.chul.clashroyalysis

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.app.chul.clashroyalysis.adapter.RegisterRecyclerAdapter
import com.app.chul.clashroyalysis.bus.RxBus
import com.app.chul.clashroyalysis.bus.RxEvent
import com.app.chul.clashroyalysis.preference.RoyalysisPreferenceManager
import com.app.chul.clashroyalysis.retrofit.ClashRoyaleRetrofit
import com.bumptech.glide.Glide
import com.app.chul.clashroyalysis.utils.isAvailableTag
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: AppCompatActivity() {

    private fun registerRxBus() {
        /*RxBus.getObservable().subscribe {
            if(it == RxEvent.EventAddTag::class.java) {
                RoyalysisPreferenceManager.addUser((it as RxEvent.EventAddTag).tag)
                initUserInfo()
            }
        }*/
        RxBus.register(this, RxBus.listen(RxEvent.EventAddTag::class.java).subscribe {
            RoyalysisPreferenceManager.addUser(it.tag)
            initUserInfo()
        })
    }

    private val mAdapter : RegisterRecyclerAdapter by lazy {
        RegisterRecyclerAdapter(this@RegisterActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var uri: Uri = Uri.parse("https://images7.alphacoders.com/856/856231.jpg")
        Glide.with(applicationContext).load(uri).into(header_image_view)

        initRecyclerView()
        initUserInfo()

        registerRxBus()
        register_tab_2.setOnClickListener{
            val intent = Intent(this, PopularDeckActivity::class.java)
            startActivity(intent)
        }

        /*register_btn.setOnClickListener {
            val intent = Intent(this, PopularDeckActivity::class.java)
            startActivity(intent)
            *//*if(isAvailableTag(getUserTag())){
                RoyalysisPreferenceManager.addUser(getUserTag())
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("tag", getUserTag())
                startActivity(intent)
            }*//*
        }*/
    }

    private fun initRecyclerView() {
        register_recycler_view.layoutManager = LinearLayoutManager(this@RegisterActivity)
        register_recycler_view.adapter = mAdapter
        register_recycler_view.setHasFixedSize(true)
    }

    private fun initUserInfo() {
        val userList: ArrayList<String> = RoyalysisPreferenceManager.getUserList()
        mAdapter.setData(userList)
        register_loading_cover.visibility = View.GONE
    }

    private fun getUserTag(): String {
        /*return if(register_id.text.toString().contains("#")) {
            register_id.text.toString().replace("#", "")
        }else{
            register_id.text.toString()
        }*/
        return ""
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if(ev?.action == MotionEvent.ACTION_UP){
            /*if(register_id.isFocusable){
                val outRect = Rect()
                register_id.getGlobalVisibleRect(outRect)
                if(!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())){
                    register_id.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(register_id.windowToken, 0)
                }
            }*/
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}