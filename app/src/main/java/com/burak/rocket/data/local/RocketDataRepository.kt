package com.burak.rocket.data.local

import com.burak.rocket.data.local.entity.RocketInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RocketDataRepository @Inject constructor(private val rocketInfoDao: RocketInfoDao) {

    fun allList(): Flow<List<RocketInfo>> {
        return rocketInfoDao.readAllRocketData()
    }

    suspend fun addAll(rocketInfo: List<RocketInfo>) {
        rocketInfoDao.addAllRocket(rocketInfo)
    }

    fun readAllfavorite(): Flow<List<RocketInfo>> {
        return rocketInfoDao.readAllFavorite()
    }

    fun updateRocket(rocketInfo: RocketInfo) {
        rocketInfoDao.updateRocket(rocketInfo)
    }

    suspend fun deleteAllRocket() {
        rocketInfoDao.deleteAllRocket()
    }
}