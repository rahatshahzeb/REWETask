package com.example.rewetask.model

import com.google.gson.annotations.SerializedName


data class Astronomy(
    @SerializedName("moon_illumination") var moonIllumination: String? = null,
    @SerializedName("moon_phase") var moonPhase: String? = null,
    @SerializedName("moonrise") var moonRise: String? = null,
    @SerializedName("moonset") var moonSet: String? = null,
    @SerializedName("sunrise") var sunRise: String? = null,
    @SerializedName("sunset") var sunSet: String? = null
)