package com.app.chul.clashroyalysis.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.adapter.DeckListAdapter
import com.app.chul.clashroyalysis.jsonobject.PopularDeckList
import com.app.chul.clashroyalysis.retrofit.ClashRoyaleRetrofit
import kotlinx.android.synthetic.main.popular_deck_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularDeckFragment: Fragment() {

    private var page = 0
    private val adapter : DeckListAdapter by lazy {
        DeckListAdapter(context)
    }

    companion object {
        fun newInstance(): Fragment {
            return PopularDeckFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.popular_deck_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        requestPopularDeckList()
        test.setOnClickListener {
            page++
            requestMorePopularDeckList(page)
        }
    }

    private fun initRecyclerView() {
        popular_deck_recycler_view.layoutManager = LinearLayoutManager(context)
        popular_deck_recycler_view.adapter = adapter
        popular_deck_recycler_view.setHasFixedSize(true)
    }

    private fun requestPopularDeckList() {
        val popularDeckCall = ClashRoyaleRetrofit.getService().getPopularDecks()
        popularDeckCall.enqueue(object: Callback<PopularDeckList> {

            override fun onFailure(call: Call<PopularDeckList>?, t: Throwable?) {
                Log.i("Popular Service", t.toString())
            }

            override fun onResponse(call: Call<PopularDeckList>?, response: Response<PopularDeckList>?) {
                Log.i("Popular Service", "Response Success")
                response?.body()?.let {
                    adapter.setData(it)
                    Toast.makeText(context, "Size : " + it.size, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun requestMorePopularDeckList(page: Int) {
        val popularDeckCall = ClashRoyaleRetrofit.getService().getPopularDecks(page)
        popularDeckCall.enqueue(object: Callback<PopularDeckList> {

            override fun onFailure(call: Call<PopularDeckList>?, t: Throwable?) {
                Log.i("Popular Service", t.toString())
            }

            override fun onResponse(call: Call<PopularDeckList>?, response: Response<PopularDeckList>?) {
                Log.i("Popular Service", "Response Success")
                response?.body()?.let {
                    adapter.addData(it)
                    Toast.makeText(context, "Size : " + it.size + "Page : " + page, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}