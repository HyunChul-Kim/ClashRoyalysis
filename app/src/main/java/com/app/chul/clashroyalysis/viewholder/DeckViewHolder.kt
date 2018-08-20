package com.app.chul.clashroyalysis.viewholder

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.adapter.DeckAdapter
import com.app.chul.clashroyalysis.jsonobject.DeckInfo

class DeckViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val popularityTextView = itemView.findViewById<TextView>(R.id.deck_popularity)
    private val shareBtn = itemView.findViewById<TextView>(R.id.deck_link_button)
    private val recyclerView = itemView.findViewById<RecyclerView>(R.id.deck_recycler_view)
    private val adapter = DeckAdapter()

    private lateinit var mDeck: DeckInfo

    init {
        recyclerView.layoutManager = GridLayoutManager(itemView.context, 4)
        recyclerView.adapter = adapter

        shareBtn.setOnClickListener {

        }
    }

    fun bindData(deck: DeckInfo) {
        mDeck = deck
        popularityTextView.text = mDeck?.popularity.toString()
        adapter.setData(mDeck.cards)
    }

    private fun setPopularity(rank: Int) {
        val stringBuilder = StringBuilder(popularityTextView.text)
        stringBuilder.append(" ")
        stringBuilder.append(rank)

        popularityTextView.text = stringBuilder.toString()
    }
}