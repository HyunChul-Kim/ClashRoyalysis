package com.app.chul.clashroyalysis.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
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
import com.app.chul.clashroyalysis.utils.DragAndDropHelperCallback
import com.app.chul.clashroyalysis.utils.UserDataHelper
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment: Fragment(), BaseFragmentInterface {

    override fun scrollTop() {
        register_recycler_view?.scrollToPosition(0)
    }

    private val mAdapter : RegisterAdapter by lazy {
        object: RegisterAdapter(context) {
            override fun showDeleteDialog(tag: String) {
                context?.let {
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
        fun getInstance(): Fragment {
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
        var itemTouchHelper = ItemTouchHelper(DragAndDropHelperCallback(mAdapter))
        itemTouchHelper.attachToRecyclerView(register_recycler_view)
    }

    private fun initUserInfo() {
        val userList: ArrayList<String> = UserDataHelper.getInstance(context).getUserList()
        mAdapter.setData(userList)
    }

    private fun addUser(tag: String) {
        mAdapter.addData(tag)
    }

    private fun deleteUser(tag: String) {
        mAdapter.deleteItem(tag)
    }

    private fun registerRxBus() {
        RxBus.register(this, RxBus.listen(RxEvent.EventAddTag::class.java).subscribe {
            if(UserDataHelper.getInstance(context).addUserData(it.tag)) {
                mAdapter.notifyItemInserted(mAdapter.itemCount - 1)
            }
        })
    }

}