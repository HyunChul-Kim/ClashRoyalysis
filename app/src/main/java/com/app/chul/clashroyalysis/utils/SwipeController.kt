package com.app.chul.clashroyalysis.utils

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.MotionEvent
import android.view.View

@SuppressLint("ClickableViewAccessibility")
class SwipeController: ItemTouchHelper.Callback() {

    enum class ButtonState{
        GONE, LEFT_VISIBLE, RIGHT_VISIBLE
    }

    private var swipeBack = false
    private var buttonState = ButtonState.GONE
    private var buttonWidth = 300f

    private var buttonInstance: RectF? = null
    private var selectedViewHolder: RecyclerView.ViewHolder? = null

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder?): Float {
        return 1f
    }

    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
        return 2f
    }

    override fun getSwipeVelocityThreshold(defaultValue: Float): Float {
        return 2f
    }

    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        return makeMovementFlags(0, ItemTouchHelper.RIGHT)
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {

    }

    fun onDraw(c: Canvas?) {
        selectedViewHolder?.let { drawButton(c, it) }
    }

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if(buttonState != ButtonState.GONE) {
                var finalDeltaX = dX
                if(buttonState == ButtonState.LEFT_VISIBLE) finalDeltaX = Math.max(dX, buttonWidth)
                if(buttonState == ButtonState.RIGHT_VISIBLE) finalDeltaX = Math.min(dX, -buttonWidth)
                super.onChildDraw(c, recyclerView, viewHolder, finalDeltaX, dY, actionState, isCurrentlyActive)

            } else {
                setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }

        if(buttonState == ButtonState.GONE) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
        selectedViewHolder = viewHolder
//        selectedViewHolder?.let { drawButton(c, it) }
    }

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        /*if(swipeBack) {
            swipeBack = buttonState != ButtonState.GONE
            return 0
        }*/
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    private fun setTouchListener(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        recyclerView?.setOnTouchListener { v, event ->
            swipeBack = event?.action == MotionEvent.ACTION_CANCEL || event?.action == MotionEvent.ACTION_UP
            if(swipeBack) {
                if(dX < -buttonWidth) {
                    buttonState = ButtonState.RIGHT_VISIBLE
                } else if(dX > buttonWidth) {
                    buttonState = ButtonState.LEFT_VISIBLE
                }

                if(buttonState != ButtonState.GONE) {
                    setTouchDownListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    setItemsClickable(recyclerView, false)
                }
            }
            false
        }
    }

    private fun setTouchDownListener(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        recyclerView?.setOnTouchListener { v, event ->
            if(event.action == MotionEvent.ACTION_DOWN) {
                setTouchUpListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
            false
        }
    }

    private fun setTouchUpListener(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        recyclerView?.setOnTouchListener { v, event ->
            if(event.action == MotionEvent.ACTION_UP) {
                super.onChildDraw(c, recyclerView, viewHolder, 0f, dY, actionState, isCurrentlyActive)
                recyclerView.setOnTouchListener { v, event -> false }
                setItemsClickable(recyclerView, true)
                swipeBack = false
                buttonState = ButtonState.GONE
                selectedViewHolder = null
            }
            false
        }
    }

    private fun setItemsClickable(recyclerView: RecyclerView?, isClickable: Boolean) {
        recyclerView?.let {
            for(i in 0 until it.childCount) {
                it.getChildAt(i).isClickable = isClickable
            }
        }
    }

    private fun drawButton(c: Canvas?, viewHolder: RecyclerView.ViewHolder?) {
        val buttonWidthWithoutPadding = buttonWidth - 20
        val corners = 16f

        val itemView = viewHolder?.itemView
        val paint = Paint()
        val leftButton = RectF(itemView?.left!!.toFloat(), itemView.top.toFloat(), itemView.left.toFloat() + buttonWidthWithoutPadding, itemView.bottom.toFloat())
        paint.color = Color.BLUE
        c?.drawRoundRect(leftButton, corners, corners, paint)
        drawText("DELETE", c, leftButton, paint)

        buttonInstance = null
        if(buttonState == ButtonState.LEFT_VISIBLE) {
            buttonInstance = leftButton
        }
    }

    private fun drawText(text: String, c: Canvas?, button: RectF, paint: Paint) {
        val textSize = 60f
        paint.color = Color.WHITE
        paint.isAntiAlias = true
        paint.textSize = textSize

        val textWidth = paint.measureText(text)
        c?.drawText(text, button.centerX() - (textWidth / 2), button.centerY() + (textSize / 2), paint)
    }
}