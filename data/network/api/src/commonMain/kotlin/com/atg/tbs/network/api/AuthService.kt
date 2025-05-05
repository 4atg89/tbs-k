package com.atg.tbs.network.api

interface AuthService {
    suspend fun login(email: String, password: String): String
    suspend fun loginConfirmation(code: String, verificationId: String): String
}