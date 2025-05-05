package com.atg.tbs.network.impl.auth.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResetPasswordRequest(
    @SerialName("Email") val email: String
)

@Serializable
class NewPasswordRequest(
    @SerialName("Email") val email: String,
    @SerialName("ResetToken") val resetToken: String,
    @SerialName("Password") val password: String
)

//--------------------------------------------------------------------------------------------------

@Serializable
class ResetPasswordResponse(
    @SerialName("Success") val success: Boolean
)

@Serializable
class PasswordChangedResponse(
    @SerialName("Message") val message: String
)