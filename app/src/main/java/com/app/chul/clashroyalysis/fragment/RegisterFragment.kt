package com.app.chul.clashroyalysis.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.`interface`.BaseFragmentInterface
import com.app.chul.clashroyalysis.adapter.RegisterAdapter
import com.app.chul.clashroyalysis.jsonobject.PlayerData
import com.app.chul.clashroyalysis.jsonobject.PlayerDataList
import com.app.chul.clashroyalysis.listener.FragmentStateListener
import com.app.chul.clashroyalysis.utils.ChulLog
import com.app.chul.clashroyalysis.utils.DragAndDropHelperCallback
import com.app.chul.clashroyalysis.utils.UserDataHelper
import com.app.chul.clashroyalysis.view.FragmentTabView
import com.facebook.ads.AdError
import com.facebook.ads.NativeAdsManager
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment: Fragment(), BaseFragmentInterface, NativeAdsManager.Listener {

    override fun scrollTop() {
        register_recycler_view?.scrollToPosition(0)
    }

    override fun refresh() {
        mAdapter?.setData(userList)
    }

    private var nativeAdsManager: NativeAdsManager? = null
    private var userList = PlayerDataList()
    private var mAdapter : RegisterAdapter ?= null

    private var fragmentListener: FragmentStateListener ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentListener?.onActivityCreated(FragmentTabView.TabType.Home, register_recycler_view)
        initRecyclerView()
        initAdapter()
    }

    private fun initRecyclerView() {
        register_recycler_view.layoutManager = LinearLayoutManager(activity)
        register_recycler_view.setHasFixedSize(true)
    }

    private fun initAdapter() {
        mAdapter = object: RegisterAdapter(activity, nativeAdsManager) {
                override fun showDeleteDialog(position: Int) {
                    activity?.let {
                        AlertDialog.Builder(it)
                                .setTitle(R.string.delete_user)
                                .setMessage(R.string.delete_user_ask)
                                .setNegativeButton(R.string.cancel) { dialog, which ->
                                    mAdapter?.insertItem(position)
                                    dialog.dismiss()
                                }
                                .setPositiveButton(R.string.ok) { dialog, which ->
                                    UserDataHelper.getInstance(activity).deleteUserData(mAdapter!!.getDeletedItemTag())
                                    dialog.dismiss()
                                }
                                .setCancelable(false)
                                .show()
                    }
                }
            }
        mAdapter?.setData(userList)
        mAdapter?.let {
            val itemTouchHelper = ItemTouchHelper(DragAndDropHelperCallback(it))
            itemTouchHelper.attachToRecyclerView(register_recycler_view)
        }
        register_recycler_view.adapter = mAdapter
    }

    fun setNativeAdsManager(nativeAdsManager: NativeAdsManager?) {
        this.nativeAdsManager = nativeAdsManager
        mAdapter?.setNativeAdsManager(nativeAdsManager)
    }

    override fun onAdError(p0: AdError?) {

    }

    override fun onAdsLoaded() {
        if(activity == null) {
            return
        }
        mAdapter?.setNativeAdsManager(nativeAdsManager)
    }

    fun setData(data: PlayerDataList) {
        userList = data
        ChulLog.log("Register Fragment SetData(), Adapter is ${if(mAdapter == null) "null" else "not null"}")
        mAdapter?.setData(userList)
    }

    fun setFragmentListener(listener: FragmentStateListener?) {
        fragmentListener = listener
    }

    fun addUser(data: PlayerData) {
        mAdapter?.addData(data)
    }

    fun addUser() {
        mAdapter?.let { adapter ->
            adapter.notifyItemInserted(adapter.itemCount - 1)
        }
    }

    fun deleteUser(tag: String) {
        for((index, data)in userList.withIndex()) {
            if(data.tag.equals(tag, true)) {
                userList.remove(data)
                mAdapter?.deleteItem(index)
            }
        }
    }

    fun getRecyclerView() = register_recycler_view
}