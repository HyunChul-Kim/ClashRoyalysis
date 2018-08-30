package com.app.chul.clashroyalysis.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.CardData
import com.bumptech.glide.Glide

class DeckListView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val userDeckCard1: ImageView
    private val userDeckCard2: ImageView
    private val userDeckCard3: ImageView
    private val userDeckCard4: ImageView
    private val userDeckCard5: ImageView
    private val userDeckCard6: ImageView
    private val userDeckCard7: ImageView
    private val userDeckCard8: ImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.deck_list_view, this, true)
        userDeckCard1 = findViewById(R.id.simple_deck_card1)
        userDeckCard2 = findViewById(R.id.simple_deck_card2)
        userDeckCard3 = findViewById(R.id.simple_deck_card3)
        userDeckCard4 = findViewById(R.id.simple_deck_card4)
        userDeckCard5 = findViewById(R.id.simple_deck_card5)
        userDeckCard6 = findViewById(R.id.simple_deck_card6)
        userDeckCard7 = findViewById(R.id.simple_deck_card7)
        userDeckCard8 = findViewById(R.id.simple_deck_card8)
    }

    fun setUserDeckList(deckList: List<CardData>?) {
        deckList?.let {
            for(index in it.indices){
                Glide.with(context).load(it[index].icon).into(getCardView(index))
            }
        }
    }

    private fun getCardView(position: Int): ImageView{
        return when(position){
            0 -> userDeckCard1
            1 -> userDeckCard2
            2 -> userDeckCard3
            3 -> userDeckCard4
            4 -> userDeckCard5
            5 -> userDeckCard6
            6 -> userDeckCard7
            7 -> userDeckCard8
            else -> userDeckCard1
        }
    }
}