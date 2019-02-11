package com.app.chul.clashroyalysis.fragment

import android.graphics.Canvas
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.`interface`.BaseFragmentInterface
import com.app.chul.clashroyalysis.adapter.RegisterAdapter
import com.app.chul.clashroyalysis.bus.RxBus
import com.app.chul.clashroyalysis.bus.RxEvent
import com.app.chul.clashroyalysis.preference.RoyalysisPreferenceManager
import com.app.chul.clashroyalysis.utils.ChulLog
import com.app.chul.clashroyalysis.viewholder.register.SimpleInfoViewHolder
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment: Fragment(), BaseFragmentInterface {

    override fun scrollTop() {
        register_recycler_view?.scrollToPosition(0)
    }

    private val mAdapter : RegisterAdapter by lazy {
        RegisterAdapter(context)
    }

    companion object {
        fun newInstance(): Fragment {
            return RegisterFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerRxBus()
        initRecyclerView()
        initUserInfo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        RxBus.unregister(this)
    }

    private fun initRecyclerView() {
        register_recycler_view.layoutManager = LinearLayoutManager(context)
        register_recycler_view.adapter = mAdapter
        register_recycler_view.setHasFixedSize(true)
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                if(viewHolder != null && viewHolder is SimpleInfoViewHolder) {
                    val tag = viewHolder.getTag()
                    RoyalysisPreferenceManager.deleteUser(tag)
                    mAdapter.removeItem(viewHolder.adapterPosition)
                }
            }

            override fun getSwipeDirs(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
                return if(viewHolder is SimpleInfoViewHolder) super.getSwipeDirs(recyclerView, viewHolder) else 0
            }

            override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                if(viewHolder != null && viewHolder is SimpleInfoViewHolder) {
                    if (dX < 0) {
                        ChulLog.log("Background x = ${viewHolder.getBackgroundTask().x} / Foreground x = ${viewHolder.getForegroundTask().x}, width = ${viewHolder.getForegroundTask().width}")
                        ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(c, recyclerView, viewHolder.getForegroundTask(), dX, dY, actionState, isCurrentlyActive)
                    }
                }
            }

            override fun onChildDrawOver(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                if(viewHolder != null && viewHolder is SimpleInfoViewHolder) {
                    if(dX < 0) {
                        ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(c, recyclerView, viewHolder.getForegroundTask(), dX, dY, actionState, isCurrentlyActive)
                    }
                }
            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                if(viewHolder != null && viewHolder is SimpleInfoViewHolder) {
                    ItemTouchHelper.Callback.getDefaultUIUtil().onSelected(viewHolder.getForegroundTask())
                }
            }

            fun undo() {

            }
        })
        itemTouchHelper.attachToRecyclerView(register_recycler_view)
    }

    private fun initUserInfo() {
        val userList: ArrayList<String> = RoyalysisPreferenceManager.getUserList()
        mAdapter.setData(userList)
    }

    private fun addUser(tag: String) {
        mAdapter.addData(tag)
    }

    private fun registerRxBus() {
        RxBus.register(this, RxBus.listen(RxEvent.EventAddTag::class.java).subscribe {
            if(RoyalysisPreferenceManager.addUser(it.tag)) {
                addUser(it.tag)
            }
        })
    }

}