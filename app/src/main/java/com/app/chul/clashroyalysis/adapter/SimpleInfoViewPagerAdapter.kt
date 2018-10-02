package com.app.chul.clashroyalysis.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
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
            0 -> {
                val userDeckCard1 = itemView.findViewById<ImageView>(R.id.simple_deck_card1)
                val userDeckCard2 = itemView.findViewById<ImageView>(R.id.simple_deck_card2)
                val userDeckCard3 = itemView.findViewById<ImageView>(R.id.simple_deck_card3)
                val userDeckCard4 = itemView.findViewById<ImageView>(R.id.simple_deck_card4)
                val userDeckCard5 = itemView.findViewById<ImageView>(R.id.simple_deck_card5)
                val userDeckCard6 = itemView.findViewById<ImageView>(R.id.simple_deck_card6)
                val userDeckCard7 = itemView.findViewById<ImageView>(R.id.simple_deck_card7)
                val userDeckCard8 = itemView.findViewById<ImageView>(R.id.simple_deck_card8)
            }
            1 -> {}
            2 -> {}
            else -> {}
        }
    }

}