package com.example.rewetask.db.city

import com.example.rewetask.model.LocalCityDetail
import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("geonames") val cities: List<LocalCityDetail>
)
