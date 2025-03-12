package com.example.rewetask.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherIconUrl(
    @SerializedName("value") var value: String? = null
)