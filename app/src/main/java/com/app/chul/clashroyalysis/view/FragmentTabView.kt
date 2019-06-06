package com.app.chul.clashroyalysis.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.design.widget.TabLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.listener.TabChangedListener

class FragmentTabView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    enum class TabType {
        Home, Deck, Rank
    }

    private val tabLayout: TabLayout

    private var tabChangedListener: TabChangedListener ?= null
    private var selectedTab: TabType = TabType.Home

    init {
        LayoutInflater.from(getContext()).inflate(R.layout.view_fragment_tab, this, true)
        tabLayout = findViewById(R.id.fragment_tab_layout)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                tabChangedListener?.onTabReselected()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.text?.let { type ->
                    setSelectedTab(type.toString())
                }
            }

        })
    }

    private fun setSelectedTab(tab: String) {
        when {
            TabType.Home.name.equals(tab, true) -> {
                selectedTab = TabType.Home
                tabChangedListener?.onTabSelected(TabType.Home)
            }
            TabType.Deck.name.equals(tab, true) -> {
                selectedTab = TabType.Deck
                tabChangedListener?.onTabSelected(TabType.Deck)
            }
            TabType.Rank.name.equals(tab, true) -> {
                selectedTab = TabType.Rank
                tabChangedListener?.onTabSelected(TabType.Rank)
            }
        }
    }

    fun setTabChangedListener(listener: TabChangedListener?) {
        if(listener != null) {
            tabChangedListener = listener
        }
    }

    fun getSelectedTab() = selectedTab

}