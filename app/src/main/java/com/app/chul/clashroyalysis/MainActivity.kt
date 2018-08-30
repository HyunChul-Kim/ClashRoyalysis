package com.app.chul.clashroyalysis

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.app.chul.clashroyalysis.adapter.HomeRecyclerAdapter
import com.app.chul.clashroyalysis.jsonobject.PlayerData
import com.app.chul.clashroyalysis.retrofit.ClashRoyaleRetrofit
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var mAdapter: HomeRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        loadUserData()
    }

    private fun initRecyclerView() {
        mAdapter = HomeRecyclerAdapter(this@MainActivity)
        main_recycler_view.layoutManager = LinearLayoutManager(this@MainActivity)
        main_recycler_view.adapter = mAdapter
        main_recycler_view.setHasFixedSize(true)
    }

    private fun loadUserData() {
        if(intent.hasExtra("tag")) {
            val tag = intent.getStringExtra("tag")
            val userData = ClashRoyaleRetrofit.getService().getPlayer(tag)
            userData.enqueue(object: Callback<PlayerData>{
                override fun onFailure(call: Call<PlayerData>?, t: Throwable?) {
                    Toast.makeText(this@MainActivity, "API Fail", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<PlayerData>?, response: Response<PlayerData>?) {
                    if(response?.body() != null){
                        mAdapter?.setData(response.body() as PlayerData)
                    }
                }

            })
        }
    }
}
