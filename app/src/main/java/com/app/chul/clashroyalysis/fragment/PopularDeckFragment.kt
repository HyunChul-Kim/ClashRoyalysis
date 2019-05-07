package com.app.chul.clashroyalysis.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.`interface`.BaseFragmentInterface
import com.app.chul.clashroyalysis.adapter.DeckListAdapter
import com.app.chul.clashroyalysis.jsonobject.PopularDeckList
import com.app.chul.clashroyalysis.listener.FragmentStateListener
import com.app.chul.clashroyalysis.view.FragmentTabView
import kotlinx.android.synthetic.main.fragment_popular_deck.*

class PopularDeckFragment: Fragment(), BaseFragmentInterface<PopularDeckList> {

    override fun scrollTop() {
        popular_deck_recycler_view?.scrollToPosition(0)
    }

    override fun refresh() {
        adapter?.setData(deckList)
    }

    override fun setData(data: PopularDeckList) {
        deckList = data
        adapter?.setData(deckList)
    }

    private var page = 0
    private var deckList = PopularDeckList()
    private var adapter : DeckListAdapter ?= null

    private var fragmentListener: FragmentStateListener?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular_deck, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentListener?.onActivityCreated(FragmentTabView.TabType.Home, popular_deck_recycler_view)
        initRecyclerView()
        initAdapter()
    }

    private fun initRecyclerView() {
        popular_deck_recycler_view.layoutManager = LinearLayoutManager(activity)
        popular_deck_recycler_view.setHasFixedSize(true)
    }

    private fun initAdapter() {
        adapter = DeckListAdapter(activity)
        adapter?.setData(deckList)
        popular_deck_recycler_view.adapter = adapter
    }

    fun setFragmentListener(listener: FragmentStateListener?) {
        fragmentListener = listener
    }
}