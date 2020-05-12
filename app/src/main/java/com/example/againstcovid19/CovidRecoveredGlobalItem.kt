package com.example.againstcovid19


import com.google.gson.annotations.SerializedName

data class CovidRecoveredGlobalItem(
    @SerializedName("latestWkidRecovered")
    val latestWkidRecovered: String,
    @SerializedName("nameRecovered")
    val nameRecovered: String,
    @SerializedName("TotalRecovered")
    val totalRecovered: String,
    @SerializedName("wkidRecovered")
    val wkidRecovered: String
)