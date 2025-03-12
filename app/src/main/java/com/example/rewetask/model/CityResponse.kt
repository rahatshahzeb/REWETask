package com.example.rewetask.model

import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("geonames") val cities: List<LocalCityDetail>
)