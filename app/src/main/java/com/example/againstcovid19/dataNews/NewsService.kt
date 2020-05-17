package com.example.againstcovid19.dataNews

import com.example.againstcovid19.News.News
import retrofit2.Call
import retrofit2.http.GET

interface NewsService {
    @GET("top-headlines?country=id&category=health&apiKey=a6c3f397825441b1887051aef349fe12")
    fun getNews(): Call<News>
}