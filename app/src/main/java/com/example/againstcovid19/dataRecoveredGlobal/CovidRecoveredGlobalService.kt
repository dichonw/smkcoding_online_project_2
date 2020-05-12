package com.example.againstcovid19.dataRecoveredGlobal

import com.example.againstcovid19.CovidRecoveredGlobalItem
import retrofit2.Call
import retrofit2.http.GET

interface CovidRecoveredGlobalService {
    @GET("recovered")
    fun getRecovered(): Call<List<CovidRecoveredGlobalItem>>
}