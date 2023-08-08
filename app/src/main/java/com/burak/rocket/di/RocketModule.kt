package com.burak.rocket.di

import com.burak.rocket.data.remote.RocketServices
import com.burak.rocket.data.repository.RocketRepositoryImpl
import com.burak.rocket.domain.repository.RocketRepository
import com.burak.rocket.domain.use_case.GetRocketListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RocketModule {

    /* Services */
    @Provides
    @Singleton
    fun provideRocketService(
        retrofit: Retrofit
    ): RocketServices {
        return retrofit.create(RocketServices::class.java)
    }

    /* Repositories */
    @Provides
    @Singleton
    fun provideRocketListRepository(
        service: RocketServices,
    ): RocketRepository = RocketRepositoryImpl(service)

}