package com.app.chul.clashroyalysis.view

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.adapter.CardListAdapter
import com.app.chul.clashroyalysis.jsonobject.CardData
import com.bumptech.glide.Glide

class CardListView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val mRecyclerView: RecyclerView
    private val mAdapter by lazy { CardListAdapter(context) }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_card_list, this, true)
        mRecyclerView = findViewById(R.id.card_list_recycler_view)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager =
            GridLayoutManager(
                context,
                4,
                GridLayoutManager.VERTICAL,
                false
            )
        mRecyclerView.adapter = mAdapter
    }

    fun bind(cardList: ArrayList<CardData>) {
        mAdapter.setData(cardList)
    }

}