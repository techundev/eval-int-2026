package com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.usecase

import com.techun.dev.pruebatecnicaintecap2026.core.data.toNetwork
import com.techun.dev.pruebatecnicaintecap2026.core.domain.User
import com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUser @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        id: String, user: User
    ): Result<Unit> {
        return try {
            repository.saveAndUpdateUser(id, user.toNetwork())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}