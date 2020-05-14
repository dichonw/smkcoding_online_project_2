package com.example.againstcovid19.dataCovidIndo

import com.example.againstcovid19.CovidIndoItem
import retrofit2.Call
import retrofit2.http.GET

interface CovidIndoService {
    @GET("indonesia")
    fun getIndo(): Call<List<CovidIndoItem>>
}