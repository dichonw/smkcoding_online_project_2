package com.example.againstcovid19


import com.google.gson.annotations.SerializedName

data class CovidConfirmedGlobalItem(
    @SerializedName("latestWkidConfirmed")
    val latestWkidConfirmed: String,
    @SerializedName("nameConfirmed")
    val nameConfirmed: String,
    @SerializedName("TotalConfirmed")
    val totalConfirmed: String,
    @SerializedName("wkidConfirmed")
    val wkidConfirmed: String
)