package com.app.chul.clashroyalysis.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.CardData
import com.app.chul.clashroyalysis.viewholder.CardViewHolder

class CardListAdapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mCardList = ArrayList<CardData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var viewHolder = holder as CardViewHolder
        viewHolder.bindData(mCardList[position])
    }

    override fun getItemCount(): Int {
        return mCardList.size
    }

    fun setData(data: ArrayList<CardData>) {
        mCardList = data
        notifyDataSetChanged()
    }

}