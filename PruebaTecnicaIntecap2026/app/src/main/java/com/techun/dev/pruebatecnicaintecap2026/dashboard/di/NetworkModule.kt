package com.techun.dev.pruebatecnicaintecap2026.dashboard.di

import com.techun.dev.pruebatecnicaintecap2026.dashboard.data.repository.UserRepositoryImpl
import com.techun.dev.pruebatecnicaintecap2026.dashboard.data.source.api.UsersApiService
import com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideApiService(retrofit: Retrofit): UsersApiService =
        retrofit.create(UsersApiService::class.java)

    @Provides
    fun provideAuthRepository(apiService: UsersApiService): UserRepository =
        UserRepositoryImpl(apiService)

}