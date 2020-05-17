package com.example.againstcovid19.dataNews

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun httpClient(): OkHttpClient {
    val logInterceptor = HttpLoggingInterceptor()
    logInterceptor.level = HttpLoggingInterceptor.Level. BODY
    val builder = OkHttpClient.Builder()
    builder.readTimeout( 15 , TimeUnit. SECONDS )
    builder.connectTimeout( 15 , TimeUnit. SECONDS )
    builder.addInterceptor(logInterceptor)
    return builder.build()
}
inline fun < reified T > apiRequestNews(okHttpClient: OkHttpClient): T {
    val gson = GsonBuilder().create()
    val retrofit = Retrofit.Builder()
        .baseUrl( "http://newsapi.org/v2/" )
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    return retrofit.create( T :: class.java )
}