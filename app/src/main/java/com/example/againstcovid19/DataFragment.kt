package com.example.againstcovid19

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_data.*


class DataFragment : Fragment() {

    lateinit var listData : ArrayList<DataItem>

    private fun simulasiDataTeman() {
        listData = ArrayList()
        listData.add(DataItem("Fakhry", "Laki-laki", "fakhry@smkcoding.id", "081123123123", "Malang"))
        listData.add(DataItem("Ahmad", "Laki-laki", "ahmad@smkcoding.id", "085123123123", "Malang"))
    }
    private fun tampilTeman() {
        rv_listMyFriends.layoutManager = LinearLayoutManager(activity)
        rv_listMyFriends.adapter = DataAdapter(activity!!, listData)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView() {
        simulasiDataTeman()
        tampilTeman()
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }


}
