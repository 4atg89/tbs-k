package com.atg.tbs.network.impl.auth

import com.atg.tbs.network.api.AuthService
import com.atg.tbs.network.impl.NetworkContract
import com.atg.tbs.network.api.dto.CodeConfirmationRequest
import com.atg.tbs.network.api.dto.AuthenticatedResponse
import com.atg.tbs.network.api.dto.CodeExpirationResponse
import com.atg.tbs.network.api.dto.LoginRequest
import com.atg.tbs.network.api.dto.LogoutRequest
import com.atg.tbs.network.api.dto.RegistrationRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerAuthProvider
import io.ktor.client.plugins.plugin
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AuthServiceImpl(private val ktor: HttpClient) : AuthService {

    override suspend fun register(body: RegistrationRequest): CodeExpirationResponse =
        ktor.post(NetworkContract.Auth.REGISTER) { setBody(body) }.body()

    override suspend fun registerConfirmation(body: CodeConfirmationRequest): AuthenticatedResponse =
        ktor.post(NetworkContract.Auth.REGISTER_CONFIRMATION) { setBody(body) }.body()

    override suspend fun login(body: LoginRequest): CodeExpirationResponse =
        ktor.post(NetworkContract.Auth.LOGIN) { setBody(body) }.body()

    override suspend fun loginConfirmation(body: CodeConfirmationRequest): AuthenticatedResponse =
        ktor.post(NetworkContract.Auth.LOGIN_CONFIRMATION) { setBody(body) }.body()

    override suspend fun logout(body: LogoutRequest) {
        ktor.post(NetworkContract.Auth.LOGOUT) { setBody(body) }.body<Unit>()
        ktor.plugin(Auth).providers.filterIsInstance<BearerAuthProvider>().firstOrNull()?.clearToken()
    }

}