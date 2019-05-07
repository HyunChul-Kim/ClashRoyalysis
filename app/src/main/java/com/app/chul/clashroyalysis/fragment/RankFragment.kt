package com.app.chul.clashroyalysis.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.`interface`.BaseFragmentInterface
import com.app.chul.clashroyalysis.adapter.TopPlayerAdapter
import com.app.chul.clashroyalysis.jsonobject.TopPlayerList
import com.app.chul.clashroyalysis.listener.FragmentStateListener
import com.app.chul.clashroyalysis.view.FragmentTabView
import kotlinx.android.synthetic.main.fragment_rank.*

class RankFragment: Fragment(), BaseFragmentInterface<TopPlayerList> {

    private var location = "KR"
    private var page = 1
    private val max = 50

    private var rankList = TopPlayerList()
    private var adapter: TopPlayerAdapter ?= null

    private var fragmentListener: FragmentStateListener?= null

    override fun scrollTop() {
        rank_recycler_view.scrollToPosition(0)
    }

    override fun refresh() {
        adapter?.setData(rankList)
    }

    override fun setData(data: TopPlayerList) {
        rankList = data
        adapter?.setData(rankList)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rank, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentListener?.onActivityCreated(FragmentTabView.TabType.Home, rank_recycler_view)
        initRecyclerView()
        initAdapter()
    }

    private fun initRecyclerView() {
        rank_recycler_view.layoutManager = LinearLayoutManager(activity)
        rank_recycler_view.setHasFixedSize(false)
    }

    private fun initAdapter() {
        adapter = TopPlayerAdapter(activity)
        adapter?.setData(rankList)
        rank_recycler_view.adapter = adapter
    }

    fun setFragmentListener(listener: FragmentStateListener?) {
        fragmentListener = listener
    }
}