package com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.usecase

import com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.model.UserModel
import com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUsers @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<List<UserModel>> = userRepository.users
}