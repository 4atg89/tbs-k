package com.atg.tbs.domain.auth.auth

import com.atg.tbs.domain.auth.model.TokenEntity

interface AuthRepository {

    suspend fun register(email: String, password: String, nickname: String)
    suspend fun confirmRegister(code: String): TokenEntity

    suspend fun login(email: String, password: String)
    suspend fun confirmLogin(code: String): TokenEntity

    suspend fun logout(refreshToken: String)
}