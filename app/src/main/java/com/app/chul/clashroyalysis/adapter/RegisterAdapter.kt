package com.app.chul.clashroyalysis.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.jsonobject.PlayerData
import com.app.chul.clashroyalysis.jsonobject.PlayerDataList
import com.app.chul.clashroyalysis.utils.ChulLog
import com.app.chul.clashroyalysis.utils.DragAndDropHelperCallback
import com.app.chul.clashroyalysis.viewholder.adUnits.NativeAdViewHolder
import com.app.chul.clashroyalysis.viewholder.register.RegisterViewHolder
import com.app.chul.clashroyalysis.viewholder.register.SimpleInfoViewHolder
import com.facebook.ads.NativeAd
import com.facebook.ads.NativeAdLayout
import com.facebook.ads.NativeAdsManager

abstract class RegisterAdapter(private val activity: Activity, private var nativeAdsManager: NativeAdsManager?): RecyclerView.Adapter<RecyclerView.ViewHolder>(), DragAndDropHelperCallback.DragAndDropListener {

    private val mAdItems : MutableList<NativeAd>
    private var mUserList = PlayerDataList()
    private var mDeletedItem: PlayerData? = null

    companion object ViewType {
        const val AD_DISPLAY_FREQUENCY = 5
        const val AD_VIEW_TYPE = 0
        const val ADD_VIEW_TYPE = 1
        const val USER_VIEW_TYPE = 2
    }

    init {
        mAdItems = ArrayList()
    }

    fun setNativeAdsManager(manager: NativeAdsManager?) {
        nativeAdsManager = manager
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            AD_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_native_ad_unit, parent, false) as NativeAdLayout
                NativeAdViewHolder(view)
            }
            ADD_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_register, parent, false)
                RegisterViewHolder(view)
            }
            USER_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_user_simple_info, parent, false)
                SimpleInfoViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_register, parent, false)
                RegisterViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return mUserList.size + ((mUserList.size / AD_DISPLAY_FREQUENCY) + 1) + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            AD_VIEW_TYPE -> {
                val ad: NativeAd?

                if(mAdItems.size > position / AD_DISPLAY_FREQUENCY) {
                    ad = mAdItems[position / AD_DISPLAY_FREQUENCY]
                } else {
                    ad = nativeAdsManager?.nextNativeAd()
                    if(ad != null && !ad.isAdInvalidated) {
                        mAdItems.add(ad)
                    } else {
                        Log.w(RegisterAdapter::class.java.simpleName, "Ad is invalidated!")
                    }
                }
                (holder as NativeAdViewHolder).bind(ad, activity)
            }
            USER_VIEW_TYPE -> {
                val viewHolder = holder as SimpleInfoViewHolder
                viewHolder.bind(mUserList[position - ((position / AD_DISPLAY_FREQUENCY) + 1)])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == itemCount - 1) ADD_VIEW_TYPE else {
            if (position % AD_DISPLAY_FREQUENCY == 0) {
                AD_VIEW_TYPE
            } else {
                USER_VIEW_TYPE
            }
        }
    }

    override fun itemMoved(position: Int, targetPosition: Int): Boolean {
        if(position >= 0 && targetPosition >= 0
                && getItemViewType(position) == USER_VIEW_TYPE
                && getItemViewType(position) == USER_VIEW_TYPE) {
            val item = mUserList[position - ((position / AD_DISPLAY_FREQUENCY) + 1)]
            mUserList.remove(item)
            mUserList.add(targetPosition, item)
            notifyItemMoved(position, targetPosition)

            return true
        }

        return false
    }

    override fun itemSwiped(position: Int) {
        if(position >= 0 && getItemViewType(position) == USER_VIEW_TYPE) {
            mDeletedItem = mUserList[position - ((position / AD_DISPLAY_FREQUENCY) + 1)]
            deleteItem(position)
            showDeleteDialog(position)
        }
    }

    fun getDeletedItemTag(): String {
        return mDeletedItem?.tag ?: ""
    }

    fun setData(data: PlayerDataList) {
        mUserList = data
        notifyDataSetChanged()
    }

    fun addData(data: PlayerData) {
        mUserList.add(data)
        notifyItemInserted(itemCount - 1)
    }

    fun deleteItem(position: Int) {
        if(position >= 0 && getItemViewType(position) == USER_VIEW_TYPE) {
            mUserList.removeAt(position - ((position / AD_DISPLAY_FREQUENCY) + 1))
            notifyItemRemoved(position)
        }
    }

    fun insertItem(position: Int) {
        if(position >= 0) {
            mDeletedItem?.let { deletedItem ->
                mUserList.add(position - ((position / AD_DISPLAY_FREQUENCY) + 1), deletedItem)
                notifyItemInserted(position)
            }
        }
    }

    fun refreshItem(position: Int) {
        if(position >= 0 && position < mUserList.size) {
            notifyItemChanged(position)
        }
    }

    abstract fun showDeleteDialog(position: Int)
}