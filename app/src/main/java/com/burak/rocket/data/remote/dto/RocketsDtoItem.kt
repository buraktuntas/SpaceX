package com.burak.rocket.data.remote.dto


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class RocketsDtoItem(
    @SerializedName("auto_update")
    val autoUpdate: Boolean? = false,
    @SerializedName("capsules")
    val capsules: List<String?>? = listOf(),
    @SerializedName("cores")
    val cores: @RawValue List<Core?>? = listOf(),
    @SerializedName("crew")
    val crew: @RawValue List<Crew?>? = listOf(),
    @SerializedName("date_local")
    val dateLocal: String? = "",
    @SerializedName("date_precision")
    val datePrecision: String? = "",
    @SerializedName("date_unix")
    val dateUnix: Int? = 0,
    @SerializedName("date_utc")
    val dateUtc: String? = "",
    @SerializedName("details")
    val details: String? = "",
    @SerializedName("failures")
    val failures: @RawValue List<Failure?>? = listOf(),
    @SerializedName("fairings")
    val fairings: @RawValue Fairings? = Fairings(),
    @SerializedName("flight_number")
    val flightNumber: Int? = 0,
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("launch_library_id")
    val launchLibraryİd: String? = "",
    @SerializedName("launchpad")
    val launchpad: String? = "",
    @SerializedName("links")
    val links: @RawValue Links? = Links(),
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("net")
    val net: Boolean? = false,
    @SerializedName("payloads")
    val payloads: List<String?>? = listOf(),
    @SerializedName("rocket")
    val rocket: String? = "",
    @SerializedName("ships")
    val ships: List<String?>? = listOf(),
    @SerializedName("static_fire_date_unix")
    val staticFireDateUnix: Int? = 0,
    @SerializedName("static_fire_date_utc")
    val staticFireDateUtc: String? = "",
    @SerializedName("success")
    val success: Boolean? = false,
    @SerializedName("tbd")
    val tbd: Boolean? = false,
    @SerializedName("upcoming")
    val upcoming: Boolean? = false,
    @SerializedName("window")
    val window: Int? = 0
): Parcelable