package com.example.rewetask.model

import com.google.gson.annotations.SerializedName

data class WeatherUrl(
    @SerializedName("value") var value: String? = null
)
