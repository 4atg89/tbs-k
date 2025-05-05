package com.atg.tbs.network.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CodeConfirmationRequest(
    @SerialName("verificationId") val verificationId: String,
    @SerialName("code") val code: String
)

//--------------------------------------------------------------------------------------------------

@Serializable
class CodeExpirationResponse(
    @SerialName("verificationId") val verificationId: String,
    @SerialName("expirationTime") val expirationTime: String
)

@Serializable
class AuthenticatedResponse(
    @SerialName("token") val token: String,
    @SerialName("refreshToken") val refreshToken: String
)
