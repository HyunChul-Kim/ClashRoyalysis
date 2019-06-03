package com.app.chul.clashroyalysis.presenter

import android.content.Context
import com.app.chul.clashroyalysis.jsonobject.*
import com.app.chul.clashroyalysis.retrofit.ClashRoyaleRetrofit
import com.app.chul.clashroyalysis.utils.ChulLog
import com.app.chul.clashroyalysis.utils.SingletonHolder
import com.app.chul.clashroyalysis.utils.UserDataHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BaseDataPresenter private constructor(context: Context) {

    private var mUserDataHelper = UserDataHelper.getInstance(context)

    private var mUserList = PlayerDataList()
    private var mDeckList = PopularDeckList()
    private var mRankList = TopPlayerList()

    interface ResponseListener<in T> {
        fun onError(message: String)
        fun onResponse(response: T)
    }

    companion object: SingletonHolder<BaseDataPresenter, Context>(::BaseDataPresenter)

    fun requestPlayersDataList(listener: ResponseListener<PlayerDataList>?) {
        val tags = mUserDataHelper.getUserListToString()
        val playersDataCall = ClashRoyaleRetrofit.getService().getPlayers(tags)
        playersDataCall.enqueue(object : Callback<PlayerDataList> {
            override fun onFailure(call: Call<PlayerDataList>?, t: Throwable?) {
                listener?.onError(t.toString())
                ChulLog.log("Player Data Service Failed ${t.toString()}")
            }

            override fun onResponse(call: Call<PlayerDataList>?, response: Response<PlayerDataList>?) {
                ChulLog.log("Player Data Service Response Success")
                response?.let {
                    if(it.body() != null) {
                        mUserList = it.body() as PlayerDataList
                        listener?.onResponse(mUserList)
                    } else {
                        it.errorBody()?.let { listener?.onError(it.toString()) }
                    }
                }
            }
        })
    }

    fun requestPlayerData(tag: String, added: Boolean, listener: ResponseListener<PlayerData>?) {
        val playersDataCall = ClashRoyaleRetrofit.getService().getPlayer(tag)
        playersDataCall.enqueue(object : Callback<PlayerData> {
            override fun onFailure(call: Call<PlayerData>?, t: Throwable?) {
                ChulLog.log("Player Data Service Failed ${t.toString()}")
                listener?.onError(t.toString())
            }

            override fun onResponse(call: Call<PlayerData>?, response: Response<PlayerData>?) {
                ChulLog.log("Player Data Service Response Success")
                response?.let {
                    if(it.body() != null) {
                        if(added) {
                            mUserList.add(it.body() as PlayerData)
                        }
                        listener?.onResponse(it.body() as PlayerData)
                    } else {
                        it.errorBody()?.let { listener?.onError(it.toString()) }
                    }
                }
            }
        })
    }

    fun requestDeckList(listener: ResponseListener<PopularDeckList>?) {
        val popularDeckCall = ClashRoyaleRetrofit.getService().getPopularDecks()
        popularDeckCall.enqueue(object: Callback<PopularDeckList> {

            override fun onFailure(call: Call<PopularDeckList>?, t: Throwable?) {
                ChulLog.log("Popular Service Failed ${t.toString()}")
                listener?.onError(t.toString())
            }

            override fun onResponse(call: Call<PopularDeckList>?, response: Response<PopularDeckList>?) {
                ChulLog.log("Popular Service Response Success")
                response?.let {
                    if(it.body() != null) {
                        mDeckList = it.body() as PopularDeckList
                        listener?.onResponse(mDeckList)
                    } else {
                        it.errorBody()?.let { listener?.onError(it.toString()) }
                    }
                }
            }
        })
    }

    fun requestRankList(location: String, max: Int, listener: ResponseListener<TopPlayerList>?) {
        val topPlayerListCall = ClashRoyaleRetrofit.getService().getTopPlayers(location, max)
        topPlayerListCall.enqueue(object: Callback<TopPlayerList> {
            override fun onFailure(call: Call<TopPlayerList>?, t: Throwable?) {
                ChulLog.log("TopPlayer Service Failed ${t.toString()}")
                listener?.onError(t.toString())
            }

            override fun onResponse(call: Call<TopPlayerList>?, response: Response<TopPlayerList>?) {
                ChulLog.log("TopPlayer Service Response Success")
                response?.let {
                    if(it.body() != null) {
                        mRankList = it.body() as TopPlayerList
                        listener?.onResponse(mRankList)
                    } else {
                        it.errorBody()?.let { listener?.onError(it.toString()) }
                    }
                }
            }
        })
    }

    fun reqeustChestsData(tag: String, listener: ResponseListener<UpcomingChestsData>?) {
        val chestsCall = ClashRoyaleRetrofit.getService().getUpcomingChests(tag)
        chestsCall.enqueue(object : Callback<UpcomingChestsData> {
            override fun onFailure(call: Call<UpcomingChestsData>?, t: Throwable?) {
                ChulLog.log("UpcomingChests Service Failed ${t.toString()}")
                listener?.onError(t.toString())
            }

            override fun onResponse(call: Call<UpcomingChestsData>?, response: Response<UpcomingChestsData>?) {
                ChulLog.log("UpcomingChests Service Response Success")
                response?.let { data ->
                    if(data.body() != null) {
                        listener?.onResponse(data.body() as UpcomingChestsData)
                    } else {
                        data.errorBody()?.let { listener?.onError(it.toString()) }
                    }
                }
            }
        })
    }

    fun getUserList()  = mUserList

    fun getDeckList() = mDeckList

    fun getRankList() = mRankList

    fun getFirstRankTrophy(): Int {
        return if(mRankList.size > 0) {
            mRankList[0].trophies
        } else {
            0
        }
    }
}