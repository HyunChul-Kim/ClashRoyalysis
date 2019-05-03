package com.app.chul.clashroyalysis.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.adapter.UserInfoAdapter
import com.app.chul.clashroyalysis.jsonobject.PlayerData
import com.app.chul.clashroyalysis.jsonobject.TopPlayerList
import com.app.chul.clashroyalysis.presenter.FragmentDataPresenter
import com.app.chul.clashroyalysis.retrofit.ClashRoyaleRetrofit
import com.app.chul.clashroyalysis.utils.ChulLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class UserInfoActivity : BaseActivity() {

    private var mAdapter: UserInfoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        loadData()
    }

    private fun initRecyclerView() {
        mAdapter = UserInfoAdapter(this@UserInfoActivity)
        user_info_recycler_view.layoutManager = LinearLayoutManager(this@UserInfoActivity)
        user_info_recycler_view.adapter = mAdapter
        user_info_recycler_view.setHasFixedSize(true)
    }

    private fun loadData() {
        val data = intent.getParcelableExtra<PlayerData>("data")
        data?.let {
            mAdapter?.setData(it, 0)
        }

        val topTrophy = FragmentDataPresenter.getInstance(this).getFirstRankTrophy()
        if(topTrophy > 0) {
            mAdapter?.setTopPlayerTrophy(topTrophy)
        } else {
            FragmentDataPresenter.getInstance(this).requestRankList("KR", 1, object: FragmentDataPresenter.ResponseListener<TopPlayerList>{
                override fun onError(message: String) {

                }

                override fun onResponse(response: TopPlayerList) {
                    mAdapter?.setTopPlayerTrophy(response?.get(0)?.trophies)
                }
            })
        }

        /*ClashRoyaleRetrofit.getService().getTopPlayers("KR", 1, 0)
                .timeout(10000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    ChulLog.log("Data size is ${it?.size}")
                    mAdapter?.setTopPlayerTrophy(it?.get(0)?.trophies)
                })*/
    }
}
