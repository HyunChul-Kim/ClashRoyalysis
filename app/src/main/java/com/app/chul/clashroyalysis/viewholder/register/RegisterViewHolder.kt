package com.app.chul.clashroyalysis.viewholder.register

import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipboardManager
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import android.text.InputFilter
import android.transition.AutoTransition
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.`interface`.BaseInterface
import com.app.chul.clashroyalysis.bus.RxBus
import com.app.chul.clashroyalysis.bus.RxEvent
import com.app.chul.clashroyalysis.utils.UserDataHelper
import com.app.chul.clashroyalysis.utils.hideKeyboard
import com.app.chul.clashroyalysis.utils.isAvailableTag

class RegisterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private val defaultSet: ConstraintSet =
        ConstraintSet()
    private val addSet: ConstraintSet =
        ConstraintSet()
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
        itemView.setOnClickListener(this)
        registerTag.setOnClickListener(this)
        registerTag.filters = arrayOf<InputFilter>(InputFilter.AllCaps())
    }

    override fun onClick(v: View?) {
        if(v == itemView) {
            TransitionManager.beginDelayedTransition(constraintLayout, transition)

            val constraint = if(isDefault) addSet else defaultSet
            constraint.applyTo(constraintLayout)
            isDefault = !isDefault

        } else if(v == registerTag) {
            if(System.currentTimeMillis() < clickTime + 1000) {
                clickTime = 0
                val clipboard = itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val hasMimeType = clipboard.primaryClipDescription?.hasMimeType(MIMETYPE_TEXT_PLAIN) ?: false
                if(clipboard.hasPrimaryClip() && hasMimeType) {
                    val item = clipboard.primaryClip?.getItemAt(0)
                    registerTag.text.append(item?.text)
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
                        if(UserDataHelper.getInstance(itemView.context).addUserData(registerTag.text.toString())) {
                            RxBus.publish(RxEvent.EventAddTag(registerTag.text.toString()))
                        }

                    } else {
                        setShakeAnim()
                    }

                    hideKeyboard(itemView.context, registerTag.windowToken)
                }
            }

        })
    }
}