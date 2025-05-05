package com.atg.tbs.network.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResetPasswordRequest(
    @SerialName("Email") val email: String
)

@Serializable
class NewPasswordRequest(
    @SerialName("Email") val email: String,
    @SerialName("Password") val password: String,
    @SerialName("ResetToken") val resetToken: String
)

//--------------------------------------------------------------------------------------------------

@Serializable
class ResetPasswordResponse(
    @SerialName("ResetToken") val resetToken: String
)

@Serializable
class PasswordChangedResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String
)