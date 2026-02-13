package com.techun.dev.pruebatecnicaintecap2026.login.di

import com.techun.dev.pruebatecnicaintecap2026.login.data.repository.AuthRepositoryImpl
import com.techun.dev.pruebatecnicaintecap2026.login.data.source.api.AuthApiService
import com.techun.dev.pruebatecnicaintecap2026.login.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    @Provides
    fun provideRetrofit(json: Json): Retrofit =
        Retrofit.Builder().baseUrl("https://today-6083d.firebaseio.com/").addConverterFactory(
            json.asConverterFactory("application/json; charset=utf-8".toMediaType())
        ).build()

    @Provides
    fun provideApiService(retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)

    @Provides
    fun provideAuthRepository(apiService: AuthApiService): AuthRepository =
        AuthRepositoryImpl(apiService)

}