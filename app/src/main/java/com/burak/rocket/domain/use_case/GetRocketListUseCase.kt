package com.burak.rocket.domain.use_case

import com.burak.rocket.common.ResultState
import com.burak.rocket.data.local.RocketDataRepository
import com.burak.rocket.data.local.entity.RocketInfo
import com.burak.rocket.data.remote.dto.RocketsDtoItem
import com.burak.rocket.domain.repository.RocketRepository
import kotlinx.coroutines.flow.catch

class GetRocketListUseCase(
    private val rocketRepository: RocketRepository,
    private val rocketDataRepository: RocketDataRepository

) {

    suspend operator fun invoke(onResult: suspend (ResultState<List<RocketInfo>>) -> Unit) {
        rocketRepository.getRocketList()
            .catch { exception ->
                onResult(ResultState.Error(exception))
            }
            .collect { rocketsDtoList ->
                val rocketInfoList = convertResponseToUI(rocketsDtoList)
                rocketDataRepository.addAll(rocketInfoList)
                onResult(ResultState.Success(rocketInfoList))
            }
    }

    private fun convertResponseToUI(response: List<RocketsDtoItem>): List<RocketInfo> {
        return response.map { rocketsDtoItem ->
            with(rocketsDtoItem) {
                RocketInfo(
                    id =  id?: "1",
                    name = name ?: "",
                    isFavorite = false,
                    image = links?.patch?.small ?: "",
                    sliderListUrl = links?.flickr?.original?.filterNotNull() ?: emptyList(),
                    description = details ?: ""
                )
            }
        }
    }
}