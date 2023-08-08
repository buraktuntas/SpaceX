package com.burak.rocket.data.remote

import com.burak.rocket.data.remote.dto.RocketsDtoItem
import retrofit2.http.GET

interface RocketServices {

    @GET("launches")
    suspend fun getRocketList(): List<RocketsDtoItem>
}