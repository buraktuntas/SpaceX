package com.burak.rocket.domain.repository

import com.burak.rocket.data.remote.dto.RocketsDtoItem
import kotlinx.coroutines.flow.Flow

interface RocketRepository {
    suspend fun getRocketList(): Flow<List<RocketsDtoItem>>
}
