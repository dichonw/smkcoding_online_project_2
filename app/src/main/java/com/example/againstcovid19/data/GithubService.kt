package com.example.againstcovid19.data

import com.example.againstcovid19.GithubUserItem
import retrofit2.Call
import retrofit2.http.GET

interface GithubService {
    @GET("users")
    fun getUsers(): Call<List<GithubUserItem>>
}
