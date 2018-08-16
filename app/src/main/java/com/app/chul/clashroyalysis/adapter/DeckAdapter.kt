package com.app.chul.clashroyalysis.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.CardData
import com.app.chul.clashroyalysis.utils.getInflatedView
import com.app.chul.clashroyalysis.viewholder.CardViewHolder

class DeckAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mCardList = ArrayList<CardData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CardViewHolder(getInflatedView(R.layout.card_view_holder, parent, viewType))
    }

    override fun getItemCount(): Int {
        return mCardList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var viewHolder = holder as CardViewHolder
        viewHolder.bindData(mCardList[position])
    }

    fun setData(list: List<CardData>) {
        mCardList = list as ArrayList<CardData>
        notifyDataSetChanged()
    }
}