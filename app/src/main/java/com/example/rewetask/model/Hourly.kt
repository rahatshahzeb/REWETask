package com.example.rewetask.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Hourly(
    @SerializedName("DewPointC") var dewPointC: String? = null,
    @SerializedName("DewPointF") var dewPointF: String? = null,
    @SerializedName("FeelsLikeC") var feelsLikeC: String? = null,
    @SerializedName("FeelsLikeF") var feelsLikeF: String? = null,
    @SerializedName("HeatIndexC") var heatIndexC: String? = null,
    @SerializedName("HeatIndexF") var heatIndexF: String? = null,
    @SerializedName("WindChillC") var windChillC: String? = null,
    @SerializedName("WindChillF") var windChillF: String? = null,
    @SerializedName("WindGustKmph") var windGustKmph: String? = null,
    @SerializedName("WindGustMiles") var windGustMiles: String? = null,
    @SerializedName("chanceoffog") var chanceoffog: String? = null,
    @SerializedName("chanceoffrost") var chanceoffrost: String? = null,
    @SerializedName("chanceofhightemp") var chanceofhightemp: String? = null,
    @SerializedName("chanceofovercast") var chanceofovercast: String? = null,
    @SerializedName("chanceofrain") var chanceofrain: String? = null,
    @SerializedName("chanceofremdry") var chanceofremdry: String? = null,
    @SerializedName("chanceofsnow") var chanceofsnow: String? = null,
    @SerializedName("chanceofsunshine") var chanceofsunshine: String? = null,
    @SerializedName("chanceofthunder") var chanceofthunder: String? = null,
    @SerializedName("chanceofwindy") var chanceofwindy: String? = null,
    @SerializedName("cloudcover") var cloudcover: String? = null,
    @SerializedName("diffRad") var diffRad: String? = null,
    @SerializedName("humidity") var humidity: String? = null,
    @SerializedName("precipInches") var precipInches: String? = null,
    @SerializedName("precipMM") var precipMM: String? = null,
    @SerializedName("pressure") var pressure: String? = null,
    @SerializedName("pressureInches") var pressureInches: String? = null,
    @SerializedName("shortRad") var shortRad: String? = null,
    @SerializedName("tempC") var tempC: String? = null,
    @SerializedName("tempF") var tempF: String? = null,
    @SerializedName("time") var time: String? = null,
    @SerializedName("uvIndex") var uvIndex: String? = null,
    @SerializedName("visibility") var visibility: String? = null,
    @SerializedName("visibilityMiles") var visibilityMiles: String? = null,
    @SerializedName("weatherCode") var weatherCode: String? = null,
    @SerializedName("weatherDesc") var weatherDesc: ArrayList<WeatherDesc> = arrayListOf(),
    @SerializedName("weatherIconUrl") var weatherIconUrl: ArrayList<WeatherIconUrl> = arrayListOf(),
    @SerializedName("winddir16Point") var winddir16Point: String? = null,
    @SerializedName("winddirDegree") var winddirDegree: String? = null,
    @SerializedName("windspeedKmph") var windspeedKmph: String? = null,
    @SerializedName("windspeedMiles") var windspeedMiles: String? = null
)