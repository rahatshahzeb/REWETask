package com.example.rewetask.model

import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("query") var query: String? = null,
    @SerializedName("type") var type: String? = null
)