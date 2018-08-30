package com.app.chul.clashroyalysis.viewholder

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.app.chul.clashroyalysis.CLASH_ROYALE_PACKAGE
import com.app.chul.clashroyalysis.CLASH_ROYALE_SCHEME
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.DeckInfo
import com.app.chul.clashroyalysis.utils.isInstalled
import com.app.chul.clashroyalysis.view.DeckListView

class DeckViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val popularityTextView = itemView.findViewById<TextView>(R.id.deck_popularity_value)
    private val shareBtn = itemView.findViewById<TextView>(R.id.deck_link_button)
    private val deckList = itemView.findViewById<DeckListView>(R.id.popular_deck_list)

    private lateinit var mDeck: DeckInfo

    init {
        shareBtn.setOnClickListener {
            if(isInstalled(itemView.context, CLASH_ROYALE_PACKAGE)) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(getDeckScheme())
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                itemView.context.startActivity(intent)
            }else {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(mDeck.decklink)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                itemView.context.startActivity(intent)
            }
        }
    }

    fun bindData(deck: DeckInfo) {
        mDeck = deck
        popularityTextView.text = getCostAvg()
        deckList.setUserDeckList(mDeck.cards)
    }

    private fun getCostAvg(): String {
        var totalCost = 0
        mDeck?.let {
            for(card in it.cards) {
                totalCost += card.elixir
            }
        }

        return String.format("%.2f", totalCost / 8f)
    }

    private fun getDeckScheme(): String {
        return CLASH_ROYALE_SCHEME + mDeck?.decklink.removeRange(0, mDeck?.decklink.indexOf("?"))
    }
}