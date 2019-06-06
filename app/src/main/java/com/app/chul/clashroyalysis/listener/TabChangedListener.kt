package com.app.chul.clashroyalysis.listener

import com.app.chul.clashroyalysis.view.FragmentTabView

interface TabChangedListener {
    fun onTabSelected(type: FragmentTabView.TabType)
    fun onTabReselected()
}