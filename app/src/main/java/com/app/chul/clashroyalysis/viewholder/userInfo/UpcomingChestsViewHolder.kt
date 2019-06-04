package com.app.chul.clashroyalysis.viewholder.userInfo

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.adapter.UpcomingChestsAdapter
import com.app.chul.clashroyalysis.dto.Chest

class UpcomingChestsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val recyclerView = itemView.findViewById<RecyclerView>(R.id.upcoming_chests_recycler_view)
    private val message = itemView.findViewById<TextView>(R.id.upcoming_chests_message)
    private val adapter by lazy { UpcomingChestsAdapter(itemView.context) }

    init {
        recyclerView.layoutManager = GridLayoutManager(itemView.context, 4)
        recyclerView.adapter = adapter
    }

    fun bind(chestList: ArrayList<Chest>) {
        if(chestList.size > 0) {
            message.visibility = View.INVISIBLE
            adapter.setData(chestList)
        } else {
            message.visibility = View.VISIBLE
            message.text = "chests info loading.."
        }
    }

}