package com.app.chul.clashroyalysis

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.app.chul.clashroyalysis.adapter.DeckListAdapter
import com.app.chul.clashroyalysis.jsonobject.PopularDeckList
import com.app.chul.clashroyalysis.retrofit.ClashRoyaleRetrofit
import kotlinx.android.synthetic.main.activity_popular_deck.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularDeckActivity : AppCompatActivity() {

    private val adapter : DeckListAdapter by lazy {
        DeckListAdapter(this@PopularDeckActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_deck)
        initRecyclerView()
        requestPopularDeckList()
    }

    private fun initRecyclerView() {
        popular_deck_recycler_view.layoutManager = LinearLayoutManager(this)
        popular_deck_recycler_view.adapter = adapter
        popular_deck_recycler_view.setHasFixedSize(true)
    }

    private fun requestPopularDeckList() {
        val popularDeckCall = ClashRoyaleRetrofit.getService().getPopularDecks()
        popularDeckCall.enqueue(object: Callback<PopularDeckList>{

            override fun onFailure(call: Call<PopularDeckList>?, t: Throwable?) {
                Log.i("Popular Service", t.toString())
            }

            override fun onResponse(call: Call<PopularDeckList>?, response: Response<PopularDeckList>?) {
                Log.i("Popular Service", "Response Success")
                response?.body()?.let {
                    adapter.setData(it)
                }
            }
        })
    }
}
