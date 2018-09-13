package com.app.chul.clashroyalysis

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.app.chul.clashroyalysis.adapter.UserInfoAdapter
import com.app.chul.clashroyalysis.jsonobject.PlayerData
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
            ClashRoyaleRetrofit.getService().getPlayer(tag)
                    .zipWith(ClashRoyaleRetrofit.getService().getTopPlayers("KR", 1),
                            BiFunction { userData: PlayerData, topPlayer: TopPlayerList -> Pair(userData, topPlayer)})
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        mAdapter?.setData(it.first, it.second[0].trophies)
                    }, {}, {})
        }
    }

}
