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
import com.app.chul.clashroyalysis.bus.RxBus
import com.app.chul.clashroyalysis.bus.RxEvent
import com.app.chul.clashroyalysis.utils.ChulLog
import com.app.chul.clashroyalysis.utils.DragAndDropHelperCallback
import com.app.chul.clashroyalysis.utils.UserDataHelper
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment: Fragment(), BaseFragmentInterface<ArrayList<String>> {

    override fun scrollTop() {
        register_recycler_view?.scrollToPosition(0)
    }

    override fun refresh() {

    }

    private var userList = ArrayList<String>()

    private val mAdapter : RegisterAdapter by lazy {
        object: RegisterAdapter(activity) {
            override fun showDeleteDialog(tag: String) {
                activity?.let {
                    AlertDialog.Builder(it)
                            .setTitle(R.string.delete_user)
                            .setMessage(R.string.delete_user_ask)
                            .setNegativeButton(R.string.cancel, { dialog, which ->
                                mAdapter.refreshItem(tag)
                                dialog.dismiss()
                            })
                            .setPositiveButton(R.string.ok, { dialog, which ->
                                deleteUser(tag)
                                dialog.dismiss()
                            }).show()
                }
            }
        }
    }

    companion object {
        private var Instance: RegisterFragment? = null
        fun getInstance(): Fragment =
            Instance ?: synchronized(this) {
                Instance ?: RegisterFragment()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerRxBus()
        initRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        RxBus.unregister(this)
    }

    private fun initRecyclerView() {
        register_recycler_view.layoutManager = LinearLayoutManager(activity)
        register_recycler_view.adapter = mAdapter
        register_recycler_view.setHasFixedSize(true)
        mAdapter.setData(userList)
        var itemTouchHelper = ItemTouchHelper(DragAndDropHelperCallback(mAdapter))
        itemTouchHelper.attachToRecyclerView(register_recycler_view)
    }

    override fun setData(data: ArrayList<String>) {
        userList = data
    }

    private fun addUser(tag: String) {
        mAdapter.addData(tag)
    }

    private fun deleteUser(tag: String) {
        mAdapter.deleteItem(tag)
    }

    private fun registerRxBus() {
        RxBus.register(this, RxBus.listen(RxEvent.EventAddTag::class.java).subscribe {
            if(UserDataHelper.getInstance(activity).addUserData(it.tag)) {
                mAdapter.notifyItemInserted(mAdapter.itemCount - 1)
            }
        })
    }

}