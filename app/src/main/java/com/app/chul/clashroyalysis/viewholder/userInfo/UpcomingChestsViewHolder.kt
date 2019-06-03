package com.app.chul.clashroyalysis.viewholder.userInfo

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.adapter.UpcomingChestsAdapter
import com.app.chul.clashroyalysis.dto.Chest

class UpcomingChestsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val recyclerView = itemView.findViewById<RecyclerView>(R.id.upcoming_chests_recycler_view)
    private val adapter by lazy { UpcomingChestsAdapter(itemView.context) }

    init {
        recyclerView.layoutManager = GridLayoutManager(itemView.context, 4)
        recyclerView.adapter = adapter
    }

    fun bind(chestList: ArrayList<Chest>) {
        adapter.setData(chestList)
    }

}