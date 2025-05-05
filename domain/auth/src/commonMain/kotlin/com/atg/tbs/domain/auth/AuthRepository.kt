package com.atg.tbs.domain.auth

interface AuthRepository {
    suspend fun login(email: String, password: String)
}