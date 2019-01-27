package com.app.chul.clashroyalysis.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.`interface`.BaseFragmentInterface
import com.app.chul.clashroyalysis.adapter.TopPlayerAdapter
import com.app.chul.clashroyalysis.jsonobject.TopPlayerList
import com.app.chul.clashroyalysis.retrofit.ClashRoyaleRetrofit
import kotlinx.android.synthetic.main.fragment_rank.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RankFragment: Fragment(), BaseFragmentInterface {

    companion object {
        fun newInstance(): Fragment {
            return RankFragment()
        }
    }

    private var location = "KR"
    private var page = 1
    private val max = 50

    private val adapter: TopPlayerAdapter by lazy {
        TopPlayerAdapter(context)
    }

    override fun scrollTop() {
        rank_recycler_view.scrollToPosition(0)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rank, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        requestTopPlayerList()
    }

    private fun initRecyclerView() {
        rank_recycler_view.layoutManager = LinearLayoutManager(context)
        rank_recycler_view.adapter = adapter
        rank_recycler_view.setHasFixedSize(false)
    }

    private fun requestTopPlayerList() {
        val topPlayerListCall = ClashRoyaleRetrofit.getService().getTopPlayers(location, max)
        topPlayerListCall.enqueue(object: Callback<TopPlayerList> {
            override fun onFailure(call: Call<TopPlayerList>?, t: Throwable?) {
                Log.i("TopPlayer Service", t.toString())
            }

            override fun onResponse(call: Call<TopPlayerList>?, response: Response<TopPlayerList>?) {
                response?.body()?.let {
                    adapter.setData(it)
                }
            }

        })
    }
}