package com.example.againstcovid19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.againstcovid19.ListData.CovidGlobalListAdapter
import com.example.againstcovid19.ListData.CovidGlobalListItem
import com.example.againstcovid19.ListDataIndo.CovidIndoListAdapter
import com.example.againstcovid19.ListDataIndo.CovidIndoListItem
import com.example.againstcovid19.dataCovidGlobalList.CovidGlobalListService
import com.example.againstcovid19.dataCovidGlobalList.apiRequestCovidGlobalList
import com.example.againstcovid19.dataCovidGlobalList.httpClient
import com.example.againstcovid19.dataCovidIndoList.CovidIndoListService
import com.example.againstcovid19.dataCovidIndoList.apiRequestCovidIndoList
import com.example.againstcovid19.util.dismissLoading
import com.example.againstcovid19.util.showLoading
import com.example.againstcovid19.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_global_list.*
import kotlinx.android.synthetic.main.activity_indo_list.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.list_covid_indo_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IndoListActivity : AppCompatActivity() {

    private lateinit var button : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indo_list)

        button = findViewById(R.id.btn_back_indo) as ImageButton
        button.setOnClickListener(View.OnClickListener { this@IndoListActivity.finish() })

        callApiGetCovidIndoList()
    }
    private fun callApiGetCovidIndoList() {
        showLoading(this, swipeRefreshLayoutDataIndo)
        val httpClient = httpClient()
        val apiRequest = apiRequestCovidIndoList<CovidIndoListService>(httpClient)
        val call = apiRequest.getIndo()
        call.enqueue(object : Callback<List<CovidIndoListItem>> {
            override fun onFailure(call: Call<List<CovidIndoListItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }
            override fun onResponse(call: Call<List<CovidIndoListItem>>, response:
            Response<List<CovidIndoListItem>>
            ) {
                dismissLoading(swipeRefreshLayoutDataIndo)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilIndoCovidList(response.body()!!)
                            else -> {
//                                tampilToast(this!!, "berhasil")
                            }
                        }
                    else -> {
//                        tampilToast(this!!, "Gagal")
                    }
                }
            }
        })
    }
    private fun tampilIndoCovidList(covidIndoList: List<CovidIndoListItem>) {
        listCovidIndoProv.layoutManager = LinearLayoutManager(this)
        listCovidIndoProv.adapter = CovidIndoListAdapter(this, covidIndoList) {
            val covidindoList = it
            tampilToast(this, covidindoList.attributes.provinsi)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }


}
