package com.atg.tbs.network.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResetPasswordRequest(
    @SerialName("email") val email: String
)

@Serializable
class NewPasswordRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("resetToken") val resetToken: String
)

//--------------------------------------------------------------------------------------------------

@Serializable
class ResetPasswordResponse(
    @SerialName("resetToken") val resetToken: String
)

@Serializable
class PasswordChangedResponse(
    @SerialName("success") val success: Boolean,
    @SerialName("message") val message: String
)