package com.app.chul.clashroyalysis.fragment

import android.app.Fragment
import android.os.Bundle
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

class RankFragment: Fragment(), BaseFragmentInterface<TopPlayerList> {

    companion object {
        fun getInstance(): Fragment {
            return RankFragment()
        }
    }

    private var location = "KR"
    private var page = 1
    private val max = 50

    private var rankList = TopPlayerList()
    private val adapter: TopPlayerAdapter by lazy {
        TopPlayerAdapter(activity)
    }

    override fun scrollTop() {
        rank_recycler_view.scrollToPosition(0)
    }

    override fun refresh() {

    }

    override fun setData(data: TopPlayerList) {
        rankList = data
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rank, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        rank_recycler_view.layoutManager = LinearLayoutManager(activity)
        rank_recycler_view.adapter = adapter
        rank_recycler_view.setHasFixedSize(false)
        adapter.setData(rankList)
    }
}