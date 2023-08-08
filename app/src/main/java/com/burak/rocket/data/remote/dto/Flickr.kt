package com.burak.rocket.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Flickr(
    @SerializedName("original")
    val original: List<String?>? = listOf(),
    @SerializedName("small")
    val small: List<Any?>? = listOf()
)