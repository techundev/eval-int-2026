package com.techun.dev.pruebatecnicaintecap2026.dashboard.data.source.api

import com.techun.dev.pruebatecnicaintecap2026.core.data.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UsersApiService {
    @GET("users/.json")
    suspend fun getAllUsers(): Response<Map<String, UserResponse>>

    @PUT("users/{id}.json")
    suspend fun saveUserAuth(
        @Path("id") id: String,
        @Body auth: UserResponse
    ): Response<UserResponse>

}