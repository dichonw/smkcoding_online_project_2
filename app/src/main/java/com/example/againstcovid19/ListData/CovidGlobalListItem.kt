package com.example.againstcovid19.ListData


import com.google.gson.annotations.SerializedName

data class CovidGlobalListItem(
    @SerializedName("attributes")
    val attributes: Attributes
)