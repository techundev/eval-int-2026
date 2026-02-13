package com.techun.dev.pruebatecnicaintecap2026.login.data.source.api

import com.techun.dev.pruebatecnicaintecap2026.login.data.model.UserResponse
import retrofit2.http.GET

interface AuthApiService {
    @GET("doLogin/.json")
    suspend fun doLogin(): Map<String, UserResponse>
}