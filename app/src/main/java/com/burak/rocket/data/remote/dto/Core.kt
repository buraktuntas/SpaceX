package com.burak.rocket.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Core(
    @SerializedName("core")
    val core: String? = "",
    @SerializedName("flight")
    val flight: Int? = 0,
    @SerializedName("gridfins")
    val gridfins: Boolean? = false,
    @SerializedName("landing_attempt")
    val landingAttempt: Boolean? = false,
    @SerializedName("landing_success")
    val landingSuccess: Boolean? = false,
    @SerializedName("landing_type")
    val landingType: String? = "",
    @SerializedName("landpad")
    val landpad: String? = "",
    @SerializedName("legs")
    val legs: Boolean? = false,
    @SerializedName("reused")
    val reused: Boolean? = false
)