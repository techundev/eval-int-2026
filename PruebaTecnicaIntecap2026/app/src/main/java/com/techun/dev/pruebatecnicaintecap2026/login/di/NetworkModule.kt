package com.techun.dev.pruebatecnicaintecap2026.login.di

import com.techun.dev.pruebatecnicaintecap2026.login.data.repository.AuthRepositoryImpl
import com.techun.dev.pruebatecnicaintecap2026.login.data.source.api.AuthApiService
import com.techun.dev.pruebatecnicaintecap2026.login.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideApiService(retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)

    @Provides
    fun provideAuthRepository(apiService: AuthApiService): AuthRepository =
        AuthRepositoryImpl(apiService)

}