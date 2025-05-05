package com.atg.tbs.network.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CodeConfirmationRequest(
    @SerialName("VerificationId") val verificationId: String,
    @SerialName("Code") val code: String
)

//--------------------------------------------------------------------------------------------------

@Serializable
class CodeExpirationResponse(
    @SerialName("VerificationId") val verificationId: String,
    @SerialName("ExpirationTime") val expirationTime: String
)

@Serializable
class AuthenticatedResponse(
    @SerialName("Token") val token: String,
    @SerialName("RefreshToken") val refreshToken: String
)
