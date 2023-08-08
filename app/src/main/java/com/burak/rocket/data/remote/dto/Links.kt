package com.burak.rocket.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("article")
    val article: String? = "",
    @SerializedName("flickr")
    val flickr: Flickr? = Flickr(),
    @SerializedName("patch")
    val patch: Patch? = Patch(),
    @SerializedName("presskit")
    val presskit: String? = "",
    @SerializedName("reddit")
    val reddit: Reddit? = Reddit(),
    @SerializedName("webcast")
    val webcast: String? = "",
    @SerializedName("wikipedia")
    val wikipedia: String? = "",
    @SerializedName("youtube_id")
    val youtubeİd: String? = ""
)