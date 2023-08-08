package com.burak.rocket.di

import com.burak.rocket.data.local.RocketDataRepository
import com.burak.rocket.domain.repository.RocketRepository
import com.burak.rocket.domain.use_case.GetRocketListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

     /* Use Case */
    @ViewModelScoped
    @Provides
    fun provideCallGetRocketListUseCase(
        rocketRepository: RocketRepository,
        rocketDataRepository: RocketDataRepository
     ): GetRocketListUseCase {
        return GetRocketListUseCase(
            rocketRepository,
            rocketDataRepository
        )
    }
}