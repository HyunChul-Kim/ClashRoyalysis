package com.app.chul.clashroyalysis.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.UserData
import com.app.chul.clashroyalysis.viewholder.home.UserProfileViewHolder

class HomeRecyclerAdapter(private val mContext: Context): Adapter<RecyclerView.ViewHolder>() {

    private val USER_PROFILE = 1
    private val EMPTY = 0

    private var mHolderList: ArrayList<Int> = ArrayList()
    private var mUserData: UserData? = null

    init {
        refreshMap()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.user_profile_viewholder, parent, false)
        return UserProfileViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(mHolderList != null) mHolderList.size
        else 0
    }

    override fun getItemViewType(position: Int): Int {
        return mHolderList?.get(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        mUserData?.run {
            (holder as UserProfileViewHolder).bind(name, trophies)
        }
    }

    private fun refreshMap() {
        mHolderList = ArrayList<Int>()
        mHolderList.add(USER_PROFILE)
    }

    fun setData(data: UserData){
        mUserData = data
        notifyDataSetChanged()
    }

}