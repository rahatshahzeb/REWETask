package com.example.rewetask.model

import com.google.gson.annotations.SerializedName


data class Astronomy(
    @SerializedName("moon_illumination") var moonIllumination: String? = null,
    @SerializedName("moon_phase") var moonPhase: String? = null,
    @SerializedName("moonrise") var moonrise: String? = null,
    @SerializedName("moonset") var moonset: String? = null,
    @SerializedName("sunrise") var sunrise: String? = null,
    @SerializedName("sunset") var sunset: String? = null
)