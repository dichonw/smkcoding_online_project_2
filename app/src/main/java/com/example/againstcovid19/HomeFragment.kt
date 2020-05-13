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
import com.example.againstcovid19.data.GithubService
import com.example.againstcovid19.data.apiRequest
import com.example.againstcovid19.data.httpClient
import com.example.againstcovid19.dataCovidGlobal.CovidConfirmedGlobalService
import com.example.againstcovid19.dataCovidGlobal.apiRequestGlobal
import com.example.againstcovid19.dataDeathsGlobal.CovidDeathsGlobalService
import com.example.againstcovid19.dataDeathsGlobal.apiRequestGlobalDeaths
import com.example.againstcovid19.dataRecoveredGlobal.CovidRecoveredGlobalService
import com.example.againstcovid19.dataRecoveredGlobal.apiRequestGlobalRecovered
import com.example.againstcovid19.util.dismissLoading
import com.example.againstcovid19.util.showLoading
import com.example.againstcovid19.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetCovidConfirmedGlobal()
        callApiGetCovidRecoveredGlobal()
        callApiGetCovidDeathsGlobal()
    }
    private fun callApiGetCovidConfirmedGlobal() {
        showLoading(context!!, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest = apiRequestGlobal<CovidConfirmedGlobalService>(httpClient)
        val call = apiRequest.getConfirmed()
        call.enqueue(object : Callback<List<CovidConfirmedGlobalItem>> {
            override fun onFailure(call: Call<List<CovidConfirmedGlobalItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }
            override fun onResponse(call: Call<List<CovidConfirmedGlobalItem>>, response:
            Response<List<CovidConfirmedGlobalItem>>) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilCovidConfirmedGlobal(response.body()!!)
                            else -> {
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else -> {
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }
    private fun callApiGetCovidRecoveredGlobal() {
        showLoading(context!!, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest = apiRequestGlobalRecovered<CovidRecoveredGlobalService>(httpClient)
        val call = apiRequest.getRecovered()
        call.enqueue(object : Callback<List<CovidRecoveredGlobalItem>> {
            override fun onFailure(call: Call<List<CovidRecoveredGlobalItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }
            override fun onResponse(call: Call<List<CovidRecoveredGlobalItem>>, response:
            Response<List<CovidRecoveredGlobalItem>>) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilCovidRecoveredGlobal(response.body()!!)
                            else -> {
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else -> {
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }
    private fun callApiGetCovidDeathsGlobal() {
        showLoading(context!!, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest = apiRequestGlobalDeaths<CovidDeathsGlobalService>(httpClient)
        val call = apiRequest.getDeaths()
        call.enqueue(object : Callback<List<CovidDeathsGlobalItem>> {
            override fun onFailure(call: Call<List<CovidDeathsGlobalItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }
            override fun onResponse(call: Call<List<CovidDeathsGlobalItem>>, response:
            Response<List<CovidDeathsGlobalItem>>) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilCovidDeathsGlobal(response.body()!!)
                            else -> {
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else -> {
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }
    private fun tampilCovidConfirmedGlobal(covidConfirmedGlobal: List<CovidConfirmedGlobalItem>) {
        listCovidConfirmedGlobal.layoutManager = LinearLayoutManager(context)
        listCovidConfirmedGlobal.adapter = CovidConfirmedGlobalAdapter(context!!, covidConfirmedGlobal) {
            val covidconfirmedGlobal = it
            tampilToast(context!!, covidconfirmedGlobal.totalConfirmed)
        }
    }
    private fun tampilCovidRecoveredGlobal(covidRecoveredGlobal: List<CovidRecoveredGlobalItem>) {
        listCovidRecoveredGlobal.layoutManager = LinearLayoutManager(context)
        listCovidRecoveredGlobal.adapter = CovidRecoveredGlobalAdapter(context!!, covidRecoveredGlobal) {
            val covidrecoveredGlobal = it
            tampilToast(context!!, covidrecoveredGlobal.totalRecovered)
        }
    }
    private fun tampilCovidDeathsGlobal(covidDeathsGlobal: List<CovidDeathsGlobalItem>) {
        listCovidDeathsGlobal.layoutManager = LinearLayoutManager(context)
        listCovidDeathsGlobal.adapter = CovidDeathsGlobalAdapter(context!!, covidDeathsGlobal) {
            val coviddeathsGlobal = it
            tampilToast(context!!, coviddeathsGlobal.totalDeaths)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
