package com.atg.tbs.network.api

import com.atg.tbs.network.api.dto.CodeConfirmationRequest
import com.atg.tbs.network.api.dto.CodeExpirationResponse
import com.atg.tbs.network.api.dto.NewPasswordRequest
import com.atg.tbs.network.api.dto.PasswordChangedResponse
import com.atg.tbs.network.api.dto.ResetPasswordRequest
import com.atg.tbs.network.api.dto.ResetPasswordResponse

interface RestoreService {
    suspend fun forgotPassword(body: ResetPasswordRequest): CodeExpirationResponse
    suspend fun confirmChangePassword(body: CodeConfirmationRequest): ResetPasswordResponse
    suspend fun changePassword(body: NewPasswordRequest): PasswordChangedResponse
}