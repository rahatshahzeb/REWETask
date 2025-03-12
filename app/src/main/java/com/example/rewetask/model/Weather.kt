package com.example.rewetask.model

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("astronomy") var astronomy: ArrayList<Astronomy> = arrayListOf(),
    @SerializedName("avgtempC") var avgTempC: String? = null,
    @SerializedName("avgtempF") var avgTempF: String? = null,
    @SerializedName("date") var date: String? = null,
    @SerializedName("hourly") var hourly: ArrayList<Hourly> = arrayListOf(),
    @SerializedName("maxtempC") var maxTempC: String? = null,
    @SerializedName("maxtempF") var maxTempF: String? = null,
    @SerializedName("mintempC") var minTempC: String? = null,
    @SerializedName("mintempF") var minTempF: String? = null,
    @SerializedName("sunHour") var sunHour: String? = null,
    @SerializedName("totalSnow_cm") var totalSnowCm: String? = null,
    @SerializedName("uvIndex") var uvIndex: String? = null
)
