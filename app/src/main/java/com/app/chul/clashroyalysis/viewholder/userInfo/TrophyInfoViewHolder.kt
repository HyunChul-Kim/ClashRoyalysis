package com.app.chul.clashroyalysis.viewholder.userInfo

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.app.chul.clashroyalysis.R

class TrophyInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val mBestTrophy = itemView.findViewById<TextView>(R.id.trophy_info_max_trophy)
    private val mBestProgressBar = itemView.findViewById<ProgressBar>(R.id.trophy_info_max_progress)
    private val mBestNoDataFilter = itemView.findViewById<TextView>(R.id.trophy_info_max_no_data)

    private val mTopTrophy = itemView.findViewById<TextView>(R.id.trophy_info_top_trophy)
    private val mTopProgressBar = itemView.findViewById<ProgressBar>(R.id.trophy_info_top_progress)
    private val mTopNoDataFilter = itemView.findViewById<TextView>(R.id.trophy_info_top_no_data)

    fun bind(bestTrophy: Int?, topTrophy: Int?, currentTrophy: Int) {
        setBestTrophyInfo(bestTrophy, currentTrophy)
        setTopTrophyInfo(topTrophy, currentTrophy)
    }

    private fun setBestTrophyInfo(bestTrophy: Int?, currentTrophy: Int?) {
        bestTrophy?.let {
            if (bestTrophy > 0) {
                mBestNoDataFilter.visibility = View.GONE
                mBestProgressBar.max = bestTrophy
                mBestProgressBar.progress = currentTrophy ?: 0
                mBestTrophy.text = bestTrophy.toString()
            } else {
                mBestNoDataFilter.visibility = View.VISIBLE
            }
        }
    }

    private fun setTopTrophyInfo(topTrophy: Int?, currentTrophy: Int?) {
        topTrophy?.let {
            if (topTrophy > 0) {
                mTopNoDataFilter.visibility = View.GONE
                mTopProgressBar.max = topTrophy
                mTopProgressBar.progress = currentTrophy ?: 0
                mTopTrophy.text = topTrophy.toString()
            } else {
                mTopNoDataFilter.visibility = View.VISIBLE
            }
        }
    }

    fun onTopTrophyUpdate(topTrophy: Int?, currentTrophy: Int?) {
        setTopTrophyInfo(topTrophy, currentTrophy)
    }
}