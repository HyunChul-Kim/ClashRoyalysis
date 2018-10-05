package com.app.chul.clashroyalysis.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.chul.clashroyalysis.R
import com.app.chul.clashroyalysis.adapter.RegisterAdapter
import com.app.chul.clashroyalysis.bus.RxBus
import com.app.chul.clashroyalysis.bus.RxEvent
import com.app.chul.clashroyalysis.preference.RoyalysisPreferenceManager
import kotlinx.android.synthetic.main.register_fragment.*

class RegisterFragment: Fragment() {

    private val mAdapter : RegisterAdapter by lazy {
        RegisterAdapter(context)
    }

    companion object {
        fun newInstance(): Fragment {
            return RegisterFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.register_fragment, container, false)
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
    }

    private fun initUserInfo() {
        val userList: ArrayList<String> = RoyalysisPreferenceManager.getUserList()
        mAdapter.setData(userList)
    }

    fun addUser(tag: String) {
        mAdapter.addData(tag)
    }

    private fun registerRxBus() {
        RxBus.register(this, RxBus.listen(RxEvent.EventAddTag::class.java).subscribe {
            RoyalysisPreferenceManager.addUser(it.tag)
            addUser(it.tag)
        })
    }

}