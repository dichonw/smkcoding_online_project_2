package com.example.againstcovid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.againstcovid19.ListData.CovidGlobalListAdapter
import com.example.againstcovid19.ListData.CovidGlobalListItem
import com.example.againstcovid19.dataCovidGlobalList.CovidGlobalListService
import com.example.againstcovid19.dataCovidGlobalList.apiRequestCovidGlobalList
import com.example.againstcovid19.dataCovidGlobalList.httpClient
import com.example.againstcovid19.util.dismissLoading
import com.example.againstcovid19.util.showLoading
import com.example.againstcovid19.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_global_list.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlobalListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_list)
        callApiGetCovidGlobalList()
    }
    private fun callApiGetCovidGlobalList() {
        showLoading(this, swipeRefreshLayoutDataGlobal)
        val httpClient = httpClient()
        val apiRequest = apiRequestCovidGlobalList<CovidGlobalListService>(httpClient)
        val call = apiRequest.getGlobal()
        call.enqueue(object : Callback<List<CovidGlobalListItem>> {
            override fun onFailure(call: Call<List<CovidGlobalListItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }
            override fun onResponse(call: Call<List<CovidGlobalListItem>>, response:
            Response<List<CovidGlobalListItem>>
            ) {
                dismissLoading(swipeRefreshLayoutDataGlobal)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilGlobalCovidList(response.body()!!)
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
    private fun tampilGlobalCovidList(covidGlobalList: List<CovidGlobalListItem>) {
        listCovidGlobal.layoutManager = LinearLayoutManager(this)
        listCovidGlobal.adapter = CovidGlobalListAdapter(this, covidGlobalList) {
            val covidglobalList = it
            tampilToast(this, covidglobalList.attributes.countryRegion)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }


}
