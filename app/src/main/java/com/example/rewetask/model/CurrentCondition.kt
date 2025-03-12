package com.example.rewetask.model

import com.google.gson.annotations.SerializedName

data class CurrentCondition(
    @SerializedName("FeelsLikeC") var feelsLikeC: String? = null,
    @SerializedName("FeelsLikeF") var feelsLikeF: String? = null,
    @SerializedName("cloudcover") var cloudCover: String? = null,
    @SerializedName("humidity") var humidity: String? = null,
    @SerializedName("localObsDateTime") var localObsDateTime: String? = null,
    @SerializedName("observation_time") var observationTime: String? = null,
    @SerializedName("precipInches") var precipInches: String? = null,
    @SerializedName("precipMM") var precipMM: String? = null,
    @SerializedName("pressure") var pressure: String? = null,
    @SerializedName("pressureInches") var pressureInches: String? = null,
    @SerializedName("temp_C") var tempC: String? = null,
    @SerializedName("temp_F") var tempF: String? = null,
    @SerializedName("uvIndex") var uvIndex: String? = null,
    @SerializedName("visibility") var visibility: String? = null,
    @SerializedName("visibilityMiles") var visibilityMiles: String? = null,
    @SerializedName("weatherCode") var weatherCode: String? = null,
    @SerializedName("weatherDesc") var weatherDesc: ArrayList<WeatherDesc> = arrayListOf(),
    @SerializedName("weatherIconUrl") var weatherIconUrl: ArrayList<WeatherIconUrl> = arrayListOf(),
    @SerializedName("winddir16Point") var windDir16Point: String? = null,
    @SerializedName("winddirDegree") var windDirDegree: String? = null,
    @SerializedName("windspeedKmph") var windSpeedKmph: String? = null,
    @SerializedName("windspeedMiles") var windSpeedMiles: String? = null
)