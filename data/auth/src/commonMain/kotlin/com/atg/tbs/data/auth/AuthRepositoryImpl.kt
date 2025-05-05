package com.atg.tbs.data.auth

import com.atg.tbs.domain.auth.AuthRepository
import com.atg.tbs.network.api.AuthService
import com.atg.tbs.network.api.dto.LoginRequest

internal class AuthRepositoryImpl(private val service: AuthService) : AuthRepository {

    override suspend fun login(email: String, password: String) {
        service.login(LoginRequest(email, password))
    }

}