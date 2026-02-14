package com.techun.dev.pruebatecnicaintecap2026.dashboard.data.repository

import android.util.Log
import com.techun.dev.pruebatecnicaintecap2026.core.data.UserResponse
import com.techun.dev.pruebatecnicaintecap2026.core.domain.User
import com.techun.dev.pruebatecnicaintecap2026.core.domain.UserRole
import com.techun.dev.pruebatecnicaintecap2026.dashboard.data.source.api.UsersApiService
import com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: UsersApiService) : UserRepository {

    override val users: Flow<List<User>> = flow {
        val response = api.getAllUsers()

        if (response.isSuccessful) {
            val body = response.body() ?: emptyMap()

            val userList = body.values.mapNotNull { dto ->
                if (dto.status == 1) {
                    User(
                        id = dto.id,
                        name = dto.name,
                        lastName = dto.lastName,
                        role = UserRole.fromInt(dto.role),
                        email = dto.email,
                        phoneNumber = dto.phoneNumber,
                        username = dto.username,
                        password = dto.password,
                        status = dto.status
                    )
                } else {
                    null
                }
            }
            emit(userList)
        } else {
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun saveAndUpdateUser(
        id: String, user: UserResponse
    ) {
        withContext(Dispatchers.IO) {
            val resUser = api.saveUserAuth(id, user)

            if (!resUser.isSuccessful) {
                Log.e(
                    "FIREBASE_ERROR",
                    "Auth falló: ${resUser.errorBody()?.string()} Código: ${resUser.code()}"
                )
            }

            if (!resUser.isSuccessful) {
                throw Exception("Error Firebase: Auth=${resUser.code()}")
            }
        }
    }
}
