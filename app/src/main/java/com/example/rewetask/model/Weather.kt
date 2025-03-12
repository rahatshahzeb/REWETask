package com.example.rewetask.model

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("astronomy") var astronomy: ArrayList<Astronomy> = arrayListOf(),
    @SerializedName("avgtempC") var avgtempC: String? = null,
    @SerializedName("avgtempF") var avgtempF: String? = null,
    @SerializedName("date") var date: String? = null,
    @SerializedName("hourly") var hourly: ArrayList<Hourly> = arrayListOf(),
    @SerializedName("maxtempC") var maxtempC: String? = null,
    @SerializedName("maxtempF") var maxtempF: String? = null,
    @SerializedName("mintempC") var mintempC: String? = null,
    @SerializedName("mintempF") var mintempF: String? = null,
    @SerializedName("sunHour") var sunHour: String? = null,
    @SerializedName("totalSnow_cm") var totalSnowCm: String? = null,
    @SerializedName("uvIndex") var uvIndex: String? = null
)
