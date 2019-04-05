package com.app.chul.clashroyalysis.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.utils.DragAndDropHelperCallback
import com.app.chul.clashroyalysis.viewholder.register.RegisterViewHolder
import com.app.chul.clashroyalysis.viewholder.register.SimpleInfoViewHolder

abstract class RegisterAdapter(private val context: Context?): RecyclerView.Adapter<RecyclerView.ViewHolder>(), DragAndDropHelperCallback.DragAndDropListener {

    private var mUserList = ArrayList<String>()

    object ViewType {
        const val ADD_VIEW_TYPE = 0
        const val USER_VIEW_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ViewType.ADD_VIEW_TYPE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.viewholder_register, parent, false)
                RegisterViewHolder(view)
            }
            ViewType.USER_VIEW_TYPE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.viewholder_user_simple_info, parent, false)
                SimpleInfoViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(context).inflate(R.layout.viewholder_register, parent, false)
                RegisterViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return mUserList.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            ViewType.USER_VIEW_TYPE -> {
                val viewHolder = holder as SimpleInfoViewHolder
                viewHolder.bind(mUserList[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position >= mUserList.size) {
            return ViewType.ADD_VIEW_TYPE
        }else {
            return ViewType.USER_VIEW_TYPE
        }
    }

    override fun itemMoved(position: Int, targetPosition: Int): Boolean {
        if(position >= 0 && targetPosition >= 0 && position < mUserList.size && targetPosition < mUserList.size) {
            val item = mUserList[position]
            mUserList.remove(item)
            mUserList.add(targetPosition, item)
            notifyItemMoved(position, targetPosition)

            return true
        }

        return false
    }

    override fun itemSwiped(position: Int) {
        if(position >= 0 && position < mUserList.size) {
            showDeleteDialog(mUserList[position])
        }
    }

    fun setData(data: ArrayList<String>) {
        mUserList = data
        notifyDataSetChanged()
    }

    fun addData(data: String) {
        mUserList.add(data)
        notifyItemInserted(itemCount - 1)
    }

    fun deleteItem(tag: String) {
        val position = mUserList.indexOf(tag)
        if(position >= 0 && position < mUserList.size) {
            mUserList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun refreshItem(tag: String) {
        var position = mUserList.indexOf(tag)
        if(position >= 0 && position < mUserList.size) {
            notifyItemChanged(position)
        }
    }

    abstract fun showDeleteDialog(tag: String)
}