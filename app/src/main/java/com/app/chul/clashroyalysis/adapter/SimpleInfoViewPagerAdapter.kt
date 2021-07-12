package com.app.chul.clashroyalysis.adapter

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.app.chul.clashroyalysis.R

class SimpleInfoViewPagerAdapter(context: Context): PagerAdapter() {

    private val context = context

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view =  getItem(position, container)
        setItem(position, view)
        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return 3
    }

    private fun getItem(position: Int, parent: ViewGroup): View {
        return when(position) {
            0 -> LayoutInflater.from(context).inflate(R.layout.view_card_list, parent, false)
            1 -> LayoutInflater.from(context).inflate(R.layout.viewholder_user_simple_info, parent, false)
            2 -> LayoutInflater.from(context).inflate(R.layout.viewholder_user_simple_info, parent, false)
            else -> LayoutInflater.from(context).inflate(R.layout.viewholder_user_simple_info, parent, false)
        }
    }

    private fun setItem(position: Int, itemView: View) {
        when(position) {
            0 -> {}
            1 -> {}
            2 -> {}
            else -> {}
        }
    }

}