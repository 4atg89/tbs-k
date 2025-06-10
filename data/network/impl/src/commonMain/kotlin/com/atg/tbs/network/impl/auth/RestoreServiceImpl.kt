package com.atg.tbs.network.impl.auth

import com.atg.tbs.network.api.RestoreService
import com.atg.tbs.network.api.dto.CodeConfirmationRequest
import com.atg.tbs.network.api.dto.CodeExpirationResponse
import com.atg.tbs.network.api.dto.NewPasswordRequest
import com.atg.tbs.network.api.dto.PasswordChangedResponse
import com.atg.tbs.network.api.dto.ResetPasswordRequest
import com.atg.tbs.network.api.dto.ResetPasswordResponse
import com.atg.tbs.network.impl.NetworkContract
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class RestoreServiceImpl(private val ktor: HttpClient) : RestoreService {

    override suspend fun forgotPassword(body: ResetPasswordRequest): CodeExpirationResponse =
        ktor.post(NetworkContract.Auth.PASSWORD_RESTORE) { setBody(body) }.body()

    override suspend fun confirmChangePassword(body: CodeConfirmationRequest): ResetPasswordResponse =
        ktor.post(NetworkContract.Auth.PASSWORD_CONFIRMATION) { setBody(body) }.body()

    override suspend fun changePassword(body: NewPasswordRequest): PasswordChangedResponse =
        ktor.post(NetworkContract.Auth.PASSWORD_CHANGE) { setBody(body) }.body()

}