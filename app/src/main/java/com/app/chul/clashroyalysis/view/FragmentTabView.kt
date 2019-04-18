package com.app.chul.clashroyalysis.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.listener.TabChangedListener

class FragmentTabView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {

    enum class TabType {
        Home, Deck, Rank
    }

    private val favoriteTab: TextView
    private val popularDeckTab: TextView
    private val topPlayerTab: TextView

    private var tabChangedListener: TabChangedListener ?= null
    private var selectedTab: TabType = TabType.Home

    init {
        LayoutInflater.from(getContext()).inflate(R.layout.view_fragment_tab, this, true)
        favoriteTab = findViewById(R.id.fragment_tab_register)
        popularDeckTab = findViewById(R.id.fragment_tab_popular)
        topPlayerTab = findViewById(R.id.fragment_tab_top_player)

        favoriteTab.setOnClickListener(this)
        popularDeckTab.setOnClickListener(this)
        topPlayerTab.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            favoriteTab -> {
                selectedTab = TabType.Home
                tabChangedListener?.onChanged(TabType.Home)
            }
            popularDeckTab -> {
                selectedTab = TabType.Deck
                tabChangedListener?.onChanged(TabType.Deck)
            }
            topPlayerTab -> {
                selectedTab = TabType.Rank
                tabChangedListener?.onChanged(TabType.Rank)
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