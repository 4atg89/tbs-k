package com.atg.tbs.data.auth.auth

import com.atg.tbs.domain.auth.auth.AuthRepository
import com.atg.tbs.domain.auth.model.TokenEntity
import com.atg.tbs.network.api.AuthService
import com.atg.tbs.network.api.dto.CodeConfirmationRequest
import com.atg.tbs.network.api.dto.CodeExpirationResponse
import com.atg.tbs.network.api.dto.LoginRequest
import com.atg.tbs.network.api.dto.LogoutRequest
import com.atg.tbs.network.api.dto.RegistrationRequest

internal class AuthRepositoryImpl(private val service: AuthService) : AuthRepository {

    private var codeModel: CodeExpirationResponse? = null

    override suspend fun register(email: String, password: String, nickname: String) {
        codeModel = service.register(RegistrationRequest(email, password, nickname))
    }

    override suspend fun confirmRegister(code: String): TokenEntity {
        val verificationId = requireNotNull(codeModel).verificationId
        val token = service.registerConfirmation(CodeConfirmationRequest(verificationId, code))
        codeModel = null
        return token.let { TokenEntity(it.token, it.refreshToken) }
    }

    override suspend fun login(email: String, password: String) {
        codeModel = service.login(LoginRequest(email, password))
    }

    override suspend fun confirmLogin(code: String): TokenEntity {
        val verificationId = requireNotNull(codeModel).verificationId
        val token = service.loginConfirmation(CodeConfirmationRequest(verificationId, code))
        codeModel = null
        return token.let { TokenEntity(it.token, it.refreshToken) }
    }

    override suspend fun logout(refreshToken: String) {
        service.logout(LogoutRequest(refreshToken))
    }

}