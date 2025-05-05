package com.atg.tbs.domain.auth.password

interface PasswordRepository {
    suspend fun passwordForgotten(email: String)
    suspend fun confirmChangePassword(code: String)
    suspend fun changePassword(password: String): String
}