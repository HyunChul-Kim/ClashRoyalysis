package com.app.chul.clashroyalysis.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.DeckInfo
import com.app.chul.clashroyalysis.viewholder.DeckViewHolder

class DeckListAdapter(private val context: Context?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mDeckList = ArrayList<DeckInfo>()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var viewHolder = holder as DeckViewHolder
        viewHolder.bindData(mDeckList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_deck, parent, false)
        return DeckViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mDeckList.size
    }

    fun setData(list: ArrayList<DeckInfo>) {
        mDeckList = list
        notifyDataSetChanged()
    }

    fun addData(list: ArrayList<DeckInfo>) {
        mDeckList.addAll(list)
        notifyDataSetChanged()
    }
}