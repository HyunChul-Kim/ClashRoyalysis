package com.app.chul.clashroyalysis.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.TopPlayerList
import com.app.chul.clashroyalysis.viewholder.rank.TopPlayerViewHolder

class TopPlayerAdapter(private val context: Context?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var topPlayerList: TopPlayerList = TopPlayerList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_top_player, parent, false)
        return TopPlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return topPlayerList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var viewHolder = holder as TopPlayerViewHolder
        viewHolder.bind(topPlayerList[position])
    }

    fun setData(list: TopPlayerList) {
        topPlayerList = list
        notifyDataSetChanged()
    }

}