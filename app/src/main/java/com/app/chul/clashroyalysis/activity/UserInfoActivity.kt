package com.app.chul.clashroyalysis.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.adapter.UserInfoAdapter
import com.app.chul.clashroyalysis.jsonobject.PlayerData
import kotlinx.android.synthetic.main.activity_main.*

class UserInfoActivity : BaseActivity() {

    private var mAdapter: UserInfoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        loadData()
//        loadUserData()
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
    }
}
