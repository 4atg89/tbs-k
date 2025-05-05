package com.atg.tbs.network.impl.auth

import com.atg.tbs.network.api.AuthService
import com.atg.tbs.network.impl.NetworkContract
import com.atg.tbs.network.impl.auth.dto.AuthenticatedResponse
import com.atg.tbs.network.impl.auth.dto.CodeConfirmationRequest
import com.atg.tbs.network.impl.auth.dto.CodeExpirationResponse
import com.atg.tbs.network.impl.auth.dto.LoginRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthServiceImpl(private val ktor: HttpClient): AuthService {
    override suspend fun login(email: String, password: String): String {
        return ktor.post(NetworkContract.Auth.LOGIN) {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(email = email, password = password))
        }.body<CodeExpirationResponse>().verificationId
    }

    override suspend fun loginConfirmation(code: String, verificationId: String): String {
        return ktor.post(NetworkContract.Auth.LOGIN_CONFIRMATION) {
            contentType(ContentType.Application.Json)
            setBody(CodeConfirmationRequest(code = code, verificationId = verificationId))
        }.body<AuthenticatedResponse>().token
    }

}