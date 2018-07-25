package com.app.chul.clashroyalysis.viewholder

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.widget.RecyclerView
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import com.app.chul.clashroyalysis.R

class EmptySimpleInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val defaultSet: ConstraintSet = ConstraintSet()
    private val addSet: ConstraintSet = ConstraintSet()
    private val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.default_constraint_layout)

    private var isDefault: Boolean = true

    init {
        defaultSet.clone(constraintLayout)
        addSet.clone(itemView.context, R.layout.empty_simple_info_add_viewholder)
        itemView.setOnClickListener {
            val transition = AutoTransition()
            transition.duration = 300
            TransitionManager.beginDelayedTransition(constraintLayout, transition)

            val constraint = if(isDefault) addSet else defaultSet
            constraint.applyTo(constraintLayout)
            isDefault = !isDefault
        }
    }
}