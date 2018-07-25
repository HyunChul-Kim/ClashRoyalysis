package com.app.chul.clashroyalysis

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.app.chul.clashroyalysis.adapter.RegisterRecyclerAdapter
import com.app.chul.clashroyalysis.jsonobject.UserData
import com.app.chul.clashroyalysis.preference.RoyalysisPreferenceManager
import com.app.chul.clashroyalysis.retrofit.ClashRoyaleRetrofit
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity: AppCompatActivity() {

    private val mAdapter : RegisterRecyclerAdapter by lazy {
        RegisterRecyclerAdapter(this@RegisterActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initRecyclerView()
        initUserInfo()

        register_btn.setOnClickListener {
            if(checkUserTag()){
                RoyalysisPreferenceManager.setUsers(this@RegisterActivity, getUserTag())
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("tag", getUserTag())
                startActivity(intent)
            }
        }
    }

    private fun initRecyclerView() {
        register_recycler_view.layoutManager = LinearLayoutManager(this@RegisterActivity)
        register_recycler_view.adapter = mAdapter
        register_recycler_view.setHasFixedSize(true)
    }

    private fun initUserInfo() {
        if(intent.hasExtra("tag")){
            val tag = intent.getStringExtra("tag")
            val userData = ClashRoyaleRetrofit.getService().getPlayer(tag)
            userData.enqueue(object: Callback<UserData> {
                override fun onFailure(call: Call<UserData>?, t: Throwable?) {
                    Toast.makeText(this@RegisterActivity, "API Fail", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<UserData>?, response: Response<UserData>?) {
                    register_loading_cover.visibility = View.GONE
                    if(response?.body() != null){
                        mAdapter.setData(response.body() as UserData)
                    }
                }

            })
        }else {
            register_loading_cover.visibility = View.GONE
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

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if(ev?.action == MotionEvent.ACTION_UP){
            if(register_id.isFocusable){
                val outRect = Rect()
                register_id.getGlobalVisibleRect(outRect)
                if(!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())){
                    register_id.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(register_id.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}