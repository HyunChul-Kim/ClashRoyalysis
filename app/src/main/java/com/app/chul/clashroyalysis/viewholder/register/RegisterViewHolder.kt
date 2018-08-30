package com.app.chul.clashroyalysis.viewholder.register

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.widget.RecyclerView
import android.transition.AutoTransition
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.widget.TextView
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.bus.RxBus
import com.app.chul.clashroyalysis.bus.RxEvent
import com.app.chul.clashroyalysis.utils.isAvailableTag

class RegisterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val defaultSet: ConstraintSet = ConstraintSet()
    private val addSet: ConstraintSet = ConstraintSet()
    private val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.default_constraint_layout)
    private val registerTag: TextView = itemView.findViewById(R.id.simple_user_tag)

    private val transition = AutoTransition()

    private var isDefault: Boolean = true

    init {
        defaultSet.clone(constraintLayout)
        addSet.clone(itemView.context, R.layout.register_add_viewholder)
        setTransition()
        itemView.setOnClickListener {

            TransitionManager.beginDelayedTransition(constraintLayout, transition)

            val constraint = if(isDefault) addSet else defaultSet
            constraint.applyTo(constraintLayout)
            isDefault = !isDefault
        }
    }

    private fun setTransition() {
        transition.duration = 300
        transition.addListener(object: Transition.TransitionListener{
            override fun onTransitionEnd(transition: Transition?) {

            }

            override fun onTransitionResume(transition: Transition?) {

            }

            override fun onTransitionPause(transition: Transition?) {

            }

            override fun onTransitionCancel(transition: Transition?) {

            }

            override fun onTransitionStart(transition: Transition?) {
                if(isDefault) {
                    if(isAvailableTag(registerTag.text.toString())) {
                        RxBus.publish(RxEvent.EventAddTag(registerTag.text.toString()))
                    }
                }
            }

        })
    }
}