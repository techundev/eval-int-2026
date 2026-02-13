package com.techun.dev.pruebatecnicaintecap2026.dashboard.data.source.api

import com.techun.dev.pruebatecnicaintecap2026.core.data.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UsersApiService {
    @GET("users/.json")
    suspend fun getAllUsers(): Response<Map<String, UserResponse>>

    // --- ESCRITURA EN NODO DETALLES ---
    @PUT("users/{id}.json")
    suspend fun saveUserDetail(
        @Path("id") id: String,
        @Body detail: UserResponse
    ): Response<UserResponse>

    @DELETE("users/{id}.json")
    suspend fun deleteUserDetail(@Path("id") id: String): Response<Unit>

    // --- ESCRITURA EN NODO AUTH (LOGIN) ---
    @PUT("users/{id}.json")
    suspend fun saveUserAuth(
        @Path("id") id: String,
        @Body auth: UserResponse // Este es el que tiene password y username
    ): Response<UserResponse>

    @DELETE("users/{id}.json")
    suspend fun deleteUserAuth(@Path("id") id: String): Response<Unit>
}