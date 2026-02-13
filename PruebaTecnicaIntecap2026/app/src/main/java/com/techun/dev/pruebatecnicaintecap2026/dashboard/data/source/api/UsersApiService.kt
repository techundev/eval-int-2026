package com.techun.dev.pruebatecnicaintecap2026.dashboard.data.source.api

import com.techun.dev.pruebatecnicaintecap2026.dashboard.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UsersApiService {
    @GET("getAllUsers/.json")
    suspend fun getAllUsers(): Response<Map<String, UserResponse>>
}