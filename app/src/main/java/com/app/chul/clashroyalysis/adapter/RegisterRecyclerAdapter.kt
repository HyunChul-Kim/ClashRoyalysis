package com.app.chul.clashroyalysis.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.UserData
import com.app.chul.clashroyalysis.jsonobject.UserDataList
import com.app.chul.clashroyalysis.viewholder.EmptySimpleInfoViewHolder
import com.app.chul.clashroyalysis.viewholder.SimpleInfoViewHolder

class RegisterRecyclerAdapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mUserList = ArrayList<UserData>()

    object ViewType {
        const val ADD_VIEW_TYPE = 0
        const val USER_VIEW_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ViewType.ADD_VIEW_TYPE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.empty_simple_info_viewholder, parent, false)
                EmptySimpleInfoViewHolder(view)
            }
            ViewType.USER_VIEW_TYPE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.user_simple_info_viewholder, parent, false)
                SimpleInfoViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(context).inflate(R.layout.empty_simple_info_viewholder, parent, false)
                EmptySimpleInfoViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return mUserList.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            ViewType.USER_VIEW_TYPE -> {
                val viewHolder = holder as SimpleInfoViewHolder
                viewHolder.bind(mUserList.get(index = position))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position == itemCount - 1) {
            return ViewType.ADD_VIEW_TYPE
        }else {
            return ViewType.USER_VIEW_TYPE
        }
    }

    fun setData(data: List<UserData>) {
        mUserList = data as ArrayList<UserData>
        notifyDataSetChanged()
    }

    fun setData(data: UserData) {
        mUserList.clear()
        mUserList.add(data)
        notifyDataSetChanged()
    }

}