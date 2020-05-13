package com.example.againstcovid19


import com.google.gson.annotations.SerializedName

data class CovidDeathsGlobalItem(
    @SerializedName("latestWkidDeaths")
    val latestWkidDeaths: String,
    @SerializedName("nameDeaths")
    val nameDeaths: String,
    @SerializedName("TotalDeaths")
    val totalDeaths: String,
    @SerializedName("wkidDeaths")
    val wkidDeaths: String
)