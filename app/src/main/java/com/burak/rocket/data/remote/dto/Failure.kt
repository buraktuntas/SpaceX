package com.burak.rocket.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Failure(
    @SerializedName("altitude")
    val altitude: Int? = 0,
    @SerializedName("reason")
    val reason: String? = "",
    @SerializedName("time")
    val time: Int? = 0
)