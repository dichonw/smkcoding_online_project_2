package com.example.againstcovid19.ListDataIndo


import com.google.gson.annotations.SerializedName

data class CovidIndoListItem(
    @SerializedName("attributes")
    val attributes: Attributes
)