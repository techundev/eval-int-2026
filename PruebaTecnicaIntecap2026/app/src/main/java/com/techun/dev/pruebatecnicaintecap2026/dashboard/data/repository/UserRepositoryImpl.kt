package com.techun.dev.pruebatecnicaintecap2026.dashboard.data.repository

import com.techun.dev.pruebatecnicaintecap2026.dashboard.data.source.api.UsersApiService
import com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.model.UserModel
import com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.repository.UserRepository
import com.techun.dev.pruebatecnicaintecap2026.login.domain.model.UserRole
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val usersApiService: UsersApiService) :
    UserRepository {

    override val users: Flow<List<UserModel>> = flow {
        val response = usersApiService.getAllUsers()

        if (response.isSuccessful) {
            val body = response.body() ?: emptyMap()

            val userList = body.map { (key, dto) ->

                UserModel(
                    idUser = key,
                    name = dto.name,
                    lastName = dto.lastName,
                    role = UserRole.fromInt(dto.role),
                    email = dto.email,
                    phoneNumber = dto.phoneNumber
                )
            }
            emit(userList)
        } else {
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)

}
