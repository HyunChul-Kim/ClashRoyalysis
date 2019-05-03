package com.app.chul.clashroyalysis.listener

import android.app.Fragment
import android.support.v7.widget.RecyclerView
import com.app.chul.clashroyalysis.view.FragmentTabView

interface FragmentStateListener {
    fun onActivityCreated(type: FragmentTabView.TabType, recyclerView: RecyclerView)
}