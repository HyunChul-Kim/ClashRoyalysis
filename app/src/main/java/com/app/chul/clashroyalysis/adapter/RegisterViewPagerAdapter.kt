package com.app.chul.clashroyalysis.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.app.chul.clashroyalysis.fragment.PopularDeckFragment
import com.app.chul.clashroyalysis.fragment.RankFragment
import com.app.chul.clashroyalysis.fragment.RegisterFragment

const val TAB_COUNT = 3

class RegisterViewPagerAdapter(fm: FragmentManager, context: Context): FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> RegisterFragment.getInstance()
            1 -> PopularDeckFragment.newInstance()
            2 -> RankFragment.newInstance()
            else -> RegisterFragment.getInstance()
        }
    }

    override fun getCount(): Int {
        return TAB_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Register"
            1 -> "Popular"
            2 -> "Rank"
            else -> "Register"
        }
    }

}