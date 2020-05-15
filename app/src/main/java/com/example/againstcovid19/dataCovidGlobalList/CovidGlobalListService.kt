package com.example.againstcovid19.dataCovidGlobalList

import com.example.againstcovid19.ListData.CovidGlobalListItem
import retrofit2.Call
import retrofit2.http.GET

interface CovidGlobalListService {
    @GET(".")
    fun getGlobal(): Call<List<CovidGlobalListItem>>
}