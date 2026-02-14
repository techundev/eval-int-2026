package com.techun.dev.pruebatecnicaintecap2026.core.di

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
object RetrofitModule {
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
}