package com.atg.tbs.network.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RegistrationRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("nickname") val nickname: String
)

@Serializable
class LoginRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)

@Serializable
class LogoutRequest(
    @SerialName("refreshToken") val refreshToken: String,
)

@Serializable
class RefreshTokenRequest(
    @SerialName("refreshToken") val refreshToken: String,
)
