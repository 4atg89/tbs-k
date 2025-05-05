package com.atg.tbs.network.api

import com.atg.tbs.network.api.dto.AuthenticatedResponse
import com.atg.tbs.network.api.dto.CodeConfirmationRequest
import com.atg.tbs.network.api.dto.CodeExpirationResponse
import com.atg.tbs.network.api.dto.LoginRequest
import com.atg.tbs.network.api.dto.LogoutRequest
import com.atg.tbs.network.api.dto.RefreshTokenRequest
import com.atg.tbs.network.api.dto.RegistrationRequest

interface AuthService {

    suspend fun register(body: RegistrationRequest): CodeExpirationResponse
    suspend fun registerConfirmation(body: CodeConfirmationRequest): AuthenticatedResponse

    suspend fun login(body: LoginRequest): CodeExpirationResponse
    suspend fun loginConfirmation(body: CodeConfirmationRequest): AuthenticatedResponse

    suspend fun logout(body: LogoutRequest)

    suspend fun refresh(body: RefreshTokenRequest): AuthenticatedResponse

}