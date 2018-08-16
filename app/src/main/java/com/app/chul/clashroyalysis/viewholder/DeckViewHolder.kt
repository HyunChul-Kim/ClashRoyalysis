package com.app.chul.clashroyalysis.viewholder

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.adapter.DeckAdapter
import com.app.chul.clashroyalysis.jsonobject.CardData
import com.app.chul.clashroyalysis.jsonobject.DeckInfo

class DeckViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val recyclerView = itemView.findViewById<RecyclerView>(R.id.deck_recycler_view)
    private val adapter = DeckAdapter()

    private lateinit var mDeck: DeckInfo

    init {
        recyclerView.layoutManager = GridLayoutManager(itemView.context, 4)
        recyclerView.adapter = adapter
    }

    fun bindData(deck: DeckInfo) {
        mDeck = deck
        adapter.setData(mDeck.cards)
    }
}