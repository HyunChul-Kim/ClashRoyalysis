package com.app.chul.clashroyalysis.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.dto.Chest
import com.app.chul.clashroyalysis.viewholder.ChestViewHolder

class UpcomingChestsAdapter(private val mContext: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var chestList = ArrayList<Chest>()

    fun setData(list: ArrayList<Chest>) {
        chestList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.viewholder_chest, parent, false)
        return ChestViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chestList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ChestViewHolder) {
            holder.bind(chestList[position])
        }
    }

}