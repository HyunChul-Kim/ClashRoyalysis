package com.app.chul.clashroyalysis.presenter

import android.content.Context
import android.util.Log
import com.app.chul.clashroyalysis.jsonobject.PlayerData
import com.app.chul.clashroyalysis.jsonobject.PlayerDataList
import com.app.chul.clashroyalysis.jsonobject.PopularDeckList
import com.app.chul.clashroyalysis.jsonobject.TopPlayerList
import com.app.chul.clashroyalysis.retrofit.ClashRoyaleRetrofit
import com.app.chul.clashroyalysis.utils.UserDataHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentDataPresenter(context: Context) {

    private val mContext = context

    private var mUserList = PlayerDataList()
    private var mDeckList = PopularDeckList()
    private var mRankList = TopPlayerList()

    interface ResponseListener<in T> {
        fun onResponse(response: T)
    }

    fun requestPlayersDataList(listener: ResponseListener<PlayerDataList>) {
        var tags = UserDataHelper.getInstance(mContext).getUserListToString()
        val playersDataCall = ClashRoyaleRetrofit.getService().getPlayers(tags)
        playersDataCall.enqueue(object : Callback<PlayerDataList> {
            override fun onFailure(call: Call<PlayerDataList>?, t: Throwable?) {
                Log.i("Player Data Service", t.toString())
            }

            override fun onResponse(call: Call<PlayerDataList>?, response: Response<PlayerDataList>?) {
                Log.i("Player Data Service", "Response Success")
                response?.body()?.let {
                    mUserList = it
                    listener.onResponse(it)
                }
            }
        })
    }

    fun requestDeckList(listener: ResponseListener<PopularDeckList>) {
        val popularDeckCall = ClashRoyaleRetrofit.getService().getPopularDecks()
        popularDeckCall.enqueue(object: Callback<PopularDeckList> {

            override fun onFailure(call: Call<PopularDeckList>?, t: Throwable?) {
                Log.i("Popular Service", t.toString())
            }

            override fun onResponse(call: Call<PopularDeckList>?, response: Response<PopularDeckList>?) {
                Log.i("Popular Service", "Response Success")
                response?.body()?.let {
                    mDeckList = it
                    listener.onResponse(it)
                }
            }
        })
    }

    fun requestRankList(location: String, max: Int, listener: ResponseListener<TopPlayerList>) {
        val topPlayerListCall = ClashRoyaleRetrofit.getService().getTopPlayers(location, max)
        topPlayerListCall.enqueue(object: Callback<TopPlayerList> {
            override fun onFailure(call: Call<TopPlayerList>?, t: Throwable?) {
                Log.i("TopPlayer Service", t.toString())
            }

            override fun onResponse(call: Call<TopPlayerList>?, response: Response<TopPlayerList>?) {
                response?.body()?.let {
                    mRankList = it
                    listener.onResponse(it)
                }
            }
        })
    }

    fun getRefreshUserList(listener: ResponseListener<PlayerDataList>) {
        var tags = UserDataHelper.getInstance(mContext).getUserListToString()
        val playersDataCall = ClashRoyaleRetrofit.getService().getPlayers(tags)
        playersDataCall.enqueue(object : Callback<PlayerDataList> {
            override fun onFailure(call: Call<PlayerDataList>?, t: Throwable?) {
                Log.i("Player Data Service", t.toString())
            }

            override fun onResponse(call: Call<PlayerDataList>?, response: Response<PlayerDataList>?) {
                Log.i("Player Data Service", "Response Success")
                response?.body()?.let {
                    listener.onResponse(it)
                }
            }
        })
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

    fun requestPlayerData(tag: String, added: Boolean, listener: ResponseListener<PlayerData>) {
        val playersDataCall = ClashRoyaleRetrofit.getService().getPlayer(tag)
        playersDataCall.enqueue(object : Callback<PlayerData> {
            override fun onFailure(call: Call<PlayerData>?, t: Throwable?) {
                Log.i("Player Data Service", t.toString())
            }

            override fun onResponse(call: Call<PlayerData>?, response: Response<PlayerData>?) {
                Log.i("Player Data Service", "Response Success")
                response?.body()?.let {
                    if(added) {
                        mUserList.add(it)
                    }
                    listener.onResponse(it)
                }
            }
        })
    }

    fun getUserList()  = mUserList

    fun getDeckList() = mDeckList

    fun getRankList() = mRankList
}