package com.app.chul.clashroyalysis.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.app.chul.clashroyalysis.fragment.PopularDeckFragment
import com.app.chul.clashroyalysis.fragment.RegisterFragment

class RegisterViewPagerAdapter(fm: FragmentManager, context: Context): FragmentStatePagerAdapter(fm) {

    private val TAB_COUNT = 2

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> RegisterFragment.newInstance()
            1 -> PopularDeckFragment.newInstance()
            else -> RegisterFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return TAB_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Register"
            1 -> "Popular"
            else -> "Register"
        }
    }

}