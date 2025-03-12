package com.example.rewetask.model

import com.google.gson.annotations.SerializedName

data class NearestArea(
    @SerializedName("areaName") var areaName: ArrayList<AreaName> = arrayListOf(),
    @SerializedName("country") var country: ArrayList<Country> = arrayListOf(),
    @SerializedName("latitude") var latitude: String? = null,
    @SerializedName("longitude") var longitude: String? = null,
    @SerializedName("population") var population: String? = null,
    @SerializedName("region") var region: ArrayList<Region> = arrayListOf(),
    @SerializedName("weatherUrl") var weatherUrl: ArrayList<WeatherUrl> = arrayListOf()
)