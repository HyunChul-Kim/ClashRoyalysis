package com.app.chul.clashroyalysis.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.app.chul.clashroyalysis.R

class ChestView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet ?= null, defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    private val chestImageView: ImageView
    private val chestLeftCount: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.view_chest, this, true)
        chestImageView = findViewById(R.id.chest_image_view)
        chestLeftCount = findViewById(R.id.chest_left_count)
    }

    fun set(chestType: String, left: Int) {
        setChestImage(chestType)
        setChestLeftCount(left)
    }

    fun setChestImage(type: String) {

    }

    fun setChestLeftCount(left: Int) {
        chestLeftCount.text = left.toString()
    }
}