package com.example.againstcovid19.dataDeathsGlobal

import com.example.againstcovid19.CovidDeathsGlobalItem
import retrofit2.Call
import retrofit2.http.GET

interface CovidDeathsGlobalService {
    @GET("deaths")
    fun getDeaths(): Call<List<CovidDeathsGlobalItem>>
}