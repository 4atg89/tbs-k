package com.atg.tbs.data.auth.password

import com.atg.tbs.domain.auth.password.PasswordRepository
import com.atg.tbs.network.api.RestoreService
import com.atg.tbs.network.api.dto.CodeConfirmationRequest
import com.atg.tbs.network.api.dto.NewPasswordRequest
import com.atg.tbs.network.api.dto.ResetPasswordRequest

internal class PasswordRepositoryImpl(private val service: RestoreService) : PasswordRepository {

    private var passwordModel: PasswordModel? = null

    override suspend fun passwordForgotten(email: String) {
        val codeModel = service.forgotPassword(ResetPasswordRequest(email))
        passwordModel = PasswordModel(email = email, codeModel = codeModel)
    }

    override suspend fun confirmChangePassword(code: String) {
        val passwordModel = requireNotNull(passwordModel)
        val verificationId = requireNotNull(passwordModel.codeModel).verificationId
        val body = CodeConfirmationRequest(verificationId, code)
        passwordModel.resetToken = service.confirmChangePassword(body).resetToken
        passwordModel.codeModel = null
    }

    override suspend fun changePassword(password: String): String {
        val email = requireNotNull(passwordModel!!.email)
        val resetToken = requireNotNull(passwordModel!!.resetToken)
        passwordModel = null
        return service.changePassword(NewPasswordRequest(email, password, resetToken)).message
    }

}