package com.app.chul.clashroyalysis.presenter

import android.content.Context
import android.util.Log
import com.app.chul.clashroyalysis.jsonobject.PopularDeckList
import com.app.chul.clashroyalysis.jsonobject.TopPlayerList
import com.app.chul.clashroyalysis.retrofit.ClashRoyaleRetrofit
import com.app.chul.clashroyalysis.utils.UserDataHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentDataPresenter(context: Context) {

    private val mContext = context

    private var mUserList = ArrayList<String>()
    private var mDeckList = PopularDeckList()
    private var mRankList = TopPlayerList()

    interface ResponseListener<in T> {
        fun onResponse(response: T)
    }

    fun initData(location: String, max: Int) {
        requestUserList()
        requestDeckList()
        requestRankList(location, max)
    }

    private fun requestUserList() {
        mUserList = UserDataHelper.getInstance(mContext).getUserList()
    }

    private fun requestDeckList() {
        val popularDeckCall = ClashRoyaleRetrofit.getService().getPopularDecks()
        popularDeckCall.enqueue(object: Callback<PopularDeckList> {

            override fun onFailure(call: Call<PopularDeckList>?, t: Throwable?) {
                Log.i("Popular Service", t.toString())
            }

            override fun onResponse(call: Call<PopularDeckList>?, response: Response<PopularDeckList>?) {
                Log.i("Popular Service", "Response Success")
                response?.body()?.let {
                    mDeckList = it
                }
            }
        })
    }

    private fun requestRankList(location: String, max: Int) {
        val topPlayerListCall = ClashRoyaleRetrofit.getService().getTopPlayers(location, max)
        topPlayerListCall.enqueue(object: Callback<TopPlayerList> {
            override fun onFailure(call: Call<TopPlayerList>?, t: Throwable?) {
                Log.i("TopPlayer Service", t.toString())
            }

            override fun onResponse(call: Call<TopPlayerList>?, response: Response<TopPlayerList>?) {
                response?.body()?.let {
                    mRankList = it
                }
            }
        })
    }

    fun getRefreshUserList(listener: ResponseListener<ArrayList<String>>) {
        mUserList = UserDataHelper.getInstance(mContext).getUserList()
        listener.onResponse(mUserList)
    }

    fun getRefreshDeckList(listener: ResponseListener<PopularDeckList>) {
        val popularDeckCall = ClashRoyaleRetrofit.getService().getPopularDecks()
        popularDeckCall.enqueue(object: Callback<PopularDeckList> {

            override fun onFailure(call: Call<PopularDeckList>?, t: Throwable?) {
                Log.i("Popular Service", t.toString())
            }

            override fun onResponse(call: Call<PopularDeckList>?, response: Response<PopularDeckList>?) {
                Log.i("Popular Service", "Response Success")
                response?.body()?.let {
                    listener.onResponse(it)
                }
            }
        })
    }

    fun getRefreshRankList(location: String, max: Int, listener: ResponseListener<TopPlayerList>) {
        val topPlayerListCall = ClashRoyaleRetrofit.getService().getTopPlayers(location, max)
        topPlayerListCall.enqueue(object: Callback<TopPlayerList> {
            override fun onFailure(call: Call<TopPlayerList>?, t: Throwable?) {
                Log.i("TopPlayer Service", t.toString())
            }

            override fun onResponse(call: Call<TopPlayerList>?, response: Response<TopPlayerList>?) {
                response?.body()?.let {
                    listener.onResponse(it)
                }
            }
        })
    }

    fun getUserList()  = mUserList

    fun getDeckList() = mDeckList

    fun getRankList() = mRankList
}