package com.atg.tbs.network.impl.auth.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RegistrationRequest(
    @SerialName("Password") val password: String,
    @SerialName("Email") val email: String,
    @SerialName("Nickname") val nickname: String
)

@Serializable
class LoginRequest(
    @SerialName("Email") val email: String,
    @SerialName("Password") val password: String,
)

@Serializable
class LogoutRequest(
    @SerialName("RefreshToken") val refreshToken: String,
)

@Serializable
class RefreshTokenRequest(
    @SerialName("RefreshToken") val refreshToken: String,
)
