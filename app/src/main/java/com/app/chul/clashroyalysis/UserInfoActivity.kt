package com.app.chul.clashroyalysis

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.app.chul.clashroyalysis.adapter.UserInfoAdapter
import com.app.chul.clashroyalysis.jsonobject.ArenaData
import com.app.chul.clashroyalysis.jsonobject.PlayerData
import com.app.chul.clashroyalysis.jsonobject.TopPlayer
import com.app.chul.clashroyalysis.jsonobject.TopPlayerList
import com.app.chul.clashroyalysis.retrofit.ClashRoyaleRetrofit
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class UserInfoActivity : AppCompatActivity() {

    private var mAdapter: UserInfoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        loadUserData()
    }

    private fun initRecyclerView() {
        mAdapter = UserInfoAdapter(this@UserInfoActivity)
        main_recycler_view.layoutManager = LinearLayoutManager(this@UserInfoActivity)
        main_recycler_view.adapter = mAdapter
        main_recycler_view.setHasFixedSize(true)
    }

    private fun loadUserData() {
        if(intent.hasExtra("tag")) {
            val tag = intent.getStringExtra("tag")
            /*ClashRoyaleRetrofit.getService().getPlayer(tag)
                    .timeout(5000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                mAdapter?.setData(it, 5913)
            })*/

            /*val topPlayerCall = ClashRoyaleRetrofit.getService().getTopPlayers("KR")
            topPlayerCall.enqueue(object: Callback<TopPlayerList>{
                override fun onFailure(call: Call<TopPlayerList>?, t: Throwable?) {
                    Toast.makeText(this@UserInfoActivity, t?.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<TopPlayerList>?, response: Response<TopPlayerList>?) {
                    mAdapter?.setTopPlayerTrophy(5555)
                }

            })*/
            ClashRoyaleRetrofit.getService().getPlayer(tag)
                    .zipWith(ClashRoyaleRetrofit.getService().getTopPlayers("KR", 1).onErrorReturn {
                        TopPlayerList()
                    }, BiFunction { userData: PlayerData, topPlayer: TopPlayerList -> Pair(userData, topPlayer)})
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if(it.second.size > 0) {
                            mAdapter?.setData(it.first, it.second[0].trophies)
                        }else {
                            mAdapter?.setData(it.first, 0)
                        }
                    }, {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }, {

                    })
        }
    }

}
