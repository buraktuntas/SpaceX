package com.burak.rocket.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Patch(
    @SerializedName("large")
    val large: String? = "",
    @SerializedName("small")
    val small: String? = ""
)