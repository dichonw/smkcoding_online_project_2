package com.example.againstcovid19.dataCovidIndoList

import com.example.againstcovid19.ListDataIndo.CovidIndoListItem
import retrofit2.Call
import retrofit2.http.GET

interface CovidIndoListService {
    @GET("indonesia/provinsi")
    fun getIndo(): Call<List<CovidIndoListItem>>
}