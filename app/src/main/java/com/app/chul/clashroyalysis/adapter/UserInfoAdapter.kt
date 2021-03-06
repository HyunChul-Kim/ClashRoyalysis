package com.app.chul.clashroyalysis.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.PlayerData
import com.app.chul.clashroyalysis.viewholder.DoubleRateViewHolder
import com.app.chul.clashroyalysis.viewholder.ProgressViewHolder
import com.app.chul.clashroyalysis.viewholder.home.UserProfileViewHolder

class UserInfoAdapter(private val mContext: Context): Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val EMPTY = 0
        const val USER_PROFILE = 1
        const val USER_MAX_PROGRESS = 2
        const val TOP_PLAYER_MAX_PROGRESS = 3
        const val WIN_RATE_PROGRESS = 4
    }

    private var mHolderList: ArrayList<Int> = ArrayList()
    private var mPlayerData: PlayerData? = null
    private var mTopPlayerTrophy: Int = 0

    init {
        refreshMap()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            USER_PROFILE -> {
                val view: View = LayoutInflater.from(mContext).inflate(R.layout.viewholder_user_profile, parent, false)
                UserProfileViewHolder(view)
            }
            USER_MAX_PROGRESS -> {
                val view: View = LayoutInflater.from(mContext).inflate(R.layout.viewholder_progress, parent, false)
                ProgressViewHolder(view, mContext.getString(R.string.max_trophy))
            }
            TOP_PLAYER_MAX_PROGRESS -> {
                val view: View = LayoutInflater.from(mContext).inflate(R.layout.viewholder_progress, parent, false)
                ProgressViewHolder(view, mContext.getString(R.string.top_trophy))
            }
            WIN_RATE_PROGRESS -> {
                val view: View = LayoutInflater.from(mContext).inflate(R.layout.viewholder_double_rate, parent, false)
                DoubleRateViewHolder(view, mContext.getString(R.string.win_rate_title), mContext.getString(R.string.three_crown_title))
            }
            else -> {
                val view: View = LayoutInflater.from(mContext).inflate(R.layout.viewholder_user_profile, parent, false)
                UserProfileViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return mHolderList.size
    }

    override fun getItemViewType(position: Int): Int {
        return mHolderList[position]
    }

    private fun getItemPosition(viewType: Int): Int {
        return mHolderList.indexOf(viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            USER_PROFILE -> {
                mPlayerData?.let {
                    (holder as UserProfileViewHolder).bind(it)
                }
            }
            USER_MAX_PROGRESS -> {
                mPlayerData?.let {
                    (holder as ProgressViewHolder).bind(it.stats?.maxTrophies, it.trophies)
                }
            }
            TOP_PLAYER_MAX_PROGRESS -> {
                mPlayerData?.let {
                    (holder as ProgressViewHolder).bind(mTopPlayerTrophy, it.trophies)
                }
            }
            WIN_RATE_PROGRESS -> {
                mPlayerData?.let {
                    var threeCrownWins = 0
                    var wins = 0
                    var winPercent = 0f
                    it.stats?.let { threeCrownWins = it.threeCrownWins }
                    it.games?.let {
                        wins = it.wins
                        winPercent = it.winsPercent
                    }
                    (holder as DoubleRateViewHolder).bind(winPercent, (threeCrownWins / wins.toFloat()))
                }
            }
        }
    }

    private fun refreshMap() {
        mHolderList = ArrayList()
        if(mPlayerData != null) {
            mHolderList.add(USER_PROFILE)
            mHolderList.add(USER_MAX_PROGRESS)

            mHolderList.add(TOP_PLAYER_MAX_PROGRESS)

            mHolderList.add(WIN_RATE_PROGRESS)
        }
    }

    fun setData(data: PlayerData, trophy: Int){
        mPlayerData = data
        mTopPlayerTrophy = trophy
        refreshMap()
        notifyDataSetChanged()
    }

    fun setTopPlayerTrophy(trophy: Int?) {
        trophy?.let {
            mTopPlayerTrophy = trophy
            refreshMap()
            notifyItemChanged(getItemPosition(TOP_PLAYER_MAX_PROGRESS))
        }
    }

}