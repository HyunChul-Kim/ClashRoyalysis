package com.app.chul.clashroyalysis.view

import android.content.Context
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class SwipeMenuLayout
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ViewGroup(context, attrs, defStyleAttr) {

    private val mDragHelper: ViewDragHelper
    private val mDragHelperCallback = MenuDragHelperCallBack()

    init {
        mDragHelper = ViewDragHelper.create(this, 1f, mDragHelperCallback)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }

    class MenuDragHelperCallBack: ViewDragHelper.Callback(){
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return super.clampViewPositionHorizontal(child, left, dx)
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
        }

        override fun onEdgeDragStarted(edgeFlags: Int, pointerId: Int) {
            super.onEdgeDragStarted(edgeFlags, pointerId)
        }

        override fun onViewPositionChanged(changedView: View, left: Int, top: Int, dx: Int, dy: Int) {
            super.onViewPositionChanged(changedView, left, top, dx, dy)
        }

    }



}