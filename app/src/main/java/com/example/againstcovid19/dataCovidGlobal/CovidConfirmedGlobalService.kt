package com.example.againstcovid19.dataCovidGlobal

import com.example.againstcovid19.CovidConfirmedGlobalItem
import retrofit2.Call
import retrofit2.http.GET

interface CovidConfirmedGlobalService {
    @GET("confirmed")
    fun getConfirmed(): Call<List<CovidConfirmedGlobalItem>>
}