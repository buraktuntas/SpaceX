package com.burak.rocket.data.repository

import com.burak.rocket.data.remote.RocketServices
import com.burak.rocket.domain.repository.RocketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class RocketRepositoryImpl @Inject constructor(private val services: RocketServices) :
    RocketRepository {
    override suspend fun getRocketList() = flow {
        val response = services.getRocketList()
        emit(response)
    }.flowOn(Dispatchers.IO)
}
