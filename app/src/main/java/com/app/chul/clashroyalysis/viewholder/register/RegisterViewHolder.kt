package com.app.chul.clashroyalysis.viewholder.register

import android.content.ClipData
import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipboardManager
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.widget.RecyclerView
import android.transition.AutoTransition
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.TextView
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.bus.RxBus
import com.app.chul.clashroyalysis.bus.RxEvent
import com.app.chul.clashroyalysis.utils.hideKeyboard
import com.app.chul.clashroyalysis.utils.isAvailableTag

class RegisterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val defaultSet: ConstraintSet = ConstraintSet()
    private val addSet: ConstraintSet = ConstraintSet()
    private val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.default_constraint_layout)
    private val registerTag: EditText = itemView.findViewById(R.id.simple_user_tag)

    private val transition = AutoTransition()

    private var isDefault: Boolean = true
    private var clickTime = 0L
    private var clickCount = 0

    init {
        defaultSet.clone(constraintLayout)
        addSet.clone(itemView.context, R.layout.viewholder_register_add)
        setTransition()
        itemView.setOnClickListener {

            TransitionManager.beginDelayedTransition(constraintLayout, transition)

            val constraint = if(isDefault) addSet else defaultSet
            constraint.applyTo(constraintLayout)
            isDefault = !isDefault
        }
        registerTag.setOnClickListener {
            if(System.currentTimeMillis() < clickTime + 1000) {
                clickTime = 0
                var clipboard = itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                if(clipboard.hasPrimaryClip() && clipboard.primaryClipDescription.hasMimeType(MIMETYPE_TEXT_PLAIN)) {
                    val item = clipboard.primaryClip.getItemAt(0)
                    registerTag.text.append(item.text)
                }
            }else {
                clickTime = System.currentTimeMillis()
            }
        }
    }

    private fun setShakeAnim() {
        val animation = AnimationUtils.loadAnimation(itemView.context, R.anim.shake_anim)
        itemView.startAnimation(animation)
    }

    private fun setTransition() {
        transition.duration = 300
        transition.addListener(object: Transition.TransitionListener{
            override fun onTransitionEnd(transition: Transition?) {
                registerTag.text.clear()
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

                    } else {
                        setShakeAnim()
                    }

                    hideKeyboard(itemView.context, registerTag.windowToken)
                }
            }

        })
    }
}